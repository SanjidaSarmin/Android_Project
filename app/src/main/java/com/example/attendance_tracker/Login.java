package com.example.attendance_tracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvForgotPassword, tvSignupLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvSignupLink = findViewById(R.id.tvSignupLink);

        // Retrieve login state from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "No Name Found");
        int isLogin = sharedPreferences.getInt("isLogin", 0);

        // If user is already logged in, redirect to Home
        if (isLogin > 0) {
            Toast.makeText(Login.this, "Welcome back, " + username + "!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Login.this, Home.class));
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(email, password, sharedPreferences);
                }
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this, "Forgot Password clicked", Toast.LENGTH_SHORT).show();
            }
        });

        tvSignupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser(String email, String password, SharedPreferences sharedPreferences) {

        if (email.equals("sanjida@gmail.com") && password.equals("pass123")) {
            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

            // Save login state in SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", email);
            editor.putInt("isLogin", 1);
            editor.apply();

            startActivity(new Intent(Login.this, Home.class));
            finish();
        } else {
            Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
        }
    }
}
