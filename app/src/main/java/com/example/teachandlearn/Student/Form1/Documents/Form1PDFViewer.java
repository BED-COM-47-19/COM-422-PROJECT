

package com.example.teachandlearn.Student.Form1.Documents;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.github.barteksc.pdfviewer.PDFView;



public class Form1PDFViewer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_pdf_item);

        PDFView pdfView = findViewById(R.id.pdfView);
        String filePath = getIntent().getStringExtra("filePath");

        pdfView.fromUri(Uri.parse(filePath))
                .load();
    }
}
