package com.example.worldexplorer;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudyActivity extends AppCompatActivity {
    ArrayList<Question> questionArrayList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        RecyclerView recycler = findViewById(R.id.recycler_view);

        String[] captions = new String[Constants.questions.length];
        int[] ids = new int[Constants.questions.length];

        for(int i = 0; i<captions.length;i++){
            captions[i] = Constants.questions[i].getCorrectAnswerString();
            ids[i] = Constants.questions[i].getImage();
        }
        recycler.setLayoutManager(new LinearLayoutManager(this));
        FlagsAdapter adapter = new FlagsAdapter(captions, ids);
        recycler.setAdapter(adapter);
    }
}
