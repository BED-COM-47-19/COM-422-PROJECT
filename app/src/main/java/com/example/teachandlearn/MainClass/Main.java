
package com.example.teachandlearn.MainClass;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import com.example.teachandlearn.R;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;



public class Main extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getStartedButton = (Button) findViewById(R.id.getStartedButton);

        FirebaseDatabase.getInstance().setLogLevel(Logger.Level.DEBUG);

        FirebaseApp.initializeApp(this);

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

