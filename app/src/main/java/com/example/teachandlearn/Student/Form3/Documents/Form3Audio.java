

package com.example.teachandlearn.Student.Form3.Documents;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;



public class Form3Audio extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AudioAdapter adapter;
    private MediaPlayer mediaPlayer;  // MediaPlayer instance as a member of Form1Audio

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form3_audio);

        mediaPlayer = new MediaPlayer();  // Initialize MediaPlayer
        recyclerView = findViewById(R.id.recyclerViewAudio);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AudioAdapter(new ArrayList<>(), mediaPlayer);  // Pass MediaPlayer to the adapter
        recyclerView.setAdapter(adapter);

        fetchAudios();  // Fetch audios from Firebase
    }

    private void fetchAudios() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("audios/form3");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<AudioItem> list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    AudioItem audio = snapshot.getValue(AudioItem.class);
                    list.add(audio);
                }
                adapter.setAudioList(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Audio", "Failed to read audio", databaseError.toException());
            }
        });
    }

    public static class AudioItem {
        private String title;
        private String filePath;

        // Constructors, getters, and setters
        public AudioItem() { }  // Needed for Firebase deserialization

        public AudioItem(String title, String filePath) {
            this.title = title;
            this.filePath = filePath;
        }

        public String getTitle() {
            return title;
        }

        public String getFilePath() {
            return filePath;
        }
    }

    private class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.AudioViewHolder> {
        private List<AudioItem> audioList;
        private MediaPlayer mediaPlayer;

        // Constructor to accept MediaPlayer
        public AudioAdapter(List<AudioItem> audioList, MediaPlayer mediaPlayer) {
            this.audioList = audioList;
            this.mediaPlayer = mediaPlayer;
        }

        @NonNull
        @Override
        public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_form3_audio_item, parent, false);
            return new AudioViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
            AudioItem audio = audioList.get(position);
            holder.textViewTitle.setText(audio.getTitle());
            holder.itemView.setOnClickListener(v -> playAudio(audio.getFilePath(), holder));
        }

        private void playAudio(String filePath, AudioViewHolder holder) {
            try {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
                mediaPlayer.setDataSource(filePath);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (Exception e) {
                Toast.makeText(holder.itemView.getContext(), "Error playing audio", Toast.LENGTH_SHORT).show();
                Log.e("AudioAdapter", "Error playing audio", e);
            }
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

            public AudioViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewTitle = itemView.findViewById(R.id.textViewAudioTitle);
            }
        }
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
}
