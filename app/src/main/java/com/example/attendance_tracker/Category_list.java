package com.example.attendance_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.attendance_tracker.dbUtil.SQLiteDB;
import com.example.attendance_tracker.entity.Category;
import com.example.attendance_tracker.ui.Hint;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle the clicks without switch case
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    startActivity(new Intent(Category_list.this, Home.class));
                    return true;
                } else if (itemId == R.id.nav_search) {
                    startActivity(new Intent(Category_list.this, Hints.class));
                    return true;
                } else if (itemId == R.id.nav_add) {
                    startActivity(new Intent(Category_list.this, Quiz_add.class));
                    return true;
                } else if (itemId == R.id.nav_category) {
                    startActivity(new Intent(Category_list.this, Category_list.class));
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    startActivity(new Intent(Category_list.this, Profile.class));
                    return true;
                }
                return false;
            }
        });

        loadCategories(); // Load categories initially

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Category_list.this, Category_add.class));
            }
        });

        lvCategories.setOnItemLongClickListener((parent, view, position, id) -> {
            confirmDeleteCategory(position);
            return true;
        });
        lvCategories.setOnItemClickListener((parent, view, position, id) -> {
            showUpdateDialog(position);
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

    private void confirmDeleteCategory(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Category");
        builder.setMessage("Are you sure you want to delete this category?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            int categoryId = categoryList.get(position).getId();
            dbHelper.deleteCategory(categoryId);
            Toast.makeText(this, "Category Deleted!", Toast.LENGTH_SHORT).show();
            loadCategories();
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    private void showUpdateDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update Category");

        // Create an input field
        final EditText input = new EditText(this);
        input.setText(categoryList.get(position).getName()); // Pre-fill with current name
        builder.setView(input);

        builder.setPositiveButton("Update", (dialog, which) -> {
            String newName = input.getText().toString().trim();
            if (!newName.isEmpty()) {
                int categoryId = categoryList.get(position).getId();
                dbHelper.updateCategory(categoryId, newName);
                Toast.makeText(this, "Category Updated!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Category_list.this, Category_list.class));
                loadCategories(); // Refresh the list
            } else {
                Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }



    @Override
    protected void onResume() {
        super.onResume();
        loadCategories(); // Refresh category list when returning
    }
}