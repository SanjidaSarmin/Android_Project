package com.example.attendance_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.attendance_tracker.dbUtil.SQLiteDB;
import com.example.attendance_tracker.entity.Quiz;

import java.util.Arrays;
import java.util.List;

public class Quiz_add extends AppCompatActivity {

    private EditText etQuestion, etOptionA, etOptionB, etOptionC, etOptionD;
    private Spinner spCorrectOption;
    private Button btnSave;

    private String selectedCorrectOption;
    private Quiz currentQuiz;
    private boolean isEditMode = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz_add);

        etQuestion = findViewById(R.id.etQuestion);
        etOptionA = findViewById(R.id.etOptionA);
        etOptionB = findViewById(R.id.etOptionB);
        etOptionC = findViewById(R.id.etOptionC);
        etOptionD = findViewById(R.id.etOptionD);
        spCorrectOption = findViewById(R.id.spCorrectOption);
        btnSave = findViewById(R.id.btnSave);

        // Options for correct answer
        List<String> optionsList = Arrays.asList("A", "B", "C", "D");

        // Load options into Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, optionsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCorrectOption.setAdapter(adapter);

        spCorrectOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCorrectOption = optionsList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedCorrectOption = null;
            }
        });

        // Check if editing an existing quiz
        if (getIntent().hasExtra("quiz")) {
            isEditMode = true;
            currentQuiz = (Quiz) getIntent().getSerializableExtra("quiz");
            populateFields();
            btnSave.setText("Update Quiz");
        }

        btnSave.setOnClickListener(view -> submitQuiz());
    }

    private void populateFields() {
        if (currentQuiz != null) {
            etQuestion.setText(currentQuiz.getQuestion());
            etOptionA.setText(currentQuiz.getOptionA());
            etOptionB.setText(currentQuiz.getOptionB());
            etOptionC.setText(currentQuiz.getOptionC());
            etOptionD.setText(currentQuiz.getOptionD());

            // Set correct option selection
            List<String> optionsList = Arrays.asList("A", "B", "C", "D");
            int optionPosition = optionsList.indexOf(currentQuiz.getCorrectOption());
            if (optionPosition != -1) {
                spCorrectOption.setSelection(optionPosition);
            }
        }
    }

    SQLiteDB sqLiteDB = new SQLiteDB(this);

    private void submitQuiz() {
        String question = etQuestion.getText().toString().trim();
        String optionA = etOptionA.getText().toString().trim();
        String optionB = etOptionB.getText().toString().trim();
        String optionC = etOptionC.getText().toString().trim();
        String optionD = etOptionD.getText().toString().trim();

        // Validate inputs
        if (question.isEmpty() || optionA.isEmpty() || optionB.isEmpty() || optionC.isEmpty() || optionD.isEmpty() || selectedCorrectOption == null) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEditMode) {
            // Update quiz
            currentQuiz.setQuestion(question);
            currentQuiz.setOptionA(optionA);
            currentQuiz.setOptionB(optionB);
            currentQuiz.setOptionC(optionC);
            currentQuiz.setOptionD(optionD);
            currentQuiz.setCorrectOption(selectedCorrectOption);

            sqLiteDB.updateQuiz(currentQuiz);
            Toast.makeText(this, "Quiz Updated!", Toast.LENGTH_SHORT).show();
        } else {
            // Create new quiz
            Quiz newQuiz = new Quiz(question, optionA, optionB, optionC, optionD, selectedCorrectOption);
            long result = sqLiteDB.insertQuiz(newQuiz);
            Toast.makeText(this, "Quiz Added!", Toast.LENGTH_SHORT).show();
        }

        startActivity(new Intent(Quiz_add.this, Quiz_List.class)); // Redirect to quiz list
    }
}
