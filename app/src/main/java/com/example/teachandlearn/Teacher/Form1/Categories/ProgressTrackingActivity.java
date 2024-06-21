package com.example.teachandlearn.Teacher.Form1.Categories;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProgressTrackingActivity extends AppCompatActivity {

    private TextView textViewProgress;

    private String currentUserEmail;
    private String currentUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_tracking);

        // Initialize views
        textViewProgress = findViewById(R.id.textViewProgress);

        // Get current user's email
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            currentUserEmail = currentUser.getEmail();
            Log.d("ProgressTracking", "Current user email: " + currentUserEmail);

            // Fetch current user's name from Firebase
            fetchUserNameFromFirebase();
        } else {
            Log.e("ProgressTracking", "Current user is null");
            runOnUiThread(() -> textViewProgress.setText("Error: Current user is null"));
        }
    }

    private void fetchUserNameFromFirebase() {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Users");

        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean userFound = false;
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    String userEmail = userSnapshot.child("email").getValue(String.class);
                    Log.d("ProgressTracking", "Checking user email: " + userEmail);
                    if (userEmail != null && userEmail.equals(currentUserEmail)) {
                        currentUserName = userSnapshot.child("name").getValue(String.class);
                        Log.d("ProgressTracking", "Current user name: " + currentUserName);

                        // Once we have the user's name, calculate progress
                        fetchTotalBooksInForm1();
                        userFound = true;
                        break;
                    }
                }

                if (!userFound) {
                    // Handle case where user's name is not found
                    Log.e("ProgressTracking", "User name not found in Firebase");
                    runOnUiThread(() -> textViewProgress.setText("User name not found in Firebase"));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
                Log.e("ProgressTracking", "Failed to fetch user name from Firebase", error.toException());
                runOnUiThread(() -> textViewProgress.setText("Failed to fetch user name from Firebase"));
            }
        });
    }

    private void fetchTotalBooksInForm1() {
        DatabaseReference booksRef = FirebaseDatabase.getInstance().getReference().child("Books").child("Form1");

        booksRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int totalBooksInForm1 = (int) snapshot.getChildrenCount();
                Log.d("ProgressTracking", "Total books in Form 1: " + totalBooksInForm1);

                // Fetch number of books accessed by the user
                fetchBooksAccessedByUser(totalBooksInForm1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
                Log.e("ProgressTracking", "Failed to fetch total books in Form 1 from Firebase", error.toException());
                runOnUiThread(() -> textViewProgress.setText("Failed to fetch total books in Form 1 from Firebase"));
            }
        });
    }

    private void fetchBooksAccessedByUser(int totalBooksInForm1) {
        DatabaseReference userActivityRef = FirebaseDatabase.getInstance().getReference().child("UserActivities").child(currentUserEmail.replace(".", ",")).child("Form1");

        userActivityRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int booksAccessedByUser = (int) snapshot.getChildrenCount();
                Log.d("ProgressTracking", "Books accessed by user: " + booksAccessedByUser);

                // Calculate and display progress
                calculateAndDisplayProgress(totalBooksInForm1, booksAccessedByUser);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
                Log.e("ProgressTracking", "Failed to fetch books accessed by user from Firebase", error.toException());
                runOnUiThread(() -> textViewProgress.setText("Failed to fetch books accessed by user from Firebase"));
            }
        });
    }

    private void calculateAndDisplayProgress(int totalBooksInForm1, int booksAccessedByUser) {
        if (totalBooksInForm1 == 0) {
            runOnUiThread(() -> textViewProgress.setText("No books found for Form 1."));
            return;
        }

        // Calculate progress percentage
        double progressPercentage = (double) booksAccessedByUser / totalBooksInForm1 * 100;

        // Generate progress text
        String progressText = currentUserName + "'s Progress: " + String.format("%.2f%%", progressPercentage);

        // Display progress information in TextView on UI thread
        runOnUiThread(() -> textViewProgress.setText(progressText));
    }
}
