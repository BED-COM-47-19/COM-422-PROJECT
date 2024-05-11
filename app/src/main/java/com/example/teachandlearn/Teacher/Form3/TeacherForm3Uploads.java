

package com.example.teachandlearn.Teacher.Form3;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.teachandlearn.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;


public class TeacherForm3Uploads extends AppCompatActivity {

    private static final int REQUEST_PICK_PDF = 1;
    private static final int PERMISSION_REQUEST_READ_STORAGE = 100;

    private Uri selectedPdfUri;

    private FirebaseStorage storage;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form3_uploads);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        Button pdfButton = findViewById(R.id.button_pdf);
        pdfButton.setOnClickListener(v -> openFilePicker("application/pdf", REQUEST_PICK_PDF));

        checkStoragePermission();
    }

    private void checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_READ_STORAGE);
        } else {
            showToast("Storage permission already granted.");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_READ_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showToast("Permission granted");
            } else {
                showToast("Permission denied. Cannot access storage.");
            }
        }
    }

    private void openFilePicker(String mimeType, int requestCode) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType(mimeType);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(Intent.createChooser(intent, "Select File"), requestCode);
        } else {
            showToast("Storage permission is required to access files.");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedPdfUri = data.getData();
            uploadPdfFile(selectedPdfUri);
        }
    }

    private void uploadPdfFile(Uri fileUri) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        String fileName = getFileName(fileUri);
        String fileType = "pdf"; // You can change this if you have different file types

        StorageReference fileRef = storageReference.child("form3_uploads").child(fileName);

        try {
            ParcelFileDescriptor pfd = getContentResolver().openFileDescriptor(fileUri, "r");
            if (pfd != null) {
                InputStream stream = new FileInputStream(pfd.getFileDescriptor());
                UploadTask uploadTask = fileRef.putStream(stream);
                uploadTask.addOnSuccessListener(taskSnapshot -> {
                    progressDialog.dismiss();
                    showToast("File uploaded successfully");
                    // Get the download URL for the uploaded file
                    fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        // Save file metadata to the Realtime Database
                        String downloadUrl = uri.toString();
                        String fileId = UUID.randomUUID().toString(); // Generate unique ID for the file
                        UploadFile upload = new UploadFile(fileName, downloadUrl, fileType);
                        databaseReference.child("form4_uploads").child(fileId).setValue(upload)
                                .addOnSuccessListener(aVoid -> showToast("File metadata saved successfully"))
                                .addOnFailureListener(e -> showToast("Failed to save file metadata: " + e.getMessage()));
                    }).addOnFailureListener(exception -> {
                        progressDialog.dismiss();
                        showToast("Failed to get download URL: " + exception.getMessage());
                    });
                }).addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    showToast("Failed to upload file: " + e.getMessage());
                }).addOnProgressListener(taskSnapshot -> {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage("Uploaded " + (int) progress + "%");
                });
            }
        } catch (Exception e) {
            progressDialog.dismiss();
            showToast("Error uploading file: " + e.getMessage());
        }
    }

    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme() != null && uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } finally {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getLastPathSegment();
        }
        return result;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private static class UploadFile {
        public String name, url, type;

        public UploadFile(String name, String url, String type) {
            this.name = name;
            this.url = url;
            this.type = type;
        }
    }
}
