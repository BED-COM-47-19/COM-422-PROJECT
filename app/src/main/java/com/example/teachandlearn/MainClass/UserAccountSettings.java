

package com.example.teachandlearn.MainClass;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.teachandlearn.R; // Assuming R is generated in the same package as UserAccountSettings
import android.app.Dialog;


public class UserAccountSettings extends AppCompatActivity {

    private Toolbar toolbar;
    private Switch switchNotifications;
    private Button buttonAccount;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewFirstName;
    private TextView textViewLastName;
    private TextView textViewEmail;
    private TextView textViewPassword;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account_settings);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid());

        // Initialize toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Account Settings");

        // Initialize views
        switchNotifications = findViewById(R.id.switch_notifications);
        editTextFirstName = findViewById(R.id.editText_first_name);
        editTextLastName = findViewById(R.id.editText_last_name);
        editTextEmail = findViewById(R.id.editText_email);
        editTextPassword = findViewById(R.id.editText_password);
        textViewFirstName = findViewById(R.id.textView_first_name);
        textViewLastName = findViewById(R.id.textView_last_name);
        textViewEmail = findViewById(R.id.textView_email);
        textViewPassword = findViewById(R.id.textView_password);

        // Set up listeners for switches
        switchNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle notification switch change
                // Example: update user settings in Firebase database
            }
        });




        // Set up listener for Account button
        buttonAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a dialog to display user details
                final Dialog dialog = new Dialog(UserAccountSettings.this);
                dialog.setContentView(R.layout.activity_user_account_settings);
                dialog.setTitle("User Details");

                // Find TextViews in the dialog layout
                TextView textViewFirstName = dialog.findViewById(R.id.textView_first_name);
                TextView textViewLastName = dialog.findViewById(R.id.textView_last_name);
                TextView textViewEmail = dialog.findViewById(R.id.textView_email);
                TextView textViewPassword = dialog.findViewById(R.id.textView_password);

                // Retrieve user information from Firebase
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // Check if user exists
                        if (dataSnapshot.exists()) {
                            // Get user details
                            String firstName = dataSnapshot.child("firstName").getValue().toString();
                            String lastName = dataSnapshot.child("lastName").getValue().toString();
                            String email = dataSnapshot.child("email").getValue().toString();
                            String password = dataSnapshot.child("password").getValue().toString();

                            // Display user details in TextViews
                            textViewFirstName.setText(firstName);
                            textViewLastName.setText(lastName);
                            textViewEmail.setText(email);
                            textViewPassword.setText(password);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle database error
                        Toast.makeText(UserAccountSettings.this, "Failed to retrieve user information", Toast.LENGTH_SHORT).show();
                    }
                });

                // Show the dialog
                dialog.show();
            }
        });

    }
}
