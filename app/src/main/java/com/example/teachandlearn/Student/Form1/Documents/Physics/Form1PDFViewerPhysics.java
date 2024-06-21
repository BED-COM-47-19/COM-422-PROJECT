package com.example.teachandlearn.Student.Form1.Documents.Physics;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.R;

public class Form1PDFViewerPhysics extends AppCompatActivity {

    private static final String TAG = "Form1PDFViewerPhysics";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_pdf_item); // Ensure this layout has appropriate content

        // Get filePath from Intent
        String filePath = getIntent().getStringExtra("filePath");
        Log.d(TAG, "Received filePath: " + filePath);

        // Check if filePath is not null or empty
        if (filePath != null && !filePath.isEmpty()) {
            try {
                Uri pdfUri = Uri.parse(filePath);

                // Intent to view PDF
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(pdfUri, "application/pdf");
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                // Verify there's an app to handle this intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "No Application Available to View PDF", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Error opening PDF: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Error opening PDF", e);
            }
        } else {
            Toast.makeText(this, "Invalid File Path", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Invalid File Path: " + filePath);
        }
    }
}
