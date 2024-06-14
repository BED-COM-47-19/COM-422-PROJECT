


package com.example.teachandlearn.Student.Form2.Documents.Social_Studies;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teachandlearn.CHATGPT.ChatGPTService;
import com.example.teachandlearn.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Form2VideosSOC extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Form1VideoAdapter form1VideoAdapter;
    private ChatGPTService chatGPTService;
    private EditText editTextComment;
    private Button buttonSubmitComment;

    private ArrayList<String> comments;

    private TextView textViewComment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_video);

        recyclerView = findViewById(R.id.rvVideos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        editTextComment = findViewById(R.id.editTextComment);
        buttonSubmitComment = findViewById(R.id.buttonSubmitComment);

        chatGPTService = new ChatGPTService();
        comments = new ArrayList<>();

        buttonSubmitComment.setOnClickListener(v -> {
            String comment = editTextComment.getText().toString().trim();
            if (!comment.isEmpty()) {
                comments.add(comment);
                editTextComment.setText(""); // Clear the comment field after submission
                form1VideoAdapter.notifyDataSetChanged(); // Notify adapter of data change
                chatGPTService.sendCommentToAI(comment, new ChatGPTService.ChatGPTCallback() {
                    @Override
                    public void onSuccess(String response) {
                        // Handle the successful AI response
                        Toast.makeText(Form2VideosSOC.this, "AI Response: " + response, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        // Handle the failure of the AI response
                        Toast.makeText(Form2VideosSOC.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(Form2VideosSOC.this, "Please enter a comment", Toast.LENGTH_SHORT).show();
            }
        });

        fetchVideos();
    }

    private void fetchVideos() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child("/form2/humanities/social_studies/videos/");

        storageRef.listAll().addOnSuccessListener(listResult -> {
            List<VideoItem> videos = new ArrayList<>();
            for (StorageReference item : listResult.getItems()) {
                item.getDownloadUrl().addOnSuccessListener(uri -> {
                    String name = item.getName();
                    String url = uri.toString();
                    videos.add(new VideoItem(name, url));
                    comments.add(""); // Add an empty comment for each item
                    if (videos.size() == listResult.getItems().size()) {
                        form1VideoAdapter = new Form1VideoAdapter(videos, Form2VideosSOC.this, comments);
                        recyclerView.setAdapter(form1VideoAdapter);
                    }
                }).addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to get download URL", Toast.LENGTH_SHORT).show();
                });
            }
            if (videos.isEmpty()) {
                Toast.makeText(this, "Nothing Uploaded yet", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Failed to fetch videos", Toast.LENGTH_SHORT).show();
        });
    }

    public static class VideoItem {
        private String name;
        private String url;

        public VideoItem(String name, String url) {
            this.name = name;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }

    public static class Form1VideoAdapter extends RecyclerView.Adapter<Form1VideoAdapter.VideoViewHolder> {

        private List<VideoItem> videos;
        private Context context;
        private List<String> comments;

        public Form1VideoAdapter(List<VideoItem> videos, Context context, List<String> comments) {
            this.videos = videos;
            this.context = context;
            this.comments = comments;
        }

        @NonNull
        @Override
        public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_form1_vedio_item, parent, false);
            return new VideoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
            VideoItem videoItem = videos.get(position);
            String comment = comments.get(position);
            holder.bind(videoItem, comment);
        }

        @Override
        public int getItemCount() {
            return videos.size();
        }

        public class VideoViewHolder extends RecyclerView.ViewHolder {


            TextView textViewName;
            TextView textViewUrl;
            TextView textViewComment;

            public VideoViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewName = itemView.findViewById(R.id.textViewVideoName);
                textViewUrl = itemView.findViewById(R.id.textViewVideoUrl);
                textViewComment = itemView.findViewById(R.id.textViewComment);
            }

            public void bind(VideoItem videoItem, String comment) {
                textViewName.setText(videoItem.getName());
                textViewUrl.setText(videoItem.getUrl());
                textViewComment.setText(comment);
            }
        }
    }
}
