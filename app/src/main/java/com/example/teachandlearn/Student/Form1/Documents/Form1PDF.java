

package com.example.teachandlearn.Student.Form1.Documents;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;
import java.util.List;



public class Form1PDF extends AppCompatActivity {
    private RecyclerView recyclerViewPDFs;
    private PDFAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_pdf);

        recyclerViewPDFs = findViewById(R.id.recyclerViewPDFs);
        recyclerViewPDFs.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PDFAdapter(new ArrayList<>(), this);
        recyclerViewPDFs.setAdapter(adapter);

        fetchPDFsFromFirebase();

        // Setup chat button
        FloatingActionButton fabChatbot = findViewById(R.id.fab_chatbot);
        fabChatbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChatActivity();
            }
        });
    }

    private void openChatActivity() {
        Intent intent = new Intent(this, ChatBot.class);
        startActivity(intent);
    }

    private void fetchPDFsFromFirebase() {
        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference[] storageRefs = {
                storage.getReference().child("/form1/sciences/mathematics/pdfs/"),
                storage.getReference().child("/form1/sciences/biology/pdfs/"),
                storage.getReference().child("/form1/sciences/agriculture/pdfs/"),
                storage.getReference().child("/form1/sciences/chemistry/pdfs/"),
                storage.getReference().child("/form1/sciences/physics/pdfs/"),
                storage.getReference().child("/form1/languages/english/pdfs/"),
                storage.getReference().child("/form1/languages/chichewa/pdfs/"),
                storage.getReference().child("/form1/humanities/social_studies/pdfs/"),
                storage.getReference().child("/form1/humanities/history/pdfs/"),
                storage.getReference().child("/form1/humanities/life_skills/pdfs/"),
                storage.getReference().child("/form1/humanities/bible_knowledge/pdfs/"),
                storage.getReference().child("/form1/humanities/geography/pdfs/")
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
