

package com.example.teachandlearn.Student.Form1.Documents;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.example.teachandlearn.R;


public class Form1Videos extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Form1VideoAdapter form1VideoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form3_video);

        recyclerView = findViewById(R.id.rvVideos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize your Firebase components and setup your query
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("videos");
        Query query = databaseReference.orderByKey();

        form1VideoAdapter = new Form1VideoAdapter(query, this); // You'll need to create a custom adapter
        recyclerView.setAdapter(form1VideoAdapter);
    }
}

