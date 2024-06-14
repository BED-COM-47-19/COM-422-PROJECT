

package com.example.teachandlearn.Student.Form2.Documents.Mathematics;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teachandlearn.CHATGPT.ChatBot;
import com.example.teachandlearn.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Form2PDF extends AppCompatActivity {
    private RecyclerView recyclerViewPDFs;
    private PDFAdapter adapter;

    private String studentEmail;


    private static final String TAG = "Form2PDF";
    private static final int REQUEST_OPEN_DOCUMENT = 1;
    private String studentName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_pdf);

        recyclerViewPDFs = findViewById(R.id.recyclerViewPDFs);
        recyclerViewPDFs.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PDFAdapter(new ArrayList<>(), this);
        recyclerViewPDFs.setAdapter(adapter);

        fetchPDFsFromFirebase();

        studentEmail = getIntent().getStringExtra("student_form2_mathematics_emails");

        if (studentEmail != null) {
            // Store student email to Firebase when the intent has student email
            saveStudentEmailToFirebase(studentEmail);

        }

    }


    private void openChatActivity() {

        Intent intent = new Intent(this, ChatBot.class);
        startActivity(intent);

    }

    private void fetchPDFsFromFirebase() {

        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference[] storageRefs = {

                storage.getReference().child("/form2/sciences/mathematics/pdfs/")


        };


        for (StorageReference storageRef : storageRefs) {

            storageRef.listAll().addOnSuccessListener(listResult -> {
                List<PDFDocument> pdfs = new ArrayList<>();

                for (StorageReference item : listResult.getItems()) {
                    item.getDownloadUrl().addOnSuccessListener(uri -> {
                        pdfs.add(new PDFDocument(item.getName(), uri.toString()));
                        adapter.setPDFDocuments(pdfs);
                    }).addOnFailureListener(exception -> {
                        Log.e("PDF", "Failed to get download URL for PDF", exception);
                    });
                }

                if (pdfs.isEmpty()) {
                    showNoFilesUploaded();
                }
            }).addOnFailureListener(exception -> {
                Log.e("PDF", "Failed to list PDF files", exception);
                showNoFilesUploaded();

            });

        }

    }

    private void logStudentAccess(String studentName, String accessedFile) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> accessData = new HashMap<>();
        accessData.put("studentName", studentName);
        accessData.put("accessedFile", accessedFile);
        db.collection("student_access")
                .add(accessData)
                .addOnSuccessListener(documentReference -> Log.d(TAG, "Access log added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w(TAG, "Error adding access log", e));

    }


    private void fetchPDFsFromFirebase(String studentEmail) {
        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference[] storageRefs = {
                storage.getReference().child("/form2/sciences/mathematics/pdfs/")
                // Add more storage references for other subjects as needed
        };

        List<String> studentEmails = new ArrayList<>(); // List to store student emails

        for (StorageReference storageRef : storageRefs) {
            storageRef.listAll().addOnSuccessListener(listResult -> {
                List<PDFDocument> pdfs = new ArrayList<>();

                for (StorageReference item : listResult.getItems()) {
                    item.getDownloadUrl().addOnSuccessListener(uri -> {
                        pdfs.add(new PDFDocument(item.getName(), uri.toString()));
                        adapter.setPDFDocuments(pdfs);

                        // Save student email to list
                        studentEmails.add(studentEmail);
                    }).addOnFailureListener(exception -> {
                        Log.e("PDF", "Failed to get download URL for PDF", exception);
                    });
                }

                if (pdfs.isEmpty()) {
                    showNoFilesUploaded();
                }

                // Now you can save the list of student emails to Firebase
                saveStudentEmailToFirebase(studentEmail);
            }).addOnFailureListener(exception -> {
                Log.e("PDF", "Failed to list PDF files", exception);
                showNoFilesUploaded();

            });

        }

    }


    private void saveStudentEmailToFirebase(String studentEmail) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String userId = currentUser.getUid(); // Get the unique user ID
            DatabaseReference studentEmailsRef = FirebaseDatabase.getInstance().getReference().child("student_form1_emails").child(userId);
            studentEmailsRef.push().setValue(studentEmail);

        }
        else {
            // Handle the case where the user is not authenticated
            Log.e("Authentication Error", "User not authenticated");
        }

    }


    private void showNoFilesUploaded() {
        adapter.setPDFDocuments(new ArrayList<>());
        Toast.makeText(this, "No file Uploaded", Toast.LENGTH_SHORT).show();
    }

    public static class PDFDocument {
        private String title;
        private String downloadUrl;

        public PDFDocument() {
        }

        public PDFDocument(String title, String downloadUrl) {
            this.title = title;
            this.downloadUrl = downloadUrl;
        }

        public String getTitle() {
            return title;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }
    }

    private static class PDFAdapter extends RecyclerView.Adapter<PDFAdapter.PDFViewHolder> {
        private List<PDFDocument> pdfDocuments;
        private Context context;

        public PDFAdapter(List<PDFDocument> pdfDocuments, Context context) {
            this.pdfDocuments = pdfDocuments;
            this.context = context;
        }

        @NonNull
        @Override
        public PDFViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_form1_pdf_item, parent, false);
            return new PDFViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PDFViewHolder holder, int position) {

            PDFDocument document = pdfDocuments.get(position);
            holder.textViewTitle.setText(document.getTitle());
            holder.itemView.setOnClickListener(v -> downloadAndOpenPDF(document.getDownloadUrl()));
        }

        private void downloadAndOpenPDF(String downloadUrl) {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse(downloadUrl), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            }
            else {
                Toast.makeText(context, "No application found to open this file.", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public int getItemCount() {

            return pdfDocuments.size();
        }

        public void setPDFDocuments(List<PDFDocument> pdfDocuments) {
            this.pdfDocuments = pdfDocuments;
            notifyDataSetChanged();
        }

        public static class PDFViewHolder extends RecyclerView.ViewHolder {
            TextView textViewTitle;

            public PDFViewHolder(View itemView) {
                super(itemView);
                textViewTitle = itemView.findViewById(R.id.textViewTitle);

            }
        }
    }

}
