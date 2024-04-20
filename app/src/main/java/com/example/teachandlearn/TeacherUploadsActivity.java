package com.example.teachandlearn;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.util.UUID;

public class TeacherUploadsActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_uploads);

        // Initialize Firebase Storage
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        Button pdfButton = findViewById(R.id.pdfButton);
        Button audioButton = findViewById(R.id.audioButton);
        Button videosButton = findViewById(R.id.videosButton);
        Button questionsButton = findViewById(R.id.questionsButton);

        Button deletePdfButton = findViewById(R.id.deletePdfButton);
        Button deleteAudioButton = findViewById(R.id.deleteAudioButton);
        Button deleteVideosButton = findViewById(R.id.deleteVideosButton);
        Button deleteQuestionsButton = findViewById(R.id.deleteQuestionsButton);

        pdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFilePicker("application/pdf", REQUEST_PICK_PDF);
            }
        });

        audioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFilePicker("audio/*", REQUEST_PICK_AUDIO);
            }
        });

        videosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFilePicker("video/*", REQUEST_PICK_VIDEO);
            }
        });

        questionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFilePicker("*/*", REQUEST_PICK_QUESTION);
            }
        });

        deletePdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFile(selectedPdfUri);
                selectedPdfUri = null;
            }
        });

        deleteAudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFile(selectedAudioUri);
                selectedAudioUri = null;
            }
        });

        deleteVideosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFile(selectedVideoUri);
                selectedVideoUri = null;
            }
        });

        deleteQuestionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFile(selectedQuestionUri);
                selectedQuestionUri = null;
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
        if (resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri selectedFileUri = data.getData();
                if (selectedFileUri != null) {
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
        }
    }

    private void uploadFile(Uri fileUri) {
        if (fileUri != null) {
            // Generate a random UUID for the file name
            String fileName = UUID.randomUUID().toString();
            StorageReference fileRef = storageReference.child("uploads/" + fileName);

            // Upload file to Firebase Storage
            fileRef.putFile(fileUri)
                    .addOn
