package com.example.attendance_tracker.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.attendance_tracker.Profile;
import com.example.attendance_tracker.R;
import com.example.attendance_tracker.databinding.FragmentGalleryBinding;
import com.example.attendance_tracker.databinding.FragmentHintBinding;
import com.example.attendance_tracker.ui.gallery.GalleryViewModel;


public class Hint extends Fragment {

    private FragmentHintBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHintBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.closeButton.setOnClickListener(v -> {
            // Use Intent to go back to the previous Activity
            Intent intent = new Intent(getActivity(), Profile.class);
            startActivity(intent);
            getActivity().finish();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}