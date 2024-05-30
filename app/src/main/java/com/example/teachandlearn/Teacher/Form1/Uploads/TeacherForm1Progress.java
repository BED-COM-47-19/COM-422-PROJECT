

package com.example.teachandlearn.Teacher.Form1.Uploads;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.google.firebase.database.ServerValue;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DataSnapshot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TeacherForm1Progress extends AppCompatActivity {

    private static final String TAG = "TeacherForm1Progress";
    private ListView listView;
    private List<String> studentAccessList;
    private ArrayAdapter<String> adapter;
    private DatabaseReference accessLogsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_progress);

        listView = findViewById(R.id.listView);
        studentAccessList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentAccessList);
        listView.setAdapter(adapter);

        // Initialize the reference to the access logs node in your Realtime Database
        accessLogsRef = FirebaseDatabase.getInstance().getReference("access_logs");

        fetchStudentAccessLogs();
    }

    private void fetchStudentAccessLogs() {
        // Listen for changes in the access logs node
        accessLogsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                studentAccessList.clear(); // Clear the list before adding new logs

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String logEntry = snapshot.getValue(String.class);
                    studentAccessList.add(logEntry); // Add log entry to the list
                }

                adapter.notifyDataSetChanged(); // Update the ListView
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Error fetching logs: " + databaseError.getMessage());
            }
        });

    }



    private void logAccess(String studentId, String filePath) {
        // Generate a unique ID for the access log entry
        String logId = accessLogsRef.push().getKey();

        // Create a map to hold the log entry data
        Map<String, Object> logEntry = new HashMap<>();
        logEntry.put("studentId", studentId);
        logEntry.put("filePath", filePath);
        logEntry.put("timestamp", ServerValue.TIMESTAMP); // Use ServerValue.TIMESTAMP here

        // Write the log entry to the Realtime Database
        accessLogsRef.child(logId).setValue(logEntry);
    }
    

}
