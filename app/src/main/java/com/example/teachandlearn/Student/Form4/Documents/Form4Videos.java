

package com.example.teachandlearn.Student.Form4.Documents;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.teachandlearn.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import androidx.recyclerview.widget.RecyclerView;



public class Form4Videos extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Form4VideoAdapter form4videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form4_video);

        recyclerView = findViewById(R.id.rvVideos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize your Firebase components and setup your query
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("videos");
        Query query = databaseReference.orderByKey();

        form4videoAdapter = new Form4VideoAdapter(query, this); // You'll need to create a custom adapter
        recyclerView.setAdapter(form4videoAdapter);
    }
}

