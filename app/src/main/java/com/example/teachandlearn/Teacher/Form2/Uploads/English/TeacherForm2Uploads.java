
package com.example.teachandlearn.Teacher.Form2.Uploads.English;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class TeacherForm2Uploads extends AppCompatActivity {

    private Button buttonBack, buttonViewProgress;
    private static final int REQUEST_PICK_PDF = 1;
    private static final int REQUEST_PICK_AUDIO = 2;
    private static final int REQUEST_PICK_VIDEO = 3;
    private static final int REQUEST_PICK_QUESTION = 4;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private ProgressDialog progressDialog;
    private static final String TAG = "TeacherForm2Uploads";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form1_uploads);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        Button pdfButton = findViewById(R.id.button_pdf);
        Button audioButton = findViewById(R.id.button_audio);
        Button videoButton = findViewById(R.id.button_videos);
        Button questionsButton = findViewById(R.id.button_tests_quizzes);

        pdfButton.setOnClickListener(v -> openFilePicker("application/pdf", REQUEST_PICK_PDF));
        audioButton.setOnClickListener(v -> openFilePicker("audio/*", REQUEST_PICK_AUDIO));
        videoButton.setOnClickListener(v -> openFilePicker("video/*", REQUEST_PICK_VIDEO));
        questionsButton.setOnClickListener(v -> openFilePicker("*/*", REQUEST_PICK_QUESTION));

        buttonBack = findViewById(R.id.back_button);
        buttonBack.setOnClickListener(view -> onBackPressed());

        buttonViewProgress = findViewById(R.id.button_view_progress);

    }

    private void openFilePicker(String mimeType, int requestCode) {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(mimeType);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select File"), requestCode);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedFileUri = data.getData();

            switch (requestCode) {

                case REQUEST_PICK_PDF:
                    uploadFile(selectedFileUri, "/form2/languages/english/pdfs/", "pdfs", new String[]{"pdf", "docx", "pptx"}, "Please select a PDF, DOCX, or PPTX file.");
                    break;
                case REQUEST_PICK_AUDIO:
                    uploadFile(selectedFileUri, "/form2/languages/english/audios/", "audio", new String[]{"mp3", "WAV"}, "Please select an MP3 file.");
                    break;
                case REQUEST_PICK_VIDEO:
                    uploadFile(selectedFileUri, "/form2/languages/english/videos/", "videos", new String[]{"mp4", "AVI", "MKV", "WMV", "MOV"}, "Please Select Video format.");
                    break;
                case REQUEST_PICK_QUESTION:
                    uploadFile(selectedFileUri, "/form2/languages/english/quizzes_and_questions/", "questions", new String[]{"pdf", "docx", "pptx"}, "No restriction on question formats.");
                    break;
                default:
                    // Handle other cases if needed
                    break;

            }

        }


    }

    private void uploadFile(Uri fileUri, String storagePath, String firestoreCollection, String[] allowedExtensions, String errorMessage) {

        if (fileUri != null) {

            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            String fileName = UUID.randomUUID().toString();
            String fileExtension = getFileExtension(fileUri);

            boolean isExtensionAllowed = false;

            for (String extension : allowedExtensions) {

                if (fileExtension != null && fileExtension.equalsIgnoreCase(extension)) {
                    isExtensionAllowed = true;
                    break;

                }


            }


            if (!isExtensionAllowed) {

                progressDialog.dismiss();
                showToast(errorMessage);
                return;

            }

            StorageReference fileRef = storageReference.child(storagePath + fileName);

            fileRef.putFile(fileUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        progressDialog.dismiss();
                        showToast("File uploaded successfully");
                        fileRef.getDownloadUrl().addOnSuccessListener(uri -> saveFileUrlToFirestore(uri.toString(), firestoreCollection));
                    })
                    .addOnFailureListener(e -> {
                        progressDialog.dismiss();
                        showToast("Failed to upload file: " + e.getMessage());
                    })
                    .addOnProgressListener(taskSnapshot -> progressDialog.setMessage("Uploaded " + (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount()) + "%"));

        }


    }

    private String getFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }

    private void saveFileUrlToFirestore(String fileUrl, String firestoreCollection) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> fileData = new HashMap<>();
        fileData.put("fileUrl", fileUrl);
        db.collection(firestoreCollection)
                .add(fileData)
                .addOnSuccessListener(documentReference -> Log.d(TAG, "File URL added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w(TAG, "Error adding file URL", e));

    }

    private void showToast(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    private void logStudentAccess(String accessedFile) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {

            String firstName = user.getDisplayName();
            String lastName = user.getDisplayName();
            String studentEmail = user.getEmail();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Map<String, Object> accessData = new HashMap<>();

            accessData.put("firstName", firstName);
            accessData.put("lastName", lastName);
            accessData.put("studentEmail", studentEmail);
            accessData.put("accessedFile", accessedFile);
            accessData.put("timestamp", System.currentTimeMillis()); // Adding timestamp
            db.collection("student_email")
                    .add(accessData)
                    .addOnSuccessListener(documentReference -> Log.d(TAG, "Access log added with ID: " + documentReference.getId()))
                    .addOnFailureListener(e -> Log.w(TAG, "Error adding access log", e));

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
