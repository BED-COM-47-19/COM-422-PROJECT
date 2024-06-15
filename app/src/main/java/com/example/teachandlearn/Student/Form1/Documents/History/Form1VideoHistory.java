

package com.example.teachandlearn.Student.Form1.Documents.History;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teachandlearn.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;



public class Form1VideoHistory extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Form1VideoAdapter form1VideoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_video);

        recyclerView = findViewById(R.id.rvVideos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchVideos();
    }

    private void fetchVideos() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child("/form1/humanities/history/videos/");

        storageRef.listAll().addOnSuccessListener(listResult -> {
            List<Form1VideoHistory.VideoItem> videos = new ArrayList<>();
            for (StorageReference item : listResult.getItems()) {
                item.getDownloadUrl().addOnSuccessListener(uri -> {
                    String name = item.getName();
                    String url = uri.toString();
                    videos.add(new Form1VideoHistory.VideoItem(name, url));
                    form1VideoAdapter = new Form1VideoAdapter(videos, this);
                    recyclerView.setAdapter(form1VideoAdapter);
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
        private AppCompatActivity context;

        public Form1VideoAdapter(List<VideoItem> videos, AppCompatActivity context) {
            this.videos = videos;
            this.context = context;
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
            holder.bind(videoItem);
        }

        @Override
        public int getItemCount() {
            return videos.size();
        }

        public class VideoViewHolder extends RecyclerView.ViewHolder {

            TextView textViewName;
            TextView textViewUrl;

            public VideoViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewName = itemView.findViewById(R.id.textViewVideoName);
                textViewUrl = itemView.findViewById(R.id.textViewVideoUrl);
            }

            public void bind(VideoItem videoItem) {
                textViewName.setText(videoItem.getName());
                textViewUrl.setText(videoItem.getUrl());
            }
        }
    }
}