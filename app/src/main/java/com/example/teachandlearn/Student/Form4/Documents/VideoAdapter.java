

package com.example.teachandlearn.Student.Form4.Documents;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teachandlearn.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private List<DataSnapshot> videoList = new ArrayList<>();
    private LayoutInflater inflater;

    public VideoAdapter(Query query, AppCompatActivity activity) {
        inflater = LayoutInflater.from(activity);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                videoList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    videoList.add(snapshot);
                }
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.video_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataSnapshot snapshot = videoList.get(position);
        // Set your view components based on your data model
        holder.textView.setText(snapshot.getKey()); // Example of setting text to TextView
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView; // Example component

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.videoText); // Adjust ID as needed
        }
    }
}
