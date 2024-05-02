

package com.example.teachandlearn.Student.Documents;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.teachandlearn.R;
import java.util.ArrayList;
import java.util.List;



public class Audio extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AudioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_audio);

        recyclerView = findViewById(R.id.recyclerViewAudio);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<AudioItem> audioList = fetchAudios();
        adapter = new AudioAdapter(audioList);
        recyclerView.setAdapter(adapter);
    }

    private List<AudioItem> fetchAudios() {
        List<AudioItem> list = new ArrayList<>();
        list.add(new AudioItem("Audio 1", "/path/to/audio1.mp3"));
        list.add(new AudioItem("Audio 2", "/path/to/audio2.mp3"));
        list.add(new AudioItem("Audio 3", "/path/to/audio3.mp3"));
        return list;
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

        public AudioAdapter(List<AudioItem> audioList) {
            this.audioList = audioList;
        }

        @NonNull
        @Override
        public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_student_audio_item, parent, false);

            return new AudioViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
            AudioItem audio = audioList.get(position);
            holder.textViewTitle.setText(audio.getTitle());
        }

        @Override
        public int getItemCount() {
            return audioList.size();
        }

        public class AudioViewHolder extends RecyclerView.ViewHolder {
            TextView textViewTitle;

            public AudioViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewTitle = itemView.findViewById(R.id.textViewAudioTitle);
            }
        }
    }
}
