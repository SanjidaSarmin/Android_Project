package com.example.attendance_tracker.ui.logout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendance_tracker.Login;
import com.example.attendance_tracker.R;
import com.example.attendance_tracker.databinding.FragmentGalleryBinding;
import com.example.attendance_tracker.databinding.FragmentLogoutBinding;
import com.example.attendance_tracker.ui.gallery.GalleryViewModel;


public class Logout extends Fragment {
    private FragmentLogoutBinding binding;
    private SharedPreferences sharedPreferences;
    private TextView uName, uId, txt;
    private Button btnLogout;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLogoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        btnLogout = binding.logoutButton;

        // Get SharedPreferences using requireActivity()
        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Retrieve stored values
        String username = sharedPreferences.getString("username", "Guest");
        int userId = sharedPreferences.getInt("user_id", 0);



        // Logout button click listener
        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            Toast.makeText(requireActivity(), "Log Out Called!!!", Toast.LENGTH_SHORT).show();
            // Redirect to LoginActivity and clear back stack
            Intent intent = new Intent(requireActivity(), Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}