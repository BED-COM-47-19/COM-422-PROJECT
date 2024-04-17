package com.example.teachandlearn;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.example.teachandlearn.ClassSelectionActivity;

public class ClassSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_selection);

        // Dummy data for class names
        String[] classNames = {"Form 1", "Form 2", "Form 3", "Form 4"};

        // Find the GridView
        GridView gridView = findViewById(R.id.gridView);

        // Create an ArrayAdapter for the GridView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, classNames);

        // Set the adapter to the GridView
        gridView.setAdapter(adapter);

        // Set item click listener for GridView items
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected class name
                String selectedClassName = classNames[position];

                // Display a toast message with the selected class name
                Toast.makeText(ClassSelectionActivity.this, "Selected Class: " + selectedClassName, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
