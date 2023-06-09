package com.example.worldexplorer;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Constants {
    public static ArrayList<Question> getQuestion() {
        ArrayList<Question> questionArrayList = new ArrayList<>();

        // question 1
        Question q1 = new Question(1,
                "What country does this flag represent?",
                R.drawable.ic_flag_ps,
                "Palestine",
                "Jordan",
                "USA",
                "Tunis",
                1);
        questionArrayList.add(q1);

        // question 2
        Question q2 = new Question(2,
                "What country does this flag represent?",
                R.drawable.ic_flag_tn,
                "Turkey",
                "Morocco",
                "Lebanon",
                "Tunis",
                4);
        questionArrayList.add(q2);

        // question 3
        Question q3 = new Question(3,
                "What country does this flag represent?",
                R.drawable.ic_flag_om,
                "China",
                "Oman",
                "Canada",
                "Ecuador",
                2);
        questionArrayList.add(q3);

        // question 4
        Question q4 = new Question(4,
                "What country does this flag represent?",
                R.drawable.ic_flag_lb,
                "Libya",
                "Palestine",
                "Syria",
                "Lebanon",
                4);
        questionArrayList.add(q4);

        // question 5
        Question q5 = new Question(5,
                "What country does this flag represent?",
                R.drawable.ic_flag_qa,
                "Palestine",
                "Qatar",
                "Bahrain",
                "China",
                2);
        questionArrayList.add(q5);

        // question 6
        Question q6 = new Question(6,
                "What country does this flag represent?",
                R.drawable.ic_flag_jo,
                "Palestine",
                "Oman",
                "Jordan",
                "Iraq",
                3);
        questionArrayList.add(q6);

        // question 7
        Question q7 = new Question(7,
                "What country does this flag represent?",
                R.drawable.ic_flag_iq,
                "Yemen",
                "Saudi Arabia",
                "Syria",
                "Iraq",
                4);
        questionArrayList.add(q7);

        // question 8
        Question q8 = new Question(8,
                "What country does this flag represent?",
                R.drawable.ic_flag_eg,
                "Egypt",
                "Yemen",
                "Syria",
                "Libya",
                1);
        questionArrayList.add(q8);

        // question 9
        Question q9 = new Question(9,
                "What country does this flag represent?",
                R.drawable.ic_flag_ma,
                "Morocco",
                "Jordan",
                "Libya",
                "Tunis",
                1);
        questionArrayList.add(q9);

        // question 10
        Question q10 = new Question(10,
                "What country does this flag represent?",
                R.drawable.ic_flag_sa,
                "Lebanon",
                "Iraq",
                "Libya",
                "Saudi Arabia",
                4);
        questionArrayList.add(q10);

        return questionArrayList;
    }

    public static void saveToSharedPreferences(Context context, ArrayList<Question> questionArrayList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(questionArrayList);
        editor.putString("questionArrayList", json);
        editor.apply();
    }

    public static ArrayList<Question> readFromSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("questionArrayList", "");
        Type type = new TypeToken<ArrayList<Question>>() {
        }.getType();
        ArrayList<Question> questionArrayList = gson.fromJson(json, type);
        if (questionArrayList == null || questionArrayList.size() == 0) {
            questionArrayList = getQuestion();
        }
        return questionArrayList;
    }

    // This is the array of questions that will be used in the study activity
    public static final Question[] questions = {
            new Question(R.drawable.ic_flag_eg, "Egypt"),
            new Question(R.drawable.ic_flag_bh, "Bahrain"),
            new Question(R.drawable.ic_flag_iq, "Iraq"),
            new Question(R.drawable.ic_flag_jo, "Jordan"),
            new Question(R.drawable.ic_flag_lb, "Lebanon"),
            new Question(R.drawable.ic_flag_ma, "Morocco"),
            new Question(R.drawable.ic_flag_om, "Oman"),
            new Question(R.drawable.ic_flag_ps, "Palestine"),
            new Question(R.drawable.ic_flag_qa, "Qatar"),
            new Question(R.drawable.ic_flag_sa, "Saudi Arabia"),
            new Question(R.drawable.ic_flag_sy, "Syria"),
            new Question(R.drawable.ic_flag_tn, "Tunisia"),
    };

}
