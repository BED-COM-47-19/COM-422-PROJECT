

package com.example.teachandlearn.Student.Form4.Documents;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teachandlearn.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class Form1Videos extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_video);

        recyclerView = findViewById(R.id.rvVideos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize your Firebase components and setup your query
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("videos");
        Query query = databaseReference.orderByKey();

        videoAdapter = new VideoAdapter(query, this); // You'll need to create a custom adapter
        recyclerView.setAdapter(videoAdapter);
    }
}

