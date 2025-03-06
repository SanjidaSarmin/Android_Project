package com.example.attendance_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.attendance_tracker.dbUtil.SQLiteDB;
import com.example.attendance_tracker.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class Category_list extends AppCompatActivity {

    ListView lvCategories;
    SQLiteDB dbHelper;
    List<Category> categoryList;
    ArrayAdapter<String> arrayAdapter;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category_list);

        btnAdd = findViewById(R.id.btnAddCategory);
        lvCategories = findViewById(R.id.lvCategories);
        dbHelper = new SQLiteDB(this);

        loadCategories(); // Load categories initially

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Category_list.this, Category_add.class));
            }
        });
    }

    private void loadCategories() {
        categoryList = dbHelper.getAllCategories(); // Get categories from DB

        List<String> categoryNames = new ArrayList<>();
        for (Category category : categoryList) {
            categoryNames.add(category.getName()); // Extract category names
        }

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categoryNames);
        lvCategories.setAdapter(arrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCategories(); // Refresh category list when returning
    }
}