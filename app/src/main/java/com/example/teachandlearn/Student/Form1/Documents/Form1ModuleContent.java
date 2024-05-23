

package com.example.teachandlearn.Student.Form1.Documents;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Form1ModuleContent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_module_content);


        TextView textView = findViewById(R.id.textView);
        textView.setText("Your Text Heresbsfzkgbjksdfgl;skdjlgnkdczxnvljhczlkml;adkvhxbdfjadxlzvj oih");

        // Example: Set up OnClickListener for a Button
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click
            }
        });

    }
}
