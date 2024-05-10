package com.example.teachandlearn.Teacher.Form4;

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
import android.Manifest;
import com.example.teachandlearn.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

public class TeacherForm4Uploads extends AppCompatActivity {

    private static final int REQUEST_PICK_PDF = 1;
    private static final int REQUEST_PICK_AUDIO = 2;
    private static final int REQUEST_PICK_VIDEO = 3;
    private static final int REQUEST_PICK_QUESTION = 4;
    private static final int PERMISSION_REQUEST_READ_STORAGE = 100;

    private Uri selectedPdfUri;
    private Uri selectedAudioUri;
    private Uri selectedVideoUri;
    private Uri selectedQuestionUri;

    private FirebaseStorage storage;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form4_uploads);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        Button pdfButton = findViewById(R.id.button_pdf);
        Button audioButton = findViewById(R.id.button_audio);
        Button videoButton = findViewById(R.id.button_videos);
        Button questionsButton = findViewById(R.id.button_tests_quizzes);

        pdfButton.setOnClickListener(v -> openFilePicker("application/pdf", REQUEST_PICK_PDF));
        audioButton.setOnClickListener(v -> openFilePicker("audio/*", REQUEST_PICK_AUDIO));
        videoButton.setOnClickListener(v -> openFilePicker("video/*", REQUEST_PICK_VIDEO));
        questionsButton.setOnClickListener(v -> openFilePicker("*/*", REQUEST_PICK_QUESTION));

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
            Uri fileUri = data.getData();
            String fileType = getFileExtension(fileUri);
            if (fileType.isEmpty()) fileType = "unknown"; // Default file type if not determined
            uploadFileWithParcelDescriptor(fileUri, fileType);
        }
    }

    private void uploadFileWithParcelDescriptor(Uri fileUri, String fileType) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        String fileName = UUID.randomUUID().toString() + "." + fileType;
        StorageReference fileRef = storageReference.child("uploads/" + fileName);

        try (ParcelFileDescriptor pfd = getContentResolver().openFileDescriptor(fileUri, "r")) {
            if (pfd != null) {
                InputStream stream = new FileInputStream(pfd.getFileDescriptor());
                UploadTask uploadTask = fileRef.putStream(stream);
                uploadTask.addOnSuccessListener(taskSnapshot -> fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    progressDialog.dismiss();
                    showToast("File uploaded successfully");
                    writeMetadataToDatabase(fileName, uri.toString(), fileType);
                })).addOnFailureListener(e -> {
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

    private String getFileExtension(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    int lastDot = result.lastIndexOf('.');
                    if (lastDot != -1) {
                        result = result.substring(lastDot + 1);
                    } else {
                        result = "unknown"; // Handle files without an extension
                    }
                }
            }
        }
        return result;
    }

    private void writeMetadataToDatabase(String fileName, String fileUrl, String fileType) {
        String key = databaseReference.child("uploads").push().getKey();
        if (key != null) {
            UploadFile uploadFile = new UploadFile(fileName, fileUrl, fileType);
            databaseReference.child("uploads").child(key).setValue(uploadFile)
                    .addOnSuccessListener(aVoid -> showToast("File metadata saved successfully"))
                    .addOnFailureListener(e -> showToast("Failed to save file metadata: " + e.getMessage()));
        }
    }

    private class UploadFile {
        public String name, url, type;

        public UploadFile(String name, String url, String type) {
            this.name = name;
            this.url = url;
            this.type = type;
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
