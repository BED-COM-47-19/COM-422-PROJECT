

package com.example.teachandlearn.Student.Form3.Documents;
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
import com.example.teachandlearn.R;
import com.example.teachandlearn.Student.Form1.Documents.Form1Audio;
import com.example.teachandlearn.Student.Form2.Documents.Form2Audio;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;
import java.util.List;



public class Form3Audio extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AudioAdapter adapter;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form3_audio);

        mediaPlayer = new MediaPlayer();
        recyclerView = findViewById(R.id.recyclerViewAudio);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AudioAdapter(new ArrayList<>(), mediaPlayer);
        recyclerView.setAdapter(adapter);

        fetchAudios();
    }

    private void fetchAudios() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef;

        storageRef = FirebaseStorage.getInstance().getReference().child("/form3/humanities/bible_knowledge/audios/");
        fetchFromStorage(storageRef);

        storageRef = FirebaseStorage.getInstance().getReference().child("/form3/humanities/geography/audios/");
        fetchFromStorage(storageRef);

        storageRef = FirebaseStorage.getInstance().getReference().child("/form3/humanities/history/audios/");
        fetchFromStorage(storageRef);

        storageRef = FirebaseStorage.getInstance().getReference().child("/form3/humanities/life_skills/audios/");
        fetchFromStorage(storageRef);

        storageRef = FirebaseStorage.getInstance().getReference().child("/form3/humanities/social_studies/audios/");
        fetchFromStorage(storageRef);

        storageRef = FirebaseStorage.getInstance().getReference().child("/form3/languages/english/audios/");
        fetchFromStorage(storageRef);

        storageRef = FirebaseStorage.getInstance().getReference().child("/form3/languages/chichewa/audios/");
        fetchFromStorage(storageRef);

        storageRef = FirebaseStorage.getInstance().getReference().child("/form3/sciences/agriculture/audios/");
        fetchFromStorage(storageRef);

        storageRef = FirebaseStorage.getInstance().getReference().child("/form3/sciences/biology/audios/");
        fetchFromStorage(storageRef);

        storageRef = FirebaseStorage.getInstance().getReference().child("/form3/sciences/chemistry/audios/");
        fetchFromStorage(storageRef);

        storageRef = FirebaseStorage.getInstance().getReference().child("/form3/sciences/mathematics/audios/");
        fetchFromStorage(storageRef);

        storageRef = FirebaseStorage.getInstance().getReference().child("/form3/sciences/physics/audios/");
        fetchFromStorage(storageRef);


    }

    private void fetchFromStorage(StorageReference storageRef) {
        storageRef.listAll().addOnSuccessListener(listResult -> {
            List<Form3Audio.AudioItem> list = new ArrayList<>();
            for (StorageReference item : listResult.getItems()) {
                String fileName = item.getName();
                String filePath = item.getPath();
                String title = fileName.substring(0, fileName.lastIndexOf('.'));
                list.add(new Form3Audio.AudioItem(title, filePath, "", "", "")); // Include an empty string for the comment

            }
            if (list.isEmpty()) {
                // If no audio files found, show "No file Uploaded" message
                showNoFilesUploaded();
            } else {
                adapter.setAudioList(list);
            }
        }).addOnFailureListener(exception -> {
            Log.e("Form3Audio", "Failed to fetch audio files", exception);
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


    public class AudioAdapter extends RecyclerView.Adapter<Form3Audio.AudioAdapter.AudioViewHolder> {
        private List<Form3Audio.AudioItem> audioList;
        private MediaPlayer mediaPlayer;

        public AudioAdapter(List<Form3Audio.AudioItem> audioList, MediaPlayer mediaPlayer) {
            this.audioList = audioList;
            this.mediaPlayer = mediaPlayer;
        }

        @NonNull
        @Override
        public Form3Audio.AudioAdapter.AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_form2_audio_item, parent, false);
            return new Form3Audio.AudioAdapter.AudioViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull Form3Audio.AudioAdapter.AudioViewHolder holder, int position) {
            Form3Audio.AudioItem audio = audioList.get(position);

            // Bind audio data to views
            holder.textViewTitle.setText(audio.getTitle());
            holder.textViewDescription.setText(audio.getDescription());
            holder.textViewLength.setText(audio.getLength());

            holder.editTextComment.setText(audio.getComment());
            holder.buttonSubmitComment.setOnClickListener(v -> {
                String newComment = holder.editTextComment.getText().toString();
                // Update the comment in the AudioItem object
                audio.setComment(newComment);
                // Notify the adapter that data has changed
                notifyDataSetChanged();
            });

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

        @Override
        public int getItemCount() {
            return audioList.size();
        }

        public void setAudioList(List<Form3Audio.AudioItem> list) {
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
