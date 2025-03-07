package com.example.attendance_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.attendance_tracker.dbUtil.SQLiteDB;
import com.example.attendance_tracker.entity.Category;
import com.example.attendance_tracker.entity.Quiz;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quiz_add extends AppCompatActivity {

    private EditText etQuestion, etOptionA, etOptionB, etOptionC, etOptionD;
    private Spinner spCorrectOption, spCategory;
    private Button btnSave;

    private String selectedCorrectOption;
    private int selectedCategoryId;
    private Quiz currentQuiz;
    private boolean isEditMode = false;
    private List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz_add);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle the clicks without switch case
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    startActivity(new Intent(Quiz_add.this, Home.class));
                    return true;
                } else if (itemId == R.id.nav_search) {
                    startActivity(new Intent(Quiz_add.this, Hints.class));
                    return true;
                } else if (itemId == R.id.nav_add) {
                    startActivity(new Intent(Quiz_add.this, Quiz_add.class));
                    return true;
                } else if (itemId == R.id.nav_category) {
                    startActivity(new Intent(Quiz_add.this, Category_list.class));
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    startActivity(new Intent(Quiz_add.this, Profile.class));
                    return true;
                }
                return false;
            }
        });

        etQuestion = findViewById(R.id.etQuestion);
        etOptionA = findViewById(R.id.etOptionA);
        etOptionB = findViewById(R.id.etOptionB);
        etOptionC = findViewById(R.id.etOptionC);
        etOptionD = findViewById(R.id.etOptionD);
        spCategory = findViewById(R.id.spCategory);
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
        loadCategories();

        // Check if editing an existing quiz
        if (getIntent().hasExtra("quiz")) {
            isEditMode = true;
            currentQuiz = (Quiz) getIntent().getSerializableExtra("quiz");
            populateFields();
            btnSave.setText("Update Quiz");
        }

        btnSave.setOnClickListener(view -> submitQuiz());
    }

    private void loadCategories() {
        categoryList = sqLiteDB.getAllCategories();

        List<String> categoryNames = new ArrayList<>();
        for (Category category : categoryList) {
            Log.d("Category", "-----ID: " + category.getId() + " Name: " + category.getName());
            categoryNames.add(category.getName());
        }

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryNames);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(categoryAdapter);

        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategoryId = categoryList.get(position).getId(); // Get the category ID
                Log.d("Selected Category", "----Category ID: " + selectedCategoryId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedCategoryId = -1; // Handle case where no category is selected
            }
        });

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
        if (question.isEmpty() || optionA.isEmpty() || optionB.isEmpty() || optionC.isEmpty() || optionD.isEmpty()
                || selectedCorrectOption == null || selectedCategoryId == -1) {
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
            currentQuiz.setCategoryId(selectedCategoryId);

            sqLiteDB.updateQuiz(currentQuiz);
            Toast.makeText(this, "Quiz Updated!", Toast.LENGTH_SHORT).show();
        } else {
            // Create new quiz
            Quiz newQuiz = new Quiz(question, optionA, optionB, optionC, optionD, selectedCorrectOption, selectedCategoryId);
            long result = sqLiteDB.insertQuiz(newQuiz);
            if (result == -1) {
                Toast.makeText(this, "Error adding quiz", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Quiz Added!", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Quiz Added!", Toast.LENGTH_SHORT).show();
        }

        startActivity(new Intent(Quiz_add.this, Quiz_List.class)); // Redirect to quiz list
    }
}
