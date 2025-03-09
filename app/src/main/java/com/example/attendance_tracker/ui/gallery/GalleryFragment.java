package com.example.attendance_tracker.ui.gallery;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.attendance_tracker.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {
    private SharedPreferences sharedPreferences;

    private FragmentGalleryBinding binding;

    private int score = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Load the saved score from SharedPreferences
        loadSavedScore();


        binding.tvScoreNumber.setText(String.valueOf(score));

        binding.tvClose.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack(); // Close fragment
        });

        return root;
    }

    private void loadSavedScore() {
        SharedPreferences prefs = requireActivity().getSharedPreferences("QuizPrefs", Context.MODE_PRIVATE);
        score = prefs.getInt("totalScore", 0);
    }

    // Save score to SharedPreferences (Call this method when updating the score)
    private void saveScore() {
        SharedPreferences prefs = requireActivity().getSharedPreferences("QuizPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("totalScore", score);
        editor.apply();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}