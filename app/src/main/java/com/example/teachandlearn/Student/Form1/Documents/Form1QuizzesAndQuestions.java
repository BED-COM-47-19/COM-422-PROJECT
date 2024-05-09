

package com.example.teachandlearn.Student.Form1.Documents;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;

public class Form1QuizzesAndQuestions extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> pdfNames;
    private ArrayList<String> pdfUrls; // To store URLs of the PDFs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_past_papers);

        listView = findViewById(R.id.list_view);
        pdfNames = new ArrayList<>();
        pdfUrls = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.activity_form1_quizzes_and_questions, pdfNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = pdfUrls.get(position);
                Intent intent = new Intent(Form1QuizzesAndQuestions.this, Form1PDFViewer.class);
                intent.putExtra("PDF_URL", url);
                startActivity(intent);
            }
        });

        loadPDFs();
    }

    private void loadPDFs() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("pastpapers").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String name = document.getString("name");
                    String url = document.getString("url");
                    pdfNames.add(name);
                    pdfUrls.add(url);
                    adapter.notifyDataSetChanged();
                }
            } else {
                // Handle the error
            }
        });
    }
}
