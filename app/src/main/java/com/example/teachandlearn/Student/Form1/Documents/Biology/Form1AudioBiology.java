package com.example.teachandlearn.Student.Form1.Documents.Biology;

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

public class Form1AudioBiology extends AppCompatActivity {

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
        String path = "/form1/sciences/biology/audios/";
        StorageReference storageRef = storage.getReference().child(path);

        storageRef.listAll().addOnSuccessListener(listResult -> {
            List<AudioItem> audioList = new ArrayList<>();
            for (StorageReference item : listResult.getItems()) {
                String fileName = item.getName();
                String filePath = item.getPath();
                String title = getTitleFromFileName(fileName);
                String description = ""; // Set description as needed
                String length = ""; // Set audio length as needed
                audioList.add(new AudioItem(title, filePath, description, length));
            }
            if (audioList.isEmpty()) {
                showNoFilesUploaded();
            } else {
                adapter.setAudioList(audioList);
            }
        }).addOnFailureListener(exception -> {
            Log.e("Form1AudioBiology", "Failed to fetch audio files", exception);
            Toast.makeText(this, "Failed to fetch audio files", Toast.LENGTH_SHORT).show();
        });
    }

    private String getTitleFromFileName(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex != -1) {
            return fileName.substring(0, lastIndex);
        } else {
            return fileName;
        }
    }

    private void showNoFilesUploaded() {
        adapter.setAudioList(new ArrayList<>());
        Toast.makeText(this, "No files Uploaded", Toast.LENGTH_SHORT).show();
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

        public AudioItem(String title, String filePath, String description, String length) {
            this.title = title;
            this.filePath = filePath;
            this.description = description;
            this.length = length;
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
        }

        @Override
        public int getItemCount() {
            return audioList.size();
        }

        public void setAudioList(List<AudioItem> audioList) {
            this.audioList = audioList;
            notifyDataSetChanged();
        }

        public class AudioViewHolder extends RecyclerView.ViewHolder {
            TextView textViewTitle;
            TextView textViewDescription;
            TextView textViewLength;

            public AudioViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewTitle = itemView.findViewById(R.id.textViewAudioTitle);
                textViewDescription = itemView.findViewById(R.id.textViewAudioDescription);
                textViewLength = itemView.findViewById(R.id.textViewAudioLength);
            }
        }
    }
}
