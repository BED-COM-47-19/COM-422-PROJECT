<<<<<<< HEAD
=======
<<<<<<< HEAD
package com.example.teachandlearn.Teacher.Form3.Uploads;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
=======

>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
package com.example.teachandlearn.Teacher.Form3.Uploads;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
<<<<<<< HEAD
=======
import android.widget.ImageButton;
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
<<<<<<< HEAD
import com.google.firebase.firestore.FirebaseFirestore;
=======
<<<<<<< HEAD
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class TeacherForm3Uploads extends AppCompatActivity {
    private Button buttonBack;

=======
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class TeacherForm3Uploads extends AppCompatActivity {
    private Button buttonBack;

<<<<<<< HEAD
=======
    //    private ImageButton buttonBack;
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
    private static final int REQUEST_PICK_PDF = 1;
    private static final int REQUEST_PICK_AUDIO = 2;
    private static final int REQUEST_PICK_VIDEO = 3;
    private static final int REQUEST_PICK_QUESTION = 4;

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
    private Uri selectedPdfUri;
    private Uri selectedAudioUri;
    private Uri selectedVideoUri;
    private Uri selectedQuestionUri;

>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private ProgressDialog progressDialog;

    private static final String TAG = "TeacherForm3Uploads";

<<<<<<< HEAD
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form1_uploads);
=======
<<<<<<< HEAD
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form1_uploads);
=======

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form3_uploads);
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        Button pdfButton = findViewById(R.id.button_pdf);
        Button audioButton = findViewById(R.id.button_audio);
        Button videoButton = findViewById(R.id.button_videos);
        Button questionsButton = findViewById(R.id.button_tests_quizzes);
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
//        buttonBack = findViewById(R.id.back_button);
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945

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
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
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
<<<<<<< HEAD


            //HUMANITIES SUBJECTS
