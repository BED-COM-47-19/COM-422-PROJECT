

package com.example.teachandlearn.Student.Documents;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.teachandlearn.R;
import java.util.ArrayList;
import java.util.List;



public class PDF extends AppCompatActivity {
    private RecyclerView recyclerViewPDFs;
    private PDFAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_pdf);

        recyclerViewPDFs = findViewById(R.id.recyclerViewPDFs);
        recyclerViewPDFs.setLayoutManager(new LinearLayoutManager(this));

        List<PDFDocument> pdfDocuments = fetchPDFs();
        adapter = new PDFAdapter(pdfDocuments);
        recyclerViewPDFs.setAdapter(adapter);
    }

    private List<PDFDocument> fetchPDFs() {
        List<PDFDocument> pdfs = new ArrayList<>();
        pdfs.add(new PDFDocument("Example PDF 1", "/path/to/pdf1.pdf"));
        pdfs.add(new PDFDocument("Example PDF 2", "/path/to/pdf2.pdf"));
        pdfs.add(new PDFDocument("Example PDF 3", "/path/to/pdf3.pdf"));
        return pdfs;
    }

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
