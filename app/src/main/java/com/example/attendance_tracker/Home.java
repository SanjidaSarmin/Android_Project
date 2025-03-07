package com.example.attendance_tracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.attendance_tracker.dbUtil.SQLiteDB;
import com.example.attendance_tracker.entity.Category;
import com.example.attendance_tracker.entity.Quiz;
import com.example.attendance_tracker.ui.Hint;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class Home extends AppCompatActivity {
    SQLiteDB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        dbHelper = new SQLiteDB(this);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.executeSQLFromFile(db, this);

        List<Category> categories = dbHelper.getAllCategories();
        List<Quiz> quiz = dbHelper.getAllQuizzes();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        CardView science = findViewById(R.id.science);
        CardView math = findViewById(R.id.math);
        CardView tech = findViewById(R.id.tech);
        CardView sports = findViewById(R.id.sports);
        CardView gk = findViewById(R.id.gk);
        CardView art = findViewById(R.id.art);

        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Pass the categoryId (e.g., 1 for Science) to the Math_Quiz activity
                Intent intent = new Intent(Home.this, All_Quiz.class);
                intent.putExtra("categoryId", 1); // Pass the categoryId for Science
                startActivity(intent);
            }
        });
        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass the categoryId (e.g., 1 for Science) to the Math_Quiz activity
                Intent intent = new Intent(Home.this, All_Quiz.class);
                intent.putExtra("categoryId", 2); // Pass the categoryId for Science
                startActivity(intent);
            }
        });

        tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass the categoryId (e.g., 1 for Science) to the Math_Quiz activity
                Intent intent = new Intent(Home.this, All_Quiz.class);
                intent.putExtra("categoryId", 3); // Pass the categoryId for Science
                startActivity(intent);
            }
        });

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass the categoryId (e.g., 1 for Science) to the Math_Quiz activity
                Intent intent = new Intent(Home.this, All_Quiz.class);
                intent.putExtra("categoryId", 4); // Pass the categoryId for Science
                startActivity(intent);
            }
        });

        gk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass the categoryId (e.g., 1 for Science) to the Math_Quiz activity
                Intent intent = new Intent(Home.this, All_Quiz.class);
                intent.putExtra("categoryId", 5); // Pass the categoryId for Science
                startActivity(intent);
            }
        });

        art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass the categoryId (e.g., 1 for Science) to the Math_Quiz activity
                Intent intent = new Intent(Home.this, All_Quiz.class);
                intent.putExtra("categoryId", 6); // Pass the categoryId for Science
                startActivity(intent);
            }
        });


        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle the clicks without switch case
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    return true;
                } else if (itemId == R.id.nav_search) {
                    Toast.makeText(Home.this, "Hint clicked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Home.this, Hints.class));
                    return true;
                } else if (itemId == R.id.nav_add) {
                    Toast.makeText(Home.this, "Add clicked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Home.this, Quiz_add.class));
                    return true;
                } else if (itemId == R.id.nav_category) {
                    Toast.makeText(Home.this, "Category clicked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Home.this, Category_list.class));
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    Toast.makeText(Home.this, "Profile clicked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Home.this, Profile.class));
                    return true;
                }
                return false;
            }
        });

        queryData();
        queryQuizData();
    }

    private void queryData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Query all columns from the 'users' table
        Cursor cursor = db.query("category", new String[]{"id", "name"},
                null, null, null, null, null);

        // Loop through the results and log them
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            Log.d("DB", "Category: " + id + ", " + name);
        }
        cursor.close();
    }

    private void queryQuizData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Query all columns from the 'quiz' table
        Cursor cursor = db.query("quiz", new String[]{"id", "question", "option_a", "option_b", "option_c", "option_d", "correct_option", "categoryId"},
                null, null, null, null, null);

        // Loop through the results and log them
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String question = cursor.getString(1);
            String optionA = cursor.getString(2);
            String optionB = cursor.getString(3);
            String optionC = cursor.getString(4);
            String optionD = cursor.getString(5);
            String correctOption = cursor.getString(6);
            int categoryId = cursor.getInt(7);

            Log.d("DB", "Quiz: ID=" + id + ", Question=" + question +
                    ", A=" + optionA + ", B=" + optionB + ", C=" + optionC + ", D=" + optionD +
                    ", Correct=" + correctOption + ", CategoryID=" + categoryId);
        }
        cursor.close();
    }

}