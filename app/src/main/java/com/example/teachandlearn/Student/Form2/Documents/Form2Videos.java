

package com.example.teachandlearn.Student.Form2.Documents;
import android.content.Context;
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
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;
import java.util.List;
import android.widget.Button;
import android.widget.EditText;
import com.example.teachandlearn.CHATGPT.ChatGPTService;


public class Form2Videos extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Form2VideoAdapter form2VideoAdapter;
    private ChatGPTService chatGPTService;
    private EditText editTextComment;
    private Button buttonSubmitComment;

    private ArrayList<String> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2_video);

        recyclerView = findViewById(R.id.rvVideos);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        editTextComment = findViewById(R.id.editTextComment);

        buttonSubmitComment = findViewById(R.id.buttonSubmitComment);

        chatGPTService = new ChatGPTService();

        comments = new ArrayList<>();

        buttonSubmitComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = editTextComment.getText().toString().trim();
                if (!comment.isEmpty()) {
                    comments.add(comment);
                    editTextComment.setText(""); // Clear the comment field after submission
                    // Notify adapter of data change
                    form2VideoAdapter.notifyDataSetChanged();
                    // Send the comment to ChatGPT for processing
                    chatGPTService.sendCommentToAI(comment);
                } else {
                    Toast.makeText(Form2Videos.this, "Please enter a comment", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fetchVideos();
    }

    private void fetchVideos() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef;

        storageRef = FirebaseStorage.getInstance().getReference().child("/form2/humanities/bible_knowledge/videos/");

        storageRef = FirebaseStorage.getInstance().getReference().child("/form2/humanities/geography/videos/");

        storageRef = FirebaseStorage.getInstance().getReference().child("/form2/humanities/history/videos/");

        storageRef = FirebaseStorage.getInstance().getReference().child("/form2/humanities/life_skills/videos/");

        storageRef = FirebaseStorage.getInstance().getReference().child("/form2/humanities/social_studies/videos/");

        storageRef = FirebaseStorage.getInstance().getReference().child("/form2/languages/english/videos/");

        storageRef = FirebaseStorage.getInstance().getReference().child("/form2/languages/chichewa/videos/");

        storageRef = FirebaseStorage.getInstance().getReference().child("/form2/sciences/agriculture/videos/");

        storageRef = FirebaseStorage.getInstance().getReference().child("/form2/sciences/biology/videos/");

        storageRef = FirebaseStorage.getInstance().getReference().child("/form2/sciences/chemistry/videos/");

        storageRef = FirebaseStorage.getInstance().getReference().child("/form2/sciences/mathematics/videos/");

        storageRef = FirebaseStorage.getInstance().getReference().child("/form2/sciences/physics/videos/");


        storageRef.listAll().addOnSuccessListener(listResult -> {
            List<VideoItem> videos = new ArrayList<>();
            for (StorageReference item : listResult.getItems()) {
                String name = item.getName();
                String url = item.getDownloadUrl().toString();
                videos.add(new VideoItem(name, url));
                comments.add("");
            }
            if (videos.isEmpty()) {
                // If no videos found, show "Nothing Uploaded yet" message
                Toast.makeText(this, "Nothing Uploaded yet", Toast.LENGTH_SHORT).show();
            } else {
                form2VideoAdapter = new Form2VideoAdapter(videos, this);
                recyclerView.setAdapter(form2VideoAdapter);
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

    public static class Form2VideoAdapter extends RecyclerView.Adapter<Form2VideoAdapter.VideoViewHolder> {

        private List<VideoItem> videos;
        private Context context;

        private List<String> comments;




        public Form2VideoAdapter(List<VideoItem> videos, Context context) {
            this.videos = videos;
            this.context = context;
            this.comments = comments;
        }

        @NonNull
        @Override
        public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_form2_vedio_item, parent, false);
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
