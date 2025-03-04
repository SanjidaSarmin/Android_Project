package com.example.attendance_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Signup extends AppCompatActivity {

    EditText etName, etEmail, etPassword;
    Button btnSignup;
    TextView tvLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignup = findViewById(R.id.btnSignup);
        tvLoginLink = findViewById(R.id.tvLoginLink);

        // Set up the Sign Up button click listener
        btnSignup.setOnClickListener(v -> {
            // Logic for Sign Up
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            // Here you can add your logic to register the user, like storing details in a database

            // For now, let's just print the data in Log or proceed to login
            // You might want to replace this with actual sign up logic
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                // Handle empty fields
                return;
            }

            // You could also show a loading indicator while the registration process occurs

            // After sign up, navigate to the login screen
            Intent intent = new Intent(Signup.this, Login.class);
            startActivity(intent);
        });

        // Set up the Login link click listener
        tvLoginLink.setOnClickListener(v -> {
            // Redirect to Login screen if the user already has an account
            Intent intent = new Intent(Signup.this, Login.class);
            startActivity(intent);
        });
    }
}
