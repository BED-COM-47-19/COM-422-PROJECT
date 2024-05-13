
package com.example.teachandlearn.MainClass;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import com.example.teachandlearn.R;
import com.example.teachandlearn.databinding.ActivityMainBinding;
import androidx.appcompat.app.AppCompatActivity;

public class Main extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getStartedButton = (Button) findViewById(R.id.getStartedButton);

        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Insert the code to be executed when the Get Started button is pressed
                // For example, start a new Activity or display a message
                getStarted();
            }
        });
    }

    private void getStarted() {
        // Create an Intent to start the UserTypeSelection Activity
        Intent intent = new Intent(Main.this, UserTypeSelection.class);
        startActivity(intent);
    }

}
