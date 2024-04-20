package com.example.teachandlearn;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TeacherUploadsActivity extends AppCompatActivity {

    private static final int REQUEST_PICK_PDF = 1;
    private static final int REQUEST_PICK_AUDIO = 2;
    private static final int REQUEST_PICK_VIDEO = 3;
    private static final int REQUEST_PICK_QUESTION = 4;

    private Uri selectedPdfUri;
    private Uri selectedAudioUri;
    private Uri selectedVideoUri;
    private Uri selectedQuestionUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_uploads);

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
                            showToast("PDF Selected: " + selectedFileUri.toString());
                            break;
                        case REQUEST_PICK_AUDIO:
                            selectedAudioUri = selectedFileUri;
                            showToast("Audio Selected: " + selectedFileUri.toString());
                            break;
                        case REQUEST_PICK_VIDEO:
                            selectedVideoUri = selectedFileUri;
                            showToast("Video Selected: " + selectedFileUri.toString());
                            break;
                        case REQUEST_PICK_QUESTION:
                            selectedQuestionUri = selectedFileUri;
                            showToast("Question Selected: " + selectedFileUri.toString());
                            break;
                    }
                }
            }
        }
    }

    private void deleteFile(Uri fileUri) {
        // Implement file deletion logic here
        if (fileUri != null) {
            showToast("File deleted: " + fileUri.toString());
        } else {
            showToast("No file selected to delete");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
