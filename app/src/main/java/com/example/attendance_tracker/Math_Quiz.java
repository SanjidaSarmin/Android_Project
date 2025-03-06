package com.example.attendance_tracker;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.attendance_tracker.dbUtil.SQLiteDB;
import com.example.attendance_tracker.entity.Quiz;

import java.util.List;
import java.util.Random;
import java.util.logging.Handler;

public class Math_Quiz extends AppCompatActivity {
    private int score = 0;
    private int questionIndex = 0;
    private int categoryId;
    private Quiz currentQuiz;
    private SQLiteDB dbHelper;
    private CountDownTimer countDownTimer;

    // UI components
    private TextView questionTextView, scoreTextView, timeTextView;
    private Button btnOption1, btnOption2, btnOption3, btnOption4;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_math_quiz);

        // Initialize the UI components
        questionTextView = findViewById(R.id.questionTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timeTextView = findViewById(R.id.timeTextView);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);
        progressBar = findViewById(R.id.progressBar);

        dbHelper = new SQLiteDB(this);

        categoryId = getIntent().getIntExtra("categoryId", -1);

        if (categoryId == -1) {
            Toast.makeText(this, "Invalid category", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        loadNextQuestion();

        // Set up button listeners for options
        btnOption1.setOnClickListener(view -> checkAnswer(btnOption1));
        btnOption2.setOnClickListener(view -> checkAnswer(btnOption2));
        btnOption3.setOnClickListener(view -> checkAnswer(btnOption3));
        btnOption4.setOnClickListener(view -> checkAnswer(btnOption4));
    }

    // Load next question from the database
    private void loadNextQuestion() {
        int categoryId = getIntent().getIntExtra("categoryId", -1);
        Log.d("Math_Quiz", "Category ID: " + categoryId);
        if (categoryId == -1) {
            Toast.makeText(this, "Invalid category ID!", Toast.LENGTH_SHORT).show();
            return;
        }

        List<Quiz> quizzes = dbHelper.getQuizzesByCategory(categoryId);
        Log.d("Math_Quiz", "Number of quizzes found: " + quizzes.size());

        if (quizzes.size() > 0) {
            currentQuiz = quizzes.get(new Random().nextInt(quizzes.size()));  // Randomly select a quiz from the list
            // Set question text
            questionTextView.setText(currentQuiz.getQuestion());

            // Set answer options
            btnOption1.setText(currentQuiz.getOptionA());
            btnOption2.setText(currentQuiz.getOptionB());
            btnOption3.setText(currentQuiz.getOptionC());
            btnOption4.setText(currentQuiz.getOptionD());

            // Start or reset the countdown timer
            startTimer();

            // Update score display
            scoreTextView.setText("Score: " + score);
        } else {
            Toast.makeText(this, "No question found for this category", Toast.LENGTH_SHORT).show();
        }
    }


    // Get a random quiz question from the database
    private Quiz getRandomQuiz(int categoryId) {
        List<Quiz> quizzes = dbHelper.getQuizzesByCategory(categoryId);
        if (quizzes.size() > 0) {
            Random random = new Random();
            return quizzes.get(random.nextInt(quizzes.size()));
        }
        return null;
    }
    private void startTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int secondsLeft = (int) (millisUntilFinished / 1000);
                timeTextView.setText(secondsLeft + " sec");

                // ✅ Decrease smoothly
                progressBar.setProgress(secondsLeft+ 8);


            }

            @Override
            public void onFinish() {
                timeTextView.setText("0 sec");
                progressBar.setProgress(0);
                loadNextQuestion();  // Move to the next question
            }
        }.start();
    }


    // Check if the selected answer is correct
    private void checkAnswer(Button selectedButton) {
        // Get the text of the selected answer
        String selectedAnswer = selectedButton.getText().toString().trim();


        String correctAnswer = currentQuiz.getCorrectOption().trim();


        boolean isCorrect = false;


        switch (correctAnswer) {
            case "A":
                isCorrect = selectedAnswer.equalsIgnoreCase(currentQuiz.getOptionA());
                break;
            case "B":
                isCorrect = selectedAnswer.equalsIgnoreCase(currentQuiz.getOptionB());
                break;
            case "C":
                isCorrect = selectedAnswer.equalsIgnoreCase(currentQuiz.getOptionC());
                break;
            case "D":
                isCorrect = selectedAnswer.equalsIgnoreCase(currentQuiz.getOptionD());
                break;
        }


        int color = isCorrect ? getResources().getColor(R.color.correctAnswerColor) : getResources().getColor(R.color.incorrectAnswerColor);
        selectedButton.setBackgroundColor(color);

        // Optionally, animate the background color change for smooth transition
        ObjectAnimator.ofArgb(selectedButton, "backgroundColor", color, color)
                .setDuration(500) // 500ms duration for the animation
                .start();


        if (isCorrect) {
            score++;  // Increase score if answer is correct
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
        }


        scoreTextView.setText("SCORE: " + score);

        // Move to next question after a short delay
//        new Handler().postDelayed(() -> {
//            currentQuestionIndex++;
//            loadNextQuiz();
//        }, 1000);
    }



}
