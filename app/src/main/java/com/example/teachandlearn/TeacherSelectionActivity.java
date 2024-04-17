
package com.example.teachandlearn;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import com.example.teachandlearn.R;
import com.example.teachandlearn.TeacherSelectionActivity;


public class TeacherSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_selection);

        // Dummy data for class names
        List<String> classNames = new ArrayList<>();
        classNames.add("Form 1");
        classNames.add("Form 2");
        classNames.add("Form 3");
        classNames.add("Form 4");

        // Find the Spinner for class selection
        Spinner spinnerClasses = findViewById(R.id.spinnerClasses);

        // Create an ArrayAdapter for the spinner
        ArrayAdapter<String> classAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, classNames);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter to the spinner
        spinnerClasses.setAdapter(classAdapter);

        // Dummy data for field names
        String[] fieldNames = {"Mathematics", "Science", "History", "English"};

        // Find the GridView for field selection
        GridView gridViewFields = findViewById(R.id.gridViewFields);

        // Create an ArrayAdapter for the GridView
        ArrayAdapter<String> fieldAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fieldNames);

        // Set the adapter to the GridView
        gridViewFields.setAdapter(fieldAdapter);

        // Set item click listener for GridView items
        gridViewFields.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected field name
                String selectedFieldName = fieldNames[position];

                // Display a toast message with the selected field name
                Toast.makeText(TeacherSelectionActivity.this, "Selected Field: " + selectedFieldName, Toast.LENGTH_SHORT).show();
            }
        });

        // Find the Submit button
        Button buttonSubmit = findViewById(R.id.buttonSubmit);

        // Set OnClickListener for the Submit button
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected class name from the Spinner
                String selectedClass = spinnerClasses.getSelectedItem().toString();

                // Display a toast message with the selected class name
                Toast.makeText(TeacherSelectionActivity.this, "Selected Class: " + selectedClass, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
