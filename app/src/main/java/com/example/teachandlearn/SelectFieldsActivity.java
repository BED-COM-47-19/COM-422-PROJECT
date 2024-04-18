package com.example.teachandlearn;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SelectFieldsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_field);

        // Dummy data for field names
        String[] fieldNames = {"SCIENCES", "HUMANITIES", "LANGUAGES"};

        // Find the ListView for field selection
        ListView listViewFields = findViewById(R.id.listViewFields);

        // Create an ArrayAdapter for the ListView
        ArrayAdapter<String> fieldAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fieldNames);

        // Set the adapter to the ListView
        listViewFields.setAdapter(fieldAdapter);

        // Set item click listener for ListView items
        listViewFields.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected field name
                String selectedField = fieldNames[position];

                // Display a toast message with the selected field name
                Toast.makeText(SelectFieldsActivity.this, "Selected Field: " + selectedField, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
