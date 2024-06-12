


package com.example.teachandlearn.Student.Form3.Documents.English;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
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

public class Form3Audio extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AudioAdapter adapter;
    private MediaPlayer mediaPlayer;
    private ChatGPTService chatGPTService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_audio);

        mediaPlayer = new MediaPlayer();
        recyclerView = findViewById(R.id.recyclerViewAudio);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AudioAdapter(new ArrayList<>(), mediaPlayer, new ChatGPTService());
        recyclerView.setAdapter(adapter);

        fetchAudios();
    }

    private void fetchAudios() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        String[] paths = {

                "/form3/languages/english/audios/"

        };

        for (String path : paths) {
            StorageReference storageRef = storage.getReference().child(path);
            fetchFromStorage(storageRef);
        }
    }

    private void fetchFromStorage(StorageReference storageRef) {
        storageRef.listAll().addOnSuccessListener(listResult -> {
            List<AudioItem> list = new ArrayList<>();
            for (StorageReference item : listResult.getItems()) {
                String fileName = item.getName();
                String filePath = item.getPath();
                String title;
                int lastIndex = fileName.lastIndexOf('.');
                if (lastIndex != -1) {
                    title = fileName.substring(0, lastIndex);
                } else {
                    title = fileName;
                }
                list.add(new AudioItem(title, filePath, "", "", "")); // Include an empty string for the comment
            }
            if (list.isEmpty()) {
                showNoFilesUploaded();
            } else {
                adapter.setAudioList(list);
            }
        }).addOnFailureListener(exception -> {
            Log.e("Form1Audio", "Failed to fetch audio files", exception);
            Toast.makeText(this, "Failed to fetch audio files", Toast.LENGTH_SHORT).show();
        });
    }

    private void showNoFilesUploaded() {
        adapter.setAudioList(new ArrayList<>());
        Toast.makeText(this, "No file Uploaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public static class AudioItem {
        private final String title;
        private final String filePath;
        private final String description;
        private final String length;
        private String comment;

        public AudioItem(String title, String filePath, String description, String length, String comment) {
            this.title = title;
            this.filePath = filePath;
            this.description = description;
            this.length = length;
            this.comment = comment;
        }

        public String getTitle() {
            return title;
        }

        public String getFilePath() {
            return filePath;
        }

        public String getDescription() {
            return description;
        }

        public String getLength() {
            return length;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }

    public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.AudioViewHolder> {
        private List<AudioItem> audioList;
        private final MediaPlayer mediaPlayer;
        private final ChatGPTService chatGPTService;

        public AudioAdapter(List<AudioItem> audioList, MediaPlayer mediaPlayer, ChatGPTService chatGPTService) {
            this.audioList = audioList;
            this.mediaPlayer = mediaPlayer;
            this.chatGPTService = chatGPTService;
        }

        @NonNull
        @Override
        public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_form1_audio_item, parent, false);
            return new AudioViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
            AudioItem audio = audioList.get(position);

            holder.textViewTitle.setText(audio.getTitle());
            holder.textViewDescription.setText(audio.getDescription());
            holder.textViewLength.setText(audio.getLength());
            holder.editTextComment.setText(audio.getComment());

            holder.itemView.setOnClickListener(v -> {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }

                try {
                    FirebaseStorage.getInstance().getReference(audio.getFilePath()).getDownloadUrl().addOnSuccessListener(uri -> {
                        try {
                            mediaPlayer.setDataSource(uri.toString());
                            mediaPlayer.setOnPreparedListener(mp -> {
                                mp.start();
                                Toast.makeText(holder.itemView.getContext(), "Playing audio", Toast.LENGTH_SHORT).show();
                            });
                            mediaPlayer.prepareAsync();
                        } catch (Exception e) {
                            Log.e("AudioAdapter", "Error setting data source", e);
                            Toast.makeText(holder.itemView.getContext(), "Error playing audio", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    Log.e("AudioAdapter", "Error playing audio", e);
                    Toast.makeText(holder.itemView.getContext(), "Error playing audio", Toast.LENGTH_SHORT).show();
                }
            });

            holder.buttonSubmitComment.setOnClickListener(v -> {
                String newComment = holder.editTextComment.getText().toString();
                audio.setComment(newComment);
                chatGPTService.sendCommentToAI(newComment, new ChatGPTService.ChatGPTCallback() {
                    @Override
                    public void onSuccess(String response) {
                        // Handle the successful AI response
                        Toast.makeText(holder.itemView.getContext(), "AI Response: " + response, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        // Handle the failure of the AI response
                        Toast.makeText(holder.itemView.getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                notifyDataSetChanged();
            });
        }

        @Override
        public int getItemCount() {
            return audioList.size();
        }

        public void setAudioList(List<AudioItem> list) {
            this.audioList = list;
            notifyDataSetChanged();
        }

        public class AudioViewHolder extends RecyclerView.ViewHolder {
            TextView textViewTitle;
            TextView textViewDescription;
            TextView textViewLength;
            EditText editTextComment;
            Button buttonSubmitComment;

            public AudioViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewTitle = itemView.findViewById(R.id.textViewAudioTitle);
                textViewDescription = itemView.findViewById(R.id.textViewAudioDescription);
                textViewLength = itemView.findViewById(R.id.textViewAudioLength);
                editTextComment = itemView.findViewById(R.id.editTextComment);
                buttonSubmitComment = itemView.findViewById(R.id.buttonSubmitComment);
            }

        }

    }

}
