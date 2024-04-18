package com.example.teachandlearn;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SelectClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_class);

        // Dummy data for class names
        String[] classNames = {"Form 1", "Form 2", "Form 3", "Form 4"};

        // Find the ListView for class selection
        ListView listViewClasses = findViewById(R.id.listViewClasses);

        // Create an ArrayAdapter for the ListView
        ArrayAdapter<String> classAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, classNames);

        // Set the adapter to the ListView
        listViewClasses.setAdapter(classAdapter);

        // Set item click listener for ListView items
        listViewClasses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected class name
                String selectedClass = classNames[position];

                // Display a toast message with the selected class name
                Toast.makeText(SelectClassActivity.this, "Selected Class: " + selectedClass, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
