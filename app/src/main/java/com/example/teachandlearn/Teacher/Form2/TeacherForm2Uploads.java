
package com.example.teachandlearn.Teacher.Form2;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.UUID;
import android.util.Log;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import android.util.Log;


public class TeacherForm2Uploads extends AppCompatActivity {

    //    private ImageButton buttonBack;
    private static final int REQUEST_PICK_PDF = 1;
    private static final int REQUEST_PICK_AUDIO = 2;
    private static final int REQUEST_PICK_VIDEO = 3;
    private static final int REQUEST_PICK_QUESTION = 4;

    private Uri selectedPdfUri;
    private Uri selectedAudioUri;
    private Uri selectedVideoUri;
    private Uri selectedQuestionUri;

    private FirebaseStorage storage;
    private StorageReference storageReference;
    private ProgressDialog progressDialog;

    private static final String TAG = "TeacherForm2Uploads";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form2_uploads);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        Button pdfButton = findViewById(R.id.button_pdf);
        Button audioButton = findViewById(R.id.button_audio);
        Button videoButton = findViewById(R.id.button_videos);
        Button questionsButton = findViewById(R.id.button_tests_quizzes);
//        buttonBack = findViewById(R.id.back_button);

        pdfButton.setOnClickListener(v -> openFilePicker("application/pdf", REQUEST_PICK_PDF));
        audioButton.setOnClickListener(v -> openFilePicker("audio/*", REQUEST_PICK_AUDIO));
        videoButton.setOnClickListener(v -> openFilePicker("video/*", REQUEST_PICK_VIDEO));
        questionsButton.setOnClickListener(v -> openFilePicker("*/*", REQUEST_PICK_QUESTION));

//        buttonBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Logic for when the back button is pressed
//                onBackPressed();
//            }
//        });

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
                    selectedPdfUri = selectedFileUri;
                    uploadFile(selectedPdfUri);
                    showToast("PDF Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_AUDIO:
                    selectedAudioUri = selectedFileUri;
                    uploadFile(selectedAudioUri);
                    showToast("Audio Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_VIDEO:
                    selectedVideoUri = selectedFileUri;
                    uploadFile(selectedVideoUri);
                    showToast("Video Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_QUESTION:
                    selectedQuestionUri = selectedFileUri;
                    uploadFile(selectedQuestionUri);
                    showToast("Question Selected: " + selectedFileUri.toString());
                    break;
            }
        }
    }

    // Inside your uploadFile method after uploading the file
    private void uploadFile(Uri fileUri) {
        if (fileUri != null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            String fileName = UUID.randomUUID().toString();
            StorageReference fileRef = storageReference.child("form2/pdfs/" + fileName);

            fileRef.putFile(fileUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        progressDialog.dismiss();
                        showToast("File uploaded successfully");

                        // Retrieve the download URL after successful upload
                        fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                            String fileUrl = uri.toString();
                            // Save fileUrl to Firestore or another database
                            saveFileUrlToFirestore(fileUrl);
                        });
                    })
                    .addOnFailureListener(e -> {
                        progressDialog.dismiss();
                        showToast("Failed to upload file: " + e.getMessage());
                    })
                    .addOnProgressListener(taskSnapshot -> {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        progressDialog.setMessage("Uploaded " + (int) progress + "%");
                    });
        }
    }

    // Method to save the file URL to Firestore
    private void saveFileUrlToFirestore(String fileUrl) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> fileData = new HashMap<>();
        fileData.put("fileUrl", fileUrl);

        db.collection("files")
                .add(fileData)
                .addOnSuccessListener(documentReference -> {
                    Log.d(TAG, "File URL added with ID: " + documentReference.getId());
                })
                .addOnFailureListener(e -> {
                    Log.w(TAG, "Error adding file URL", e);
                });
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onBackPressed() {
//        // Handle the back button action
//        super.onBackPressed();
//        // You can also add custom logic here if needed
//    }

}
