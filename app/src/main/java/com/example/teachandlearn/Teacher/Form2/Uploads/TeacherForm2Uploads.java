

package com.example.teachandlearn.Teacher.Form2.Uploads;
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


public class TeacherForm2Uploads extends AppCompatActivity {

    private Button buttonBack;

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
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logic for when the back button is pressed
                onBackPressed();
            }
        });


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
                    String pdfDestination = getSubjectPdfDestination();
                    uploadFile(selectedFileUri, pdfDestination, "pdfs", new String[]{"pdf", "docx", "pptx"}, "Please select a PDF, DOCX, or PPTX file.");
                    showToast("PDF Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_AUDIO:
                    String audioDestination = getSubjectAudioDestination();
                    uploadFile(selectedFileUri, audioDestination, "audios", new String[]{"mp3", "WAV"}, "Please select an MP3 file.");
                    showToast("Audio Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_VIDEO:
                    String videoDestination = getSubjectVideoDestination();
                    uploadFile(selectedFileUri, videoDestination, "videos", new String[]{"mp4", "AVI", "MKV", "WMV", "MOV"}, "Please Select Video format.");
                    showToast("Video Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_QUESTION:
                    String questionDestination = getSubjectQuestionDestination();
                    uploadFile(selectedFileUri, questionDestination, "quizzes_and_questions", new String[]{}, "No restriction on question formats.");
                    showToast("Question Selected: " + selectedFileUri.toString());
                    break;
            }
        }
    }

    private String getSubjectPdfDestination() {
        // Determine the destination based on the subject
        switch (selectedSubject) {
            case "Bible Knowledge":
                return "/form2/humanities/bible_knowledge/pdfs/";
            case "Geography":
                return "/form2/humanities/geography/pdfs/";
            case "History":
                return "/form2/humanities/history/pdfs/";
            case "Life Skills":
                return "/form2/humanities/life_skills/pdfs/";
            case "Social Studies":
                return "/form2/humanities/social_studies/pdfs/";
            case "English":
                return "/form2/languages/english/pdfs/";
            case "Chichewa":
                return "/form2/languages/chichewa/pdfs/";
            case "Agriculture":
                return "/form2/sciences/agriculture/pdfs/";
            case "Biology":
                return "/form2/sciences/biology/pdfs/";
            case "Chemistry":
                return "/form2/sciences/chemistry/pdfs/";
            case "Mathematics":
                return "/form2/sciences/mathematics/pdfs/";
            case "Physics":
                return "/form2/sciences/physics/pdfs/";
            default:
                return "";
        }
    }

    private String getSubjectAudioDestination() {
        // Determine the destination based on the subject
        switch (selectedSubject) {
            case "Bible Knowledge":
                return "/form2/humanities/bible_knowledge/audios/";
            case "Geography":
                return "/form2/humanities/geography/audios/";
            case "History":
                return "/form2/humanities/history/audios/";
            case "Life Skills":
                return "/form2/humanities/life_skills/audios/";
            case "Social Studies":
                return "/form2/humanities/social_studies/audios/";
            case "English":
                return "/form2/languages/english/audios/";
            case "Chichewa":
                return "/form2/languages/chichewa/audios/";
            case "Agriculture":
                return "/form2/sciences/agriculture/audios/";
            case "Biology":
                return "/form2/sciences/biology/audios/";
            case "Chemistry":
                return "/form2/sciences/chemistry/audios/";
            case "Mathematics":
                return "/form2/sciences/mathematics/audios/";
            case "Physics":
                return "/form2/sciences/physics/audios/";
            default:
                return "";
        }
    }

    private String getSubjectVideoDestination() {
        // Determine the destination based on the subject
        switch (selectedSubject) {
            case "Bible Knowledge":
                return "/form2/humanities/bible_knowledge/videos/";
            case "Geography":
                return "/form2/humanities/geography/videos/";
            case "History":
                return "/form2/humanities/history/videos/";
            case "Life Skills":
                return "/form2/humanities/life_skills/videos/";
            case "Social Studies":
                return "/form2/humanities/social_studies/videos/";
            case "English":
                return "/form2/languages/english/videos/";
            case "Chichewa":
                return "/form2/languages/chichewa/videos/";
            case "Agriculture":
                return "/form2/sciences/agriculture/videos/";
            case "Biology":
                return "/form2/sciences/biology/videos/";
            case "Chemistry":
                return "/form2/sciences/chemistry/videos/";
            case "Mathematics":
                return "/form2/sciences/mathematics/videos/";
            case "Physics":
                return "/form2/sciences/physics/videos/";
            default:
                return "";
        }
    }

    private String getSubjectQuestionDestination() {
        // Determine the destination based on the subject
        switch (selectedSubject) {
            case "Bible Knowledge":
                return "/form2/humanities/bible_knowledge/quizzes_and_questions/";
            case "Geography":
                return "/form2/humanities/geography/quizzes_and_questions/";
            case "History":
                return "/form2/humanities/history/quizzes_and_questions/";
            case "Life Skills":
                return "/form2/humanities/life_skills/quizzes_and_questions/";
            case "Social Studies":
                return "/form2/humanities/social_studies/quizzes_and_questions/";
            case "English":
                return "/form2/languages/english/quizzes_and_questions/";
            case "Chichewa":
                return "/form2/languages/chichewa/quizzes_and_questions/";
            case "Agriculture":
                return "/form2/sciences/agriculture/quizzes_and_questions/";
            case "Biology":
                return "/form2/sciences/biology/quizzes_and_questions/";
            case "Chemistry":
                return "/form2/sciences/chemistry/quizzes_and_questions/";
            case "Mathematics":
                return "/form2/sciences/mathematics/quizzes_and_questions/";
            case "Physics":
                return "/form2/sciences/physics/quizzes_and_questions/";
            default:
                return "";
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

                        fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                            String fileUrl = uri.toString();
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


    @Override
    public void onBackPressed() {
        // Handle the back button action
        super.onBackPressed();
        // You can also add custom logic here if needed
    }

}
