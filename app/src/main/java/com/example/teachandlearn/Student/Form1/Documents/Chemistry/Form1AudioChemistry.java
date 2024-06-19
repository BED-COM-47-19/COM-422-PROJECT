
package com.example.teachandlearn.Student.Form1.Documents.Chemistry;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
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


public class Form1AudioChemistry extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AudioAdapter adapter;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_audio);

        mediaPlayer = new MediaPlayer();
        recyclerView = findViewById(R.id.recyclerViewAudio);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AudioAdapter(new ArrayList<>(), mediaPlayer);
        recyclerView.setAdapter(adapter);

        fetchAudios();
    }

    private void fetchAudios() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        String path = "/form1/sciences/chemistry/audios/";
        StorageReference storageRef = storage.getReference().child(path);
        fetchFromStorage(storageRef);
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
                list.add(new AudioItem(title, filePath, "", "")); // Exclude the comment
            }
            adapter.setAudioList(list); // Set the fetched list to the adapter
        }).addOnFailureListener(exception -> {
            Log.e("Form1Audio", "Failed to fetch audio files", exception);
            Toast.makeText(this, "Failed to fetch audio files", Toast.LENGTH_SHORT).show();
        });
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

        public AudioAdapter(List<AudioItem> audioList, MediaPlayer mediaPlayer) {
            this.audioList = audioList;
            this.mediaPlayer = mediaPlayer;
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

        public void setAudioList(List<AudioItem> list) {
            this.audioList = list;
            notifyDataSetChanged();

            // Optionally, show toast message only if list is empty
            if (list.isEmpty()) {
                Toast.makeText(Form1AudioChemistry.this, "No file Uploaded", Toast.LENGTH_SHORT).show();
            }
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
