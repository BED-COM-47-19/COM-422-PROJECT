<<<<<<< HEAD


package com.example.teachandlearn.Student.Form1.Documents;
=======
package com.example.teachandlearn.Student.Form1.Documents;

>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
<<<<<<< HEAD
=======

>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
<<<<<<< HEAD
=======

>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
import com.example.teachandlearn.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
<<<<<<< HEAD
=======

>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
import java.util.ArrayList;
import java.util.List;

public class Form1Audio extends AppCompatActivity {

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
        StorageReference storageRef = storage.getReference().child("form1/audios/");

        storageRef.listAll().addOnSuccessListener(listResult -> {
            List<AudioItem> list = new ArrayList<>();
            for (StorageReference item : listResult.getItems()) {
                String fileName = item.getName();
                String filePath = item.getPath();
                String title = fileName.substring(0, fileName.lastIndexOf('.'));
                list.add(new AudioItem(title, filePath, "", ""));
            }
            if (list.isEmpty()) {
                // If no audio files found, show "No file Uploaded" message
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
        // Clear the existing list of audio items
        adapter.setAudioList(new ArrayList<>());
        // Display "No file Uploaded" message
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
        private String title;
        private String filePath;
        private String description;
        private String length;

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

    private class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.AudioViewHolder> {
        private List<AudioItem> audioList;
        private MediaPlayer mediaPlayer;

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
<<<<<<< HEAD

            // Bind audio data to views
            holder.textViewTitle.setText(audio.getTitle());
            holder.textViewDescription.setText(audio.getDescription());
            holder.textViewLength.setText(audio.getLength());

            // Set click listener to play audio when clicked
            holder.itemView.setOnClickListener(v -> {
                // Stop any previous playback
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }

                try {
                    // Set the data source to the Firebase Storage URL
                    mediaPlayer.setDataSource(audio.getFilePath());
                    // Prepare the media player asynchronously
                    mediaPlayer.prepareAsync();
                    // Set a listener for when preparation is done
                    mediaPlayer.setOnPreparedListener(mp -> {
                        // Start playback
                        mp.start();
                    });
                } catch (Exception e) {
                    Log.e("AudioAdapter", "Error playing audio", e);
                    Toast.makeText(holder.itemView.getContext(), "Error playing audio", Toast.LENGTH_SHORT).show();
                }
            });
        }


=======
            // Bind audio data to views
        }

>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
        @Override
        public int getItemCount() {
            return audioList.size();
        }

        public void setAudioList(List<AudioItem> list) {
            this.audioList = list;
            notifyDataSetChanged();
        }

        // Other methods and inner class definitions

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
