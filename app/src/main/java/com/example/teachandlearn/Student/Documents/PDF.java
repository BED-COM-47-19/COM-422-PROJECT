
package com.example.teachandlearn.Student.Documents;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.example.teachandlearn.R;

public class PDF extends AppCompatActivity {
    private RecyclerView recyclerViewPDFs;
    private PDFAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_pdf);

        recyclerViewPDFs = findViewById(R.id.recyclerViewPDFs);
        recyclerViewPDFs.setLayoutManager(new LinearLayoutManager(this));

        List<PDFDocument> pdfDocuments = loadPDFsFromAssets();
        adapter = new PDFAdapter(pdfDocuments);
        recyclerViewPDFs.setAdapter(adapter);
    }

    private List<PDFDocument> loadPDFsFromAssets() {
        List<PDFDocument> pdfs = new ArrayList<>();
        try {
            // Get list of PDF files in the assets directory
            String[] files = getAssets().list("");
            for (String filename : files) {
                if (filename.endsWith(".pdf")) {
                    // Assuming PDF title is the filename without the .pdf extension
                    String title = filename.substring(0, filename.length() - 4);
                    pdfs.add(new PDFDocument(title, "file:///asset/books/Maths_book_3.pdf" + filename));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pdfs;
    }

    // Define the PDFDocument class
    public static class PDFDocument {
        private String title;
        private String filePath;

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
    }

    // Adapter for the RecyclerView
    private class PDFAdapter extends RecyclerView.Adapter<PDFAdapter.PDFViewHolder> {
        private List<PDFDocument> pdfDocuments;

        public PDFAdapter(List<PDFDocument> pdfDocuments) {
            this.pdfDocuments = pdfDocuments;
        }

        @Override
        public PDFViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_student_pdf_item, parent, false);
            return new PDFViewHolder(view);
        }

        @Override
        public void onBindViewHolder(PDFViewHolder holder, int position) {
            PDFDocument document = pdfDocuments.get(position);
            holder.textViewTitle.setText(document.getTitle());
        }

        @Override
        public int getItemCount() {
            return pdfDocuments.size();
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