=======
<<<<<<< HEAD
            switch (requestCode) {
                case REQUEST_PICK_PDF:
                    uploadFile(selectedFileUri, "form3/pdfs/", "pdfs", new String[]{"pdf", "docx", "pptx"}, "Please select a PDF, DOCX, or PPTX file.");
                    showToast("PDF Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_AUDIO:
                    uploadFile(selectedFileUri, "form3/audios/", "audio", new String[]{"mp3", "WAV"}, "Please select an MP3 file.");
                    showToast("Audio Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_VIDEO:
                    uploadFile(selectedFileUri, "form3/videos/", "videos", new String[]{"mp4", "AVI", "MKV", "WMV" , "MOV"}, "Please Select Video format.");
                    showToast("Video Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_QUESTION:
                    uploadFile(selectedFileUri, "form3/quizzes_and_questions/", "questions", new String[]{}, "No restriction on question formats.");
=======
            //FORM 4
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945

            switch (requestCode) {
                case REQUEST_PICK_PDF:
                    uploadFile(selectedFileUri, "/form3/humanities/bible_knowledge/pdfs/", "pdfs", new String[]{"pdf", "docx", "pptx"}, "Please select a PDF, DOCX, or PPTX file.");
                    showToast("PDF Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_AUDIO:
                    uploadFile(selectedFileUri, "/form3/humanities/bible_knowledge/audios/", "audio", new String[]{"mp3", "WAV"}, "Please select an MP3 file.");
                    showToast("Audio Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_VIDEO:
                    uploadFile(selectedFileUri, "/form3/humanities/bible_knowledge/videos/", "videos", new String[]{"mp4", "AVI", "MKV", "WMV" , "MOV"}, "Please Select Video format.");
                    showToast("Video Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_QUESTION:
<<<<<<< HEAD
                    uploadFile(selectedFileUri, "/form3/humanities/bible_knowledge/quizzes_and_questions/", "questions", new String[]{}, "No restriction on question formats.");
=======
                    selectedQuestionUri = selectedFileUri;
                    uploadFile(selectedQuestionUri, "form4/quizzes_and_questions/", "questions");
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
                    showToast("Question Selected: " + selectedFileUri.toString());
                    break;
            }

            switch (requestCode) {
                case REQUEST_PICK_PDF:
                    uploadFile(selectedFileUri, "/form3/humanities/geography/pdfs/", "pdfs", new String[]{"pdf", "docx", "pptx"}, "Please select a PDF, DOCX, or PPTX file.");
                    showToast("PDF Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_AUDIO:
                    uploadFile(selectedFileUri, "/form3/humanities/geography/audios/", "audio", new String[]{"mp3", "WAV"}, "Please select an MP3 file.");
                    showToast("Audio Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_VIDEO:
                    uploadFile(selectedFileUri, "/form3/humanities/geography/videos/", "videos", new String[]{"mp4", "AVI", "MKV", "WMV" , "MOV"}, "Please Select Video format.");
                    showToast("Video Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_QUESTION:
                    uploadFile(selectedFileUri, "/form3/humanities/geography/quizzes_and_questions/", "questions", new String[]{}, "No restriction on question formats.");
                    showToast("Question Selected: " + selectedFileUri.toString());
                    break;
            }

            switch (requestCode) {
                case REQUEST_PICK_PDF:
                    uploadFile(selectedFileUri, "/form3/humanities/history/pdfs/", "pdfs", new String[]{"pdf", "docx", "pptx"}, "Please select a PDF, DOCX, or PPTX file.");
                    showToast("PDF Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_AUDIO:
                    uploadFile(selectedFileUri, "/form3/humanities/history/audios/", "audio", new String[]{"mp3", "WAV"}, "Please select an MP3 file.");
                    showToast("Audio Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_VIDEO:
                    uploadFile(selectedFileUri, "/form3/humanities/history/videos/", "videos", new String[]{"mp4", "AVI", "MKV", "WMV" , "MOV"}, "Please Select Video format.");
                    showToast("Video Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_QUESTION:
                    uploadFile(selectedFileUri, "/form3/humanities/history/quizzes_and_questions/", "questions", new String[]{}, "No restriction on question formats.");
                    showToast("Question Selected: " + selectedFileUri.toString());
                    break;
            }

            switch (requestCode) {
                case REQUEST_PICK_PDF:
                    uploadFile(selectedFileUri, "/form3/humanities/life_skills/pdfs/", "pdfs", new String[]{"pdf", "docx", "pptx"}, "Please select a PDF, DOCX, or PPTX file.");
                    showToast("PDF Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_AUDIO:
                    uploadFile(selectedFileUri, "/form3/humanities/life_skills/audios/", "audio", new String[]{"mp3", "WAV"}, "Please select an MP3 file.");
                    showToast("Audio Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_VIDEO:
                    uploadFile(selectedFileUri, "/form3/humanities/life_skills/videos/", "videos", new String[]{"mp4", "AVI", "MKV", "WMV" , "MOV"}, "Please Select Video format.");
                    showToast("Video Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_QUESTION:
                    uploadFile(selectedFileUri, "/form3/humanities/life_skills/quizzes_and_questions/", "questions", new String[]{}, "No restriction on question formats.");
                    showToast("Question Selected: " + selectedFileUri.toString());
                    break;
            }

            switch (requestCode) {
                case REQUEST_PICK_PDF:
                    uploadFile(selectedFileUri, "/form3/humanities/social_studies/pdfs/", "pdfs", new String[]{"pdf", "docx", "pptx"}, "Please select a PDF, DOCX, or PPTX file.");
                    showToast("PDF Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_AUDIO:
                    uploadFile(selectedFileUri, "/form3/humanities/social_studies/audios/", "audio", new String[]{"mp3", "WAV"}, "Please select an MP3 file.");
                    showToast("Audio Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_VIDEO:
                    uploadFile(selectedFileUri, "/form3/humanities/social_studies/videos/", "videos", new String[]{"mp4", "AVI", "MKV", "WMV" , "MOV"}, "Please Select Video format.");
                    showToast("Video Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_QUESTION:
                    uploadFile(selectedFileUri, "/form3/humanities/social_studies/quizzes_and_questions/", "questions", new String[]{}, "No restriction on question formats.");
                    showToast("Question Selected: " + selectedFileUri.toString());
                    break;
            }


            //LANGUAGES SUBJECT

            switch (requestCode) {
                case REQUEST_PICK_PDF:
                    uploadFile(selectedFileUri, "/form3/languages/english/pdfs/", "pdfs", new String[]{"pdf", "docx", "pptx"}, "Please select a PDF, DOCX, or PPTX file.");
                    showToast("PDF Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_AUDIO:
                    uploadFile(selectedFileUri, "/form3/languages/english/audios/", "audio", new String[]{"mp3", "WAV"}, "Please select an MP3 file.");
                    showToast("Audio Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_VIDEO:
                    uploadFile(selectedFileUri, "/form3/languages/english/videos/", "videos", new String[]{"mp4", "AVI", "MKV", "WMV" , "MOV"}, "Please Select Video format.");
                    showToast("Video Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_QUESTION:
                    uploadFile(selectedFileUri, "/form3/languages/english/quizzes_and_questions/", "questions", new String[]{}, "No restriction on question formats.");
                    showToast("Question Selected: " + selectedFileUri.toString());
                    break;
            }

            switch (requestCode) {
                case REQUEST_PICK_PDF:
                    uploadFile(selectedFileUri, "/form3/languages/chichewa/pdfs/", "pdfs", new String[]{"pdf", "docx", "pptx"}, "Please select a PDF, DOCX, or PPTX file.");
                    showToast("PDF Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_AUDIO:
                    uploadFile(selectedFileUri, "/form3/languages/chichewa/audios/", "audio", new String[]{"mp3", "WAV"}, "Please select an MP3 file.");
                    showToast("Audio Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_VIDEO:
                    uploadFile(selectedFileUri, "/form3/languages/chichewa/videos/", "videos", new String[]{"mp4", "AVI", "MKV", "WMV" , "MOV"}, "Please Select Video format.");
                    showToast("Video Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_QUESTION:
                    uploadFile(selectedFileUri, "/form3/languages/chichewa/quizzes_and_questions/", "questions", new String[]{}, "No restriction on question formats.");
                    showToast("Question Selected: " + selectedFileUri.toString());
                    break;
            }

            //SCIENCE SUBJECTS

            switch (requestCode) {
                case REQUEST_PICK_PDF:
                    uploadFile(selectedFileUri, "/form3/sciences/agriculture/pdfs/", "pdfs", new String[]{"pdf", "docx", "pptx"}, "Please select a PDF, DOCX, or PPTX file.");
                    showToast("PDF Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_AUDIO:
                    uploadFile(selectedFileUri, "/form3/sciences/agriculture/audios/", "audio", new String[]{"mp3", "WAV"}, "Please select an MP3 file.");
                    showToast("Audio Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_VIDEO:
                    uploadFile(selectedFileUri, "/form3/sciences/agriculture/videos/", "videos", new String[]{"mp4", "AVI", "MKV", "WMV" , "MOV"}, "Please Select Video format.");
                    showToast("Video Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_QUESTION:
                    uploadFile(selectedFileUri, "/form3/sciences/agriculture/quizzes_and_questions/", "questions", new String[]{}, "No restriction on question formats.");
                    showToast("Question Selected: " + selectedFileUri.toString());
                    break;
            }

            switch (requestCode) {
                case REQUEST_PICK_PDF:
                    uploadFile(selectedFileUri, "/form3/sciences/biology/pdfs/", "pdfs", new String[]{"pdf", "docx", "pptx"}, "Please select a PDF, DOCX, or PPTX file.");
                    showToast("PDF Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_AUDIO:
                    uploadFile(selectedFileUri, "/form3/sciences/biology/audios/", "audio", new String[]{"mp3", "WAV"}, "Please select an MP3 file.");
                    showToast("Audio Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_VIDEO:
                    uploadFile(selectedFileUri, "/form3/sciences/biology/videos/", "videos", new String[]{"mp4", "AVI", "MKV", "WMV" , "MOV"}, "Please Select Video format.");
                    showToast("Video Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_QUESTION:
                    uploadFile(selectedFileUri, "/form3/sciences/biology/quizzes_and_questions/", "questions", new String[]{}, "No restriction on question formats.");
                    showToast("Question Selected: " + selectedFileUri.toString());
                    break;
            }

            switch (requestCode) {
                case REQUEST_PICK_PDF:
                    uploadFile(selectedFileUri, "/form3/sciences/chemistry/pdfs/", "pdfs", new String[]{"pdf", "docx", "pptx"}, "Please select a PDF, DOCX, or PPTX file.");
                    showToast("PDF Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_AUDIO:
                    uploadFile(selectedFileUri, "/form3/sciences/chemistry/audios/", "audio", new String[]{"mp3", "WAV"}, "Please select an MP3 file.");
                    showToast("Audio Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_VIDEO:
                    uploadFile(selectedFileUri, "/form3/sciences/chemistry/videos/", "videos", new String[]{"mp4", "AVI", "MKV", "WMV" , "MOV"}, "Please Select Video format.");
                    showToast("Video Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_QUESTION:
                    uploadFile(selectedFileUri, "/form3/sciences/chemistry/quizzes_and_questions/", "questions", new String[]{}, "No restriction on question formats.");
                    showToast("Question Selected: " + selectedFileUri.toString());
                    break;
            }

            switch (requestCode) {
                case REQUEST_PICK_PDF:
                    uploadFile(selectedFileUri, "/form3/sciences/mathematics/pdfs/", "pdfs", new String[]{"pdf", "docx", "pptx"}, "Please select a PDF, DOCX, or PPTX file.");
                    showToast("PDF Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_AUDIO:
                    uploadFile(selectedFileUri, "/form3/sciences/mathematics/audios/", "audio", new String[]{"mp3", "WAV"}, "Please select an MP3 file.");
                    showToast("Audio Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_VIDEO:
                    uploadFile(selectedFileUri, "/form3/sciences/mathematics/videos/", "videos", new String[]{"mp4", "AVI", "MKV", "WMV" , "MOV"}, "Please Select Video format.");
                    showToast("Video Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_QUESTION:
                    uploadFile(selectedFileUri, "/form3/sciences/mathematics/quizzes_and_questions/", "questions", new String[]{}, "No restriction on question formats.");
                    showToast("Question Selected: " + selectedFileUri.toString());
                    break;
            }

            switch (requestCode) {
                case REQUEST_PICK_PDF:
                    uploadFile(selectedFileUri, "/form3/sciences/physics/pdfs/", "pdfs", new String[]{"pdf", "docx", "pptx"}, "Please select a PDF, DOCX, or PPTX file.");
                    showToast("PDF Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_AUDIO:
                    uploadFile(selectedFileUri, "/form3/sciences/physics/audios/", "audio", new String[]{"mp3", "WAV"}, "Please select an MP3 file.");
                    showToast("Audio Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_VIDEO:
                    uploadFile(selectedFileUri, "/form3/sciences/physics/videos/", "videos", new String[]{"mp4", "AVI", "MKV", "WMV" , "MOV"}, "Please Select Video format.");
                    showToast("Video Selected: " + selectedFileUri.toString());
                    break;
                case REQUEST_PICK_QUESTION:
                    uploadFile(selectedFileUri, "/form3/sciences/physics/quizzes_and_questions/", "questions", new String[]{}, "No restriction on question formats.");
                    showToast("Question Selected: " + selectedFileUri.toString());
                    break;
            }


        }
    }

<<<<<<< HEAD
    private void uploadFile(Uri fileUri, String storagePath, String firestoreCollection, String[] allowedExtensions, String errorMessage) {
=======
<<<<<<< HEAD
    private void uploadFile(Uri fileUri, String storagePath, String firestoreCollection, String[] allowedExtensions, String errorMessage) {
=======

    // Inside your uploadFile method after uploading the file
    private void uploadFile(Uri fileUri, String storagePath, String firestoreCollection) {
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
        if (fileUri != null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            String fileName = UUID.randomUUID().toString();
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
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

<<<<<<< HEAD
=======
=======
            // Update the path to upload files to the specified storagePath
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
            StorageReference fileRef = storageReference.child(storagePath + fileName);

            fileRef.putFile(fileUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        progressDialog.dismiss();
                        showToast("File uploaded successfully");

<<<<<<< HEAD
                        fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                            String fileUrl = uri.toString();
=======
<<<<<<< HEAD
                        fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                            String fileUrl = uri.toString();
=======
                        // Retrieve the download URL after successful upload
                        fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                            String fileUrl = uri.toString();
                            // Save fileUrl to Firestore or another database
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
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
<<<<<<< HEAD
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void saveFileUrlToFirestore(String fileUrl, String firestoreCollection) {
<<<<<<< HEAD
=======
=======
    // Method to save the file URL to Firestore
    private void saveFileUrlToFirestore(String fileUrl) {
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> fileData = new HashMap<>();
        fileData.put("fileUrl", fileUrl);

<<<<<<< HEAD
        db.collection(firestoreCollection)
=======
<<<<<<< HEAD
        db.collection(firestoreCollection)
=======
        db.collection("files")
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
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

<<<<<<< HEAD

    @Override
    public void onBackPressed() {
        // Handle the back button action
        super.onBackPressed();
        // You can also add custom logic here if needed
    }
=======
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
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
}
