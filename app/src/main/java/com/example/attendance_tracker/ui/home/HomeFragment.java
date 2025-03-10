package com.example.attendance_tracker.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.attendance_tracker.Category_list;
import com.example.attendance_tracker.Hints;
import com.example.attendance_tracker.Home;
import com.example.attendance_tracker.Profile;
import com.example.attendance_tracker.Quiz_List;
import com.example.attendance_tracker.Quiz_add;
import com.example.attendance_tracker.R;
import com.example.attendance_tracker.databinding.FragmentHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        binding.progress1.setProgress(60);
//        binding.progress2.setProgress(80);
//        binding.progress3.setProgress(50);

        BottomNavigationView bottomNavigationView = binding.bottomNav;

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle the clicks without switch case
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    startActivity(new Intent(getActivity(), Home.class));
                    return true;
                }else if (itemId == R.id.nav_search) {
                    startActivity(new Intent(getActivity(), Hints.class));
                    return true;
                }else if (itemId == R.id.nav_add) {
                    startActivity(new Intent(getActivity(), Quiz_add.class));
                    return true;
                } else if (itemId == R.id.nav_category) {
                    startActivity(new Intent(getActivity(), Category_list.class));
                    return true;
                }  else if (itemId == R.id.nav_profile) {
                    startActivity(new Intent(getActivity(), Profile.class));
                    return true;
                }
                return false;
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}