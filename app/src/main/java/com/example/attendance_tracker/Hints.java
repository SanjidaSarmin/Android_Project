package com.example.attendance_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Hints extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hints);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle the clicks without switch case
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    startActivity(new Intent(Hints.this, Home.class));
                    return true;
                } else if (itemId == R.id.nav_search) {
                    startActivity(new Intent(Hints.this, Hints.class));
                    return true;
                } else if (itemId == R.id.nav_add) {
                    startActivity(new Intent(Hints.this, Quiz_add.class));
                    return true;
                } else if (itemId == R.id.nav_category) {
                    startActivity(new Intent(Hints.this, Category_list.class));
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    startActivity(new Intent(Hints.this, Profile.class));
                    return true;
                }
                return false;
            }
        });
    }
}