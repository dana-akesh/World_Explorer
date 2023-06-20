package com.example.worldexplorer;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button studyBtn, quizBtn;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize the buttons
        initialize();

        // hiding the action bar using the window manager
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // set the click listeners
        setOnClickListeners();
    }

    private void initialize() {
        studyBtn = findViewById(R.id.btn_study);
        quizBtn = findViewById(R.id.btn_quiz);
    }

    private void setOnClickListeners() {
        // set the click listeners
        studyBtn.setOnClickListener(v -> {
            // to open the study activity
            Intent intent = new Intent(MainActivity.this, StudyActivity.class);
            startActivity(intent);
        });
        quizBtn.setOnClickListener(v -> {
            // to open the quiz activity
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);
        });
    }

}
