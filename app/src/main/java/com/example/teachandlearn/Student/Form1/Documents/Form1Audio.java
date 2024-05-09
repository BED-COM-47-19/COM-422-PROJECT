

package com.example.teachandlearn.Student.Form1.Documents;
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


public class Form1Audio extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AudioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_audio);

        recyclerView = findViewById(R.id.recyclerViewAudio);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AudioAdapter(new ArrayList<>());  // Start with an empty list
        recyclerView.setAdapter(adapter);

        fetchAudios();  // Fetch audios from Firebase
    }

    private void fetchAudios() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("audios/form1");

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
        private MediaPlayer mediaPlayer = new MediaPlayer();

        public AudioAdapter(List<AudioItem> audioList) {
            this.audioList = audioList;
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
            holder.itemView.setOnClickListener(v -> {
                playAudio(audio.getFilePath(), holder);
            });
        }

        private void playAudio(String filePath, AudioViewHolder holder) {
            try {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
                mediaPlayer.setDataSource(filePath);  // Set the source of the audio file
                mediaPlayer.prepare();               // Prepare the MediaPlayer to play
                mediaPlayer.start();                 // Start playing
            } catch (Exception e) {
                Toast.makeText(holder.itemView.getContext(), "Error playing audio", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
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
