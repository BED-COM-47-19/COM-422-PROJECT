

package com.example.teachandlearn.MainClass;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.teachandlearn.R;


public class UserAccountSettings extends AppCompatActivity {

    private Toolbar toolbar;
    private Switch switchNotifications;
    private Switch switchDarkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account_settings);

        // Initialize toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Account Settings");

        // Initialize switches
        switchNotifications = findViewById(R.id.switch_notifications);
        switchDarkMode = findViewById(R.id.switch_dark_mode);

        // Set click listeners for switches
        switchNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle notifications switch click
                boolean isChecked = switchNotifications.isChecked();
                // Perform necessary actions based on switch state
            }
        });

        switchDarkMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle dark mode switch click
                boolean isChecked = switchDarkMode.isChecked();
                // Perform necessary actions based on switch state
            }
        });
    }
}
