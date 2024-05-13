<<<<<<< HEAD
package com.example.teachandlearn.Teacher.Form1.Uploads;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeacherForm1Uploads extends AppCompatActivity {
    private Button buttonBack;

=======

package com.example.teachandlearn.Teacher.Form1.Uploads;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
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


public class TeacherForm1Uploads extends AppCompatActivity {

    //    private ImageButton buttonBack;
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
    private static final int REQUEST_PICK_PDF = 1;
    private static final int REQUEST_PICK_AUDIO = 2;
    private static final int REQUEST_PICK_VIDEO = 3;
    private static final int REQUEST_PICK_QUESTION = 4;

<<<<<<< HEAD
=======
    private Uri selectedPdfUri;
    private Uri selectedAudioUri;
    private Uri selectedVideoUri;
    private Uri selectedQuestionUri;

>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private ProgressDialog progressDialog;

    private static final String TAG = "TeacherForm1Uploads";

<<<<<<< HEAD
=======

>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
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
<<<<<<< HEAD
=======
//        buttonBack = findViewById(R.id.back_button);
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f

        pdfButton.setOnClickListener(v -> openFilePicker("application/pdf", REQUEST_PICK_PDF));
        audioButton.setOnClickListener(v -> openFilePicker("audio/*", REQUEST_PICK_AUDIO));
        videoButton.setOnClickListener(v -> openFilePicker("video/*", REQUEST_PICK_VIDEO));
        questionsButton.setOnClickListener(v -> openFilePicker("*/*", REQUEST_PICK_QUESTION));

<<<<<<< HEAD
        buttonBack = findViewById(R.id.back_button);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logic for when the back button is pressed
                onBackPressed();
            }
        });
=======
//        buttonBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Logic for when the back button is pressed
//                onBackPressed();
//            }
//        });

>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
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
                    uploadFile(selectedFileUri, "form1/pdfs/", "pdfs", new String[]{"pdf", "docx", "pptx"}, "Please select a PDF, DOCX, or PPTX file.");
                    showToast("PDF Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_AUDIO:
                    uploadFile(selectedFileUri, "form1/audios/", "audio", new String[]{"mp3", "WAV"}, "Please select an MP3 file.");
                    showToast("Audio Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_VIDEO:
                    uploadFile(selectedFileUri, "form1/videos/", "videos", new String[]{"mp4", "AVI", "MKV", "WMV" , "MOV"}, "Please Select Vedio format.");
                    showToast("Video Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_QUESTION:
                    uploadFile(selectedFileUri, "form1/quizzes_and_questions/", "questions", new String[]{}, "No restriction on question formats.");
                    showToast("Question Selected: " + selectedFileUri.toString());
                    break;
            }
        }
    }

<<<<<<< HEAD
=======




    // Inside your uploadFile method after uploading the file
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
    private void uploadFile(Uri fileUri, String storagePath, String firestoreCollection, String[] allowedExtensions, String errorMessage) {
        if (fileUri != null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            String fileName = UUID.randomUUID().toString();
<<<<<<< HEAD
            String fileExtension = getFileExtension(fileUri);

=======
            // Get the file extension
            String fileExtension = getFileExtension(fileUri);

            // Check if the file extension is allowed
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
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

<<<<<<< HEAD
=======
            // Continue with the upload process if the extension is allowed
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
            StorageReference fileRef = storageReference.child(storagePath + fileName);

            fileRef.putFile(fileUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        progressDialog.dismiss();
                        showToast("File uploaded successfully");

<<<<<<< HEAD
                        fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                            String fileUrl = uri.toString();
=======
                        // Retrieve the download URL after successful upload
                        fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                            String fileUrl = uri.toString();
                            // Save fileUrl to Firestore or another database
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
                            saveFileUrlToFirestore(fileUrl, firestoreCollection);
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

<<<<<<< HEAD
=======
    // Method to get the file extension
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

<<<<<<< HEAD
    private void saveFileUrlToFirestore(String fileUrl, String firestoreCollection) {
=======


    // Method to save the file URL to Firestore
    private void saveFileUrlToFirestore(String fileUrl) {
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> fileData = new HashMap<>();
        fileData.put("fileUrl", fileUrl);

<<<<<<< HEAD
        db.collection(firestoreCollection)
=======
        db.collection("files")
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
                .add(fileData)
                .addOnSuccessListener(documentReference -> {
                    Log.d(TAG, "File URL added with ID: " + documentReference.getId());
                })
                .addOnFailureListener(e -> {
                    Log.w(TAG, "Error adding file URL", e);
                });
    }

<<<<<<< HEAD
=======

>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

<<<<<<< HEAD
    @Override
    public void onBackPressed() {
        // Handle the back button action
        super.onBackPressed();
        // You can also add custom logic here if needed
    }
=======
//    @Override
//    public void onBackPressed() {
//        // Handle the back button action
//        super.onBackPressed();
//        // You can also add custom logic here if needed
//    }
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f

}
