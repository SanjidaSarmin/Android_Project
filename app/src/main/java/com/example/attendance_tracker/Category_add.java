package com.example.attendance_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.attendance_tracker.dbUtil.SQLiteDB;
import com.example.attendance_tracker.ui.Hint;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle the clicks without switch case
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    startActivity(new Intent(Category_add.this, Home.class));
                    return true;
                } else if (itemId == R.id.nav_search) {
                    startActivity(new Intent(Category_add.this, Hints.class));
                    return true;
                } else if (itemId == R.id.nav_add) {
                    startActivity(new Intent(Category_add.this, Quiz_add.class));
                    return true;
                } else if (itemId == R.id.nav_category) {
                    startActivity(new Intent(Category_add.this, Category_list.class));
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    startActivity(new Intent(Category_add.this, Profile.class));
                    return true;
                }
                return false;
            }
        });

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