
package com.example.teachandlearn;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.UserTypeSelectionActivity;
import com.example.teachandlearn.ClassSelectionActivity;
import com.example.teachandlearn.TeacherSelectionActivity;

public class UserTypeSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a LinearLayout as the root layout
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setBackgroundColor(0xFF6200EE); // Purple background color
        rootLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));

        // Create Button for Student
        Button studentButton = new Button(this);
        studentButton.setText("STUDENT");
        studentButton.setTextColor(0xFF000000); // Black text color
        studentButton.setBackgroundColor(0xFF6200EE); // Purple background color
        LinearLayout.LayoutParams studentParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        studentParams.gravity = Gravity.CENTER_HORIZONTAL;
        studentParams.topMargin = 100; // Adjust top margin
        studentButton.setLayoutParams(studentParams);
        rootLayout.addView(studentButton);

        // Create Button for Teacher
        Button teacherButton = new Button(this);
        teacherButton.setText("TEACHER");
        teacherButton.setTextColor(0xFF000000); // Black text color
        teacherButton.setBackgroundColor(0xFF6200EE); // Purple background color
        LinearLayout.LayoutParams teacherParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        teacherParams.gravity = Gravity.CENTER_HORIZONTAL;
        teacherParams.topMargin = 20; // Adjust top margin
        teacherButton.setLayoutParams(teacherParams);
        rootLayout.addView(teacherButton);

        // Set OnClickListener for Student button
        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle student selection
                // For example, you can navigate to the student dashboard activity
                startActivity(new Intent(UserTypeSelectionActivity.this, ClassSelectionActivity.class));
            }
        });

        // Set OnClickListener for Teacher button
        teacherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle teacher selection
                // For example, you can navigate to the teacher dashboard activity
                startActivity(new Intent(UserTypeSelectionActivity.this, TeacherSelectionActivity.class));
            }
        });

        // Set Content View to the root layout
        setContentView(rootLayout);
    }
}
