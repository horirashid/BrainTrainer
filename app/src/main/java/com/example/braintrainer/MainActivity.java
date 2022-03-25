/********************************************
 *  Author: Hori Jawad Rashid               *
 *  Date created: March 25, 2022            *
 *  Master student at Hiroshima University  *
 ********************************************/


package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    Button startButton, button0, button1, button2, button3, playAgainButton;
    TextView resultTextView, sumTextView, scoreTextView,timerTextView;
    ConstraintLayout gameLayout;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;

    String str;
    int score = 0;
    int numberOfQuestions = 0;

    //******************************************* start function
    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        // any view not important just needs an argument so we use that one
        playAgain(findViewById(R.id.timerTextView));
    }
    //*******************************************  chooseAnswer function
    public void chooseAnswer(View view) {
        // Log.i("Tag", view.getTag().toString());
        /*int userAnswerLoc = (int) view.getTag();
        if (locationOfCorrectAnswer == userAnswerLoc) {*/

        //while (true) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
                str = "Correct!";
                score++;
            } else {
                str = "Wrong :(";
            }
            numberOfQuestions++;
            resultTextView.setText(str);
            scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        //}
        random();
    }
    //******************************************* random function
    public void random() {
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        // clear the array
        answers.clear();
        for (int i=0; i<4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == (a + b)) {
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    //******************************************* playAgain function
    public void playAgain( View view) {
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        random();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000) + "s");
            }
            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    //******************************************* onCreate function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = findViewById(R.id.sumTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        resultTextView = findViewById(R.id.resultTextView);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);

        startButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}