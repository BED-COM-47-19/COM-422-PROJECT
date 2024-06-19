
package com.example.teachandlearn.Student.Form4.Documents.Biology;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.R;

public class Form4PDFViewerBiology extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_pdf_item); // Ensure this layout has appropriate content

        String filePath = getIntent().getStringExtra("filePath");
        Uri pdfUri = Uri.parse(filePath);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(pdfUri, "application/pdf");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "No Application Available to View PDF", Toast.LENGTH_SHORT).show();
        }
    }
}
