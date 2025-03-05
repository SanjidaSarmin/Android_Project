package com.example.attendance_tracker;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.attendance_tracker.dbUtil.SQLiteDB;

public class MainActivity extends AppCompatActivity {

    SQLiteDB dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        dbHelper = new SQLiteDB(this);

        // Insert Categories
        dbHelper.insertCategory("Math");
        dbHelper.insertCategory("Science");

        Toast.makeText(this, "Categories Added!", Toast.LENGTH_SHORT).show();
    }
}