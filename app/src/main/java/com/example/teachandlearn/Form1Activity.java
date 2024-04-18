
package com.example.teachandlearn;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;


public class Form1Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView textViewForm1;
    private Button buttonSciences;
    private Button buttonBack;
    private List<String> scienceSubjects;
    private boolean isShowingSubjects = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);

        textViewForm1 = findViewById(R.id.textViewForm1);
        buttonSciences = findViewById(R.id.buttonSciencesForm1);
        buttonBack = findViewById(R.id.buttonBackForm1);
        recyclerView = findViewById(R.id.recyclerViewScienceSubjects);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        scienceSubjects = new ArrayList<>();
        scienceSubjects.add("MATHEMATICS");
        scienceSubjects.add("BIOLOGY");
        scienceSubjects.add("PHYSICS");
        scienceSubjects.add("CHEMISTRY");
        scienceSubjects.add("AGRICULTURE");

        final ScienceSubjectsAdapter adapter = new ScienceSubjectsAdapter(scienceSubjects);
        recyclerView.setAdapter(adapter);

        buttonSciences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSubjectsVisibility();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void toggleSubjectsVisibility() {
        if (isShowingSubjects) {
            recyclerView.setVisibility(View.GONE);
            isShowingSubjects = false;
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            isShowingSubjects = true;
        }
    }
}
