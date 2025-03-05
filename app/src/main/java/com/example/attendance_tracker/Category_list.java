package com.example.attendance_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.attendance_tracker.dbUtil.SQLiteDB;

import java.util.List;

public class Category_list extends AppCompatActivity {

    ListView lvCategories;
    SQLiteDB dbHelper;
    List<String> categoryList;
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
            categoryList = dbHelper.getAllCategories();

            // Using ArrayAdapter instead of custom adapter
            arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categoryList);
            lvCategories.setAdapter(arrayAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Category_list.this, Category_add.class));
            }
        });
        }
    }