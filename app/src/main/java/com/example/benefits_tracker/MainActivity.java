package com.example.benefits_tracker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.benefits_tracker.fragments.AddBenefitsActivity;
import com.example.benefits_tracker.fragments.BenefitsFragment;
import com.example.benefits_tracker.fragments.StatisticsFragment;
import com.example.benefits_tracker.fragments.TasksFragment;
import com.example.benefits_tracker.fragments.TodayFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnClickListener(item -> {
            Fragment selectedFragment = null;

            switch (item.getId()) {
                case R.id.nav_today:
                    selectedFragment = new TodayFragment();
                    break;
                case R.id.nav_benefits:
                    selectedFragment = new BenefitsFragment();
                    break;
                case R.id.nav_tasks:
                    selectedFragment = new TasksFragment();
                    break;
                case R.id.nav_stats:
                    selectedFragment = new StatisticsFragment();
                    break;
                case R.id.nav_add:
                    showAddBenefitsDialog(); // або startActivity(new Intent(...));
                    return true;
            }

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit();

            return true;
        });

        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.nav_today);
        }
    }

    private void showAddBenefitsDialog() {
        AddBenefitsActivity dialog = new AddBenefitsActivity();
        dialog.show(getSupportFragmentManager(), "AddBenefitDialog");
    }
}
