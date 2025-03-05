package com.example.attendance_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.attendance_tracker.dbUtil.SQLiteDB;

public class Category_add extends AppCompatActivity {

    EditText etCategoryName;
    Button btnAddCategory;
    SQLiteDB dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category_add);
        etCategoryName = findViewById(R.id.etCategoryName);
        btnAddCategory = findViewById(R.id.btnAddCategory);
        dbHelper = new SQLiteDB(this);

        btnAddCategory.setOnClickListener(v -> {
            String categoryName = etCategoryName.getText().toString().trim();

            if (!categoryName.isEmpty()) {
                dbHelper.insertCategory(categoryName);
                Toast.makeText(this, "Category Added!", Toast.LENGTH_SHORT).show();
                etCategoryName.setText("");
            } else {
                Toast.makeText(this, "Please enter a category name", Toast.LENGTH_SHORT).show();
            }
            startActivity(new Intent(Category_add.this, Category_list.class));
        });
    }
}