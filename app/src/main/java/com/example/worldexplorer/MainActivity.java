package com.example.worldexplorer;

import static com.example.worldexplorer.Constants.saveToSharedPreferences;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_question;
    private ImageView iv_flag;
    private ProgressBar progressbar;
    private TextView tv_option_one;
    private TextView tv_option_two;
    private TextView tv_option_three;
    private TextView tv_option_four;
    private Button btn_submit;
    private TextView tv_progress;
    private ArrayList<Question> questionArrayList;
    private int currentPosition = 1;
    private int selectedOption = 0;
    private int correctAnswers = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // to initialize all the ui components
        initializeUiComponents();

        // to set the question
        setQuestion();

        // to set the click listeners
        setOnClickComponents();

    }


    private void initializeUiComponents() {
        try {
            // to initialize all the ui components
            questionArrayList = Constants.readFromSharedPreferences(getApplicationContext());
            tv_question = findViewById(R.id.tv_title);
            iv_flag = findViewById(R.id.iv_flag);
            LinearLayout ly_progressbar = findViewById(R.id.ly_progressbar);
            progressbar = findViewById(R.id.progressbar);
            tv_option_one = findViewById(R.id.tv_option_one);
            tv_option_two = findViewById(R.id.tv_option_two);
            tv_option_three = findViewById(R.id.tv_option_three);
            tv_option_four = findViewById(R.id.tv_option_four);
            btn_submit = findViewById(R.id.btn_submit);
            tv_progress = findViewById(R.id.tv_progress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setOnClickComponents() {
        tv_option_one.setOnClickListener(this);
        tv_option_two.setOnClickListener(this);
        tv_option_three.setOnClickListener(this);
        tv_option_four.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
    }

    private void setQuestion() {
        // to set every option to it's original style
        defaultOptionView();
        if (currentPosition == questionArrayList.size()) {
            btn_submit.setText("Show Result ☺");
        } else {
            btn_submit.setText("Submit");
        }

        // to get the question from the arraylist
        Question current_question = questionArrayList.get(currentPosition - 1);

        progressbar.setProgress(currentPosition);

        tv_progress.setText(currentPosition + "/" + progressbar.getMax());
        tv_question.setText(current_question.getQuestion());
        iv_flag.setImageResource(current_question.getImage());
        tv_option_one.setText(current_question.getOptionOne() + "");
        tv_option_two.setText(current_question.getOptionTwo());
        tv_option_three.setText(current_question.getOptionThree());
        tv_option_four.setText(current_question.getOptionFour());
    }

    private void defaultOptionView() {
        ArrayList<TextView> options = new ArrayList<>();
        options.add(0, tv_option_one);
        options.add(1, tv_option_two);
        options.add(2, tv_option_three);
        options.add(3, tv_option_four);

        for (TextView op : options) {
            op.setTextColor(Color.parseColor("#000000"));
            op.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border));
        }
    }

    private void selectedOptionView(TextView tv, int selected) {
        // to set every option to it's original style
        defaultOptionView();

        selectedOption = selected;
        tv.setTextColor(Color.parseColor("#000000"));
        tv.setBackground(ContextCompat.getDrawable(this, R.drawable.selected_option_border));

    }

    // to handle the click events
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_option_one) {
            selectedOptionView(tv_option_one, 1);
        } else if (view.getId() == R.id.tv_option_two) {
            selectedOptionView(tv_option_two, 2);
        } else if (view.getId() == R.id.tv_option_three) {
            selectedOptionView(tv_option_three, 3);
        } else if (view.getId() == R.id.tv_option_four) {
            selectedOptionView(tv_option_four, 4);
        } else if (view.getId() == R.id.btn_submit) {
            // check if the user has selected an option or not
            if (selectedOption == 0) {
                currentPosition++;
                // check if the user has reached the last question or not
                if (currentPosition <= questionArrayList.size()) {
                    // if not then set the next question
                    setQuestion();
                } else {
                    // if yes then show the result
                    Toast.makeText(this, "Quiz Completed ☺", Toast.LENGTH_LONG).show();
                }
            } else {
                // if the user has selected an option then check if it is correct or not
                Question current_question = questionArrayList.get(currentPosition - 1);

                if (current_question.getCorrectAnswer() != selectedOption) {
                    answerView(selectedOption, R.drawable.wrong_option_border);
                } else {
                    correctAnswers++;
                }
                // show the correct answer
                answerView(current_question.getCorrectAnswer(), R.drawable.correct_option_border);

                // if the user has reached the last question then change the text of the button to show the result
                if (currentPosition == questionArrayList.size()) {
                    btn_submit.setText("Show Result ☺");
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Result");
                    builder.setMessage("Your Score is " + correctAnswers + "/" + questionArrayList.size());
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    builder.show();

                } else {
                    btn_submit.setText("Go to Next Question ");
                }
                selectedOption = 0;
            }
        }
    }

    private void answerView(int answer, int drawableView) {
        if (selectedOption != 0) {
            if (answer == 1) {
                tv_option_one.setBackground(ContextCompat.getDrawable(this, drawableView));
            } else if (answer == 2) {
                tv_option_two.setBackground(ContextCompat.getDrawable(this, drawableView));
            } else if (answer == 3) {
                tv_option_three.setBackground(ContextCompat.getDrawable(this, drawableView));
            } else if (answer == 4) {
                tv_option_four.setBackground(ContextCompat.getDrawable(this, drawableView));
            }
        }
    }

}
