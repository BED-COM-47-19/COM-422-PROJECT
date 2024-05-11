

package com.example.teachandlearn.Student.Form3.Documents;
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
import com.example.teachandlearn.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;
import java.util.List;


public class Form3PDF extends AppCompatActivity {
    private RecyclerView recyclerViewPDFs;
    private PDFAdapter adapter;

    // Firebase Storage reference
    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form3_pdf);

        // Initialize Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        recyclerViewPDFs = findViewById(R.id.recyclerViewPDFs);
        recyclerViewPDFs.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PDFAdapter(new ArrayList<>(), this);
        recyclerViewPDFs.setAdapter(adapter);

        fetchPDFsFromFirebase();
    }

    private void fetchPDFsFromFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("form3_pdfs");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<PDFDocument> pdfs = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    PDFDocument pdf = snapshot.getValue(PDFDocument.class);

                    // Retrieve the download URL for each PDF
                    retrieveDownloadUrl(pdf);

                    pdfs.add(pdf);
                }
                adapter.setPDFDocuments(pdfs);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("PDF", "Failed to read PDF files", databaseError.toException());
            }
        });
    }

    private void retrieveDownloadUrl(PDFDocument pdf) {
        StorageReference fileRef = storageRef.child("form3_uploads").child(pdf.getFilePath());
        fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
            // Update the PDFDocument object with the download URL
            pdf.setDownloadUrl(uri.toString());
            adapter.notifyDataSetChanged(); // Notify adapter of the change
        }).addOnFailureListener(exception -> {
            // Handle any errors
            Log.e("Form3PDF", "Error getting download URL: " + exception.getMessage());
        });
    }

    public static class PDFDocument {
        private String title;
        private String filePath;
        private String downloadUrl; // Download URL for Firebase Storage

        public PDFDocument() {
        }

        public PDFDocument(String title, String filePath) {
            this.title = title;
            this.filePath = filePath;
        }

        public String getTitle() {
            return title;
        }

        public String getFilePath() {
            return filePath;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }
    }

    private class PDFAdapter extends RecyclerView.Adapter<PDFAdapter.PDFViewHolder> {
        private List<PDFDocument> pdfDocuments;
        private Context context;

        public PDFAdapter(List<PDFDocument> pdfDocuments, Context context) {
            this.pdfDocuments = pdfDocuments;
            this.context = context;
        }

        @Override
        public PDFViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_form3_pdf_item, parent, false);
            return new PDFViewHolder(view);
        }

        @Override
        public void onBindViewHolder(PDFViewHolder holder, int position) {
            PDFDocument document = pdfDocuments.get(position);
            holder.textViewTitle.setText(document.getTitle());

            // If download URL is available, set click listener to open PDF
            if (document.getDownloadUrl() != null && !document.getDownloadUrl().isEmpty()) {
                holder.itemView.setOnClickListener(v -> openPDF(document.getDownloadUrl()));
            } else {
                holder.itemView.setOnClickListener(v -> {
                    // Handle scenario when download URL is not available
                    Toast.makeText(context, "Download URL not available", Toast.LENGTH_SHORT).show();
                });
            }
        }

        private void openPDF(String downloadUrl) {
            Uri pdfUri = Uri.parse(downloadUrl);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(pdfUri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            Intent chooser = Intent.createChooser(intent, "Open with");

            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(chooser);
            } else {
                Toast.makeText(context, "No application found to open this file.", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public int getItemCount() {
            return pdfDocuments.size();
        }

        public void setPDFDocuments(List<PDFDocument> pdfDocuments) {
            this.pdfDocuments = pdfDocuments;
            notifyDataSetChanged(); // Notify the adapter that the data set has changed
        }

        public class PDFViewHolder extends RecyclerView.ViewHolder {
            TextView textViewTitle;

            public PDFViewHolder(View itemView) {
                super(itemView);
                textViewTitle = itemView.findViewById(R.id.textViewTitle);
            }
        }
    }
}
