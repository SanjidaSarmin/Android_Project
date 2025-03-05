package com.example.attendance_tracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        CardView cardView = findViewById(R.id.science);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Card Clicked!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Home.this, Math_Quiz.class));
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle the clicks without switch case
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    Toast.makeText(Home.this, "Home clicked", Toast.LENGTH_SHORT).show();
                    return true;
                }else if (itemId == R.id.nav_search) {
                    Toast.makeText(Home.this, "List clicked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Home.this, Quiz_List.class));
                    return true;
                }else if (itemId == R.id.nav_add) {
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
    }
}