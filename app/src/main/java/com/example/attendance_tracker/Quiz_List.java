package com.example.attendance_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.attendance_tracker.dbUtil.SQLiteDB;
import com.example.attendance_tracker.entity.Quiz;

import java.util.List;

public class Quiz_List extends AppCompatActivity {

    private ListView listViewQuizzes;
    private List<Quiz> quizList;
    private ArrayAdapter<String> quizAdapter;
    private SQLiteDB sqLiteDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz_list);

        listViewQuizzes = findViewById(R.id.listViewQuizzes);
        sqLiteDB = new SQLiteDB(this);

        loadQuizzes();

        // Click to Edit Quiz
        listViewQuizzes.setOnItemClickListener((parent, view, position, id) -> {
            Quiz selectedQuiz = quizList.get(position);
            Intent intent = new Intent(Quiz_List.this, Quiz_add.class);
            intent.putExtra("quiz", selectedQuiz);
            startActivity(intent);
        });

        // Long press to Delete Quiz
        listViewQuizzes.setOnItemLongClickListener((parent, view, position, id) -> {
            confirmDelete(position);
            return true;
        });
    }

    private void loadQuizzes() {
        quizList = sqLiteDB.getAllQuizzes();

        if (quizList.isEmpty()) {
            Toast.makeText(this, "No quizzes found!", Toast.LENGTH_SHORT).show();
        } else {
            String[] quizDetails = new String[quizList.size()];
            for (int i = 0; i < quizList.size(); i++) {
                Quiz quiz = quizList.get(i);
                String question = quiz.getQuestion();
                String answer = quiz.getAnswer();  // Assuming there's a method getAnswer() in the Quiz class.
                quizDetails[i] = "Question: " + question + "\nAnswer: " + answer;
            }

            // Use android.R.layout.simple_list_item_2, which has two TextViews (primary and secondary)
            quizAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, android.R.id.text1, quizDetails);
            listViewQuizzes.setAdapter(quizAdapter);
        }
    }


    private void confirmDelete(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Quiz");
        builder.setMessage("Are you sure you want to delete this quiz?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            int quizId = quizList.get(position).getId();
            sqLiteDB.deleteQuiz(quizId);
            Toast.makeText(this, "Quiz Deleted!", Toast.LENGTH_SHORT).show();
            loadQuizzes();
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }
}