
package com.example.teachandlearn.Student.Form1.Documents;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.teachandlearn.CHATGPT.ChatBot;
import com.example.teachandlearn.R;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;
import java.util.List;


public class Form1PDF extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CourseAdapter courseAdapter;
    private List<CourseModule> courseModules;
    private PDFAdapter pdfAdapter;
    private Button resetProgressButton;
    private RecyclerView recyclerViewPDFs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_pdf);

        recyclerView = findViewById(R.id.recyclerView);
        resetProgressButton = findViewById(R.id.resetProgressButton);
        recyclerViewPDFs = findViewById(R.id.recyclerViewPDFs);

        courseModules = loadCourseModules();
        courseAdapter = new CourseAdapter(courseModules, this::openModule);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(courseAdapter);

        recyclerViewPDFs.setLayoutManager(new LinearLayoutManager(this));
        pdfAdapter = new PDFAdapter(new ArrayList<>(), this);
        recyclerViewPDFs.setAdapter(pdfAdapter);

        fetchPDFsFromFirebase();

        resetProgressButton.setOnClickListener(v -> resetProgress());

        Button chatbotButton = findViewById(R.id.chatbotButton);
        chatbotButton.setOnClickListener(v -> {
            EditText messageEditText = findViewById(R.id.messageEditText);
            String message = messageEditText.getText().toString().trim();
            if (!message.isEmpty()) {
                sendMessageToChatbot(message);
            }
        });
    }

    private List<CourseModule> loadCourseModules() {
        List<CourseModule> modules = new ArrayList<>();
        modules.add(new CourseModule("Introduction to Cybersecurity", getProgress("Introduction to Cybersecurity")));
        modules.add(new CourseModule("Types of Organizational Data", getProgress("Types of Organizational Data")));
        return modules;
    }

    private int getProgress(String moduleTitle) {
        return getSharedPreferences("CourseProgress", MODE_PRIVATE).getInt(moduleTitle, 0);
    }

    private void openModule(CourseModule module) {
        Intent intent = new Intent(this, Form1ModuleContent.class);
        intent.putExtra("moduleTitle", module.getTitle());
        startActivity(intent);
    }

    private void resetProgress() {
        getSharedPreferences("CourseProgress", MODE_PRIVATE).edit().clear().apply();
        for (CourseModule module : courseModules) {
            module.setProgress(0);
        }
        courseAdapter.notifyDataSetChanged();
    }

    private void sendMessageToChatbot(String message) {
        ChatBot.sendMessage(message, response -> {
            TextView chatbotResponseTextView = findViewById(R.id.chatbotResponseTextView);
            chatbotResponseTextView.setText(response);
        });
    }

    private void fetchPDFsFromFirebase() {
        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference storageRef1 = storage.getReference().child("/form1/sciences/mathematics/pdfs/");
        StorageReference storageRef2 = storage.getReference().child("/form1/sciences/biology/pdfs/");

        // Fetch PDFs from both references
        fetchPDFsFromReference(storageRef1);
        fetchPDFsFromReference(storageRef2);
    }

    private void fetchPDFsFromReference(StorageReference storageRef) {
        storageRef.listAll().addOnSuccessListener(listResult -> {
            List<PDFDocument> pdfs = new ArrayList<>();
            for (StorageReference item : listResult.getItems()) {
                item.getDownloadUrl().addOnSuccessListener(uri -> {
                    pdfs.add(new PDFDocument(item.getName(), uri.toString()));
                    pdfAdapter.setPDFDocuments(pdfs);
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

    private void showNoFilesUploaded() {
        pdfAdapter.setPDFDocuments(new ArrayList<>());
        Toast.makeText(this, "No file Uploaded", Toast.LENGTH_SHORT).show();
    }

    // CourseAdapter and PDFAdapter classes go here

    public static class CourseModule {
        private String title;
        private int progress;

        public CourseModule(String title, int progress) {
            this.title = title;
            this.progress = progress;
        }

        public String getTitle() {
            return title;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }
    }

    public static class PDFDocument {
        private String title;
        private String downloadUrl;

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

    public static class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

        private List<CourseModule> courseModules;
        private OnItemClickListener listener;

        public interface OnItemClickListener {
            void onItemClick(CourseModule module);
        }

        public CourseAdapter(List<CourseModule> courseModules, OnItemClickListener listener) {
            this.courseModules = courseModules;
            this.listener = listener;
        }

        @NonNull
        @Override
        public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_form1_item_module, parent, false);
            return new CourseViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
            CourseModule module = courseModules.get(position);
            holder.bind(module, listener);
        }

        @Override
        public int getItemCount() {
            return courseModules.size();
        }

        public static class CourseViewHolder extends RecyclerView.ViewHolder {
            private TextView titleTextView;
            private TextView progressTextView;

            public CourseViewHolder(@NonNull View itemView) {
                super(itemView);
                titleTextView = itemView.findViewById(R.id.moduleTitle);
                progressTextView = itemView.findViewById(R.id.moduleProgress);
            }

            public void bind(final CourseModule module, final OnItemClickListener listener) {
                if (titleTextView != null) {
                    titleTextView.setText(module.getTitle());
                }
                if (progressTextView != null) {
                    progressTextView.setText(module.getProgress() + "%");
                }
                itemView.setOnClickListener(v -> listener.onItemClick(module));
            }

        }
    }

    public static class PDFAdapter extends RecyclerView.Adapter<PDFAdapter.PDFViewHolder> {
        private List<PDFDocument> pdfDocuments;
        private Context context;

        public PDFAdapter(List<PDFDocument> pdfDocuments, Context context) {
            this.pdfDocuments = pdfDocuments;
            this.context = context;
        }

        @Override
        public PDFViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_form1_pdf_item, parent, false);
            return new PDFViewHolder(view);
        }

        @Override
        public void onBindViewHolder(PDFViewHolder holder, int position) {
            PDFDocument document = pdfDocuments.get(position);
            holder.textViewTitle.setText(document.getTitle());
            holder.itemView.setOnClickListener(v -> {
                downloadAndOpenPDF(document.getDownloadUrl());
            });
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
