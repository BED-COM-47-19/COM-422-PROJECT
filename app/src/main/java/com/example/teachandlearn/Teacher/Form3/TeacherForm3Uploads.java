
package com.example.teachandlearn.Teacher.Form3;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import java.util.UUID;

public class TeacherForm3Uploads extends AppCompatActivity {

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
    private ImageButton buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form3_uploads);

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
        buttonBack.setOnClickListener(v -> onBackPressed());


        pdfButton.setOnClickListener(v -> checkPermissionAndPickFile("application/pdf", REQUEST_PICK_PDF));
        audioButton.setOnClickListener(v -> checkPermissionAndPickFile("audio/*", REQUEST_PICK_AUDIO));
        videoButton.setOnClickListener(v -> checkPermissionAndPickFile("video/*", REQUEST_PICK_VIDEO));
        questionsButton.setOnClickListener(v -> checkPermissionAndPickFile("*/*", REQUEST_PICK_QUESTION));


    }

    private void checkPermissionAndPickFile(String mimeType, int requestCode) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
        } else {
            openFilePicker(mimeType, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Call openFilePicker or handle permission granted scenario
        } else {
            Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
        }
    }


    private void openFilePicker(String mimeType, int requestCode) {
        // Create an intent for picking data from the device storage using ACTION_OPEN_DOCUMENT
        Intent intentStorage = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intentStorage.addCategory(Intent.CATEGORY_OPENABLE);
        intentStorage.setType(mimeType);
        intentStorage.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);

        // Create an intent for picking data from all sources including cloud services using ACTION_GET_CONTENT
        Intent intentAllSources = new Intent(Intent.ACTION_GET_CONTENT);
        intentAllSources.addCategory(Intent.CATEGORY_OPENABLE);
        intentAllSources.setType(mimeType);
        intentAllSources.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        // Create a chooser intent to let the user select which source to use (local or cloud)
        Intent chooser = Intent.createChooser(intentAllSources, "Select File");
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] { intentStorage });

        startActivityForResult(chooser, requestCode);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedFileUri = data.getData();
            showCommentDialog(selectedFileUri, requestCode);
        }
    }

    private void showCommentDialog(Uri fileUri, int requestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add a Comment");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Upload", (dialog, which) -> {
            String comment = input.getText().toString();
            uploadFileWithComment(fileUri, comment, requestCode);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void uploadFileWithComment(Uri fileUri, String comment, int requestCode) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        String fileCategory = "uploads/";
        switch (requestCode) {
            case REQUEST_PICK_PDF: fileCategory += "pdfs/"; break;
            case REQUEST_PICK_AUDIO: fileCategory += "audios/"; break;
            case REQUEST_PICK_VIDEO: fileCategory += "videos/"; break;
            case REQUEST_PICK_QUESTION: fileCategory += "questions/"; break;
        }

        String fileName = UUID.randomUUID().toString() + "_commented";
        StorageReference fileRef = storageReference.child(fileCategory + fileName);

        StorageMetadata metadata = new StorageMetadata.Builder()
                .setCustomMetadata("comment", comment)
                .build();

        fileRef.putFile(fileUri, metadata)
                .addOnSuccessListener(taskSnapshot -> {
                    progressDialog.dismiss();
                    showToast("File uploaded successfully with comment: " + comment);
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

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }

}
