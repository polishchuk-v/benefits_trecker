package com.example.benefits_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView checklist = findViewById(R.id.checklist);

        List<TaskItem> tasks = new ArrayList<>();
        tasks.add(new TaskItem("Drink Water", R.drawable.ic_four, false));
        tasks.add(new TaskItem("Read Pages", R.drawable.ic_eight, false));
        tasks.add(new TaskItem("Workout Session", R.drawable.ic_five, false));
        tasks.add(new TaskItem("Doctor Appointment", R.drawable.ic_four, false));
        tasks.add(new TaskItem("Call Parents Weekly", R.drawable.ic_one, false));

        TaskAdapter adapter = new TaskAdapter(this, tasks);
        checklist.setAdapter(adapter);

        LinearLayout menuButton = findViewById(R.id.topRightMenu);
        menuButton.setOnClickListener(this::showPopupMenu);
    }

    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(menuItem -> {
            if (menuItem.getItemId() == R.id.menu_item_quit) {
                // Perform logout action
                logout();
                return true;
            }
            return false;
        });

        popupMenu.show();
    }

    private void logout() {
        // Here you can implement what happens when the user logs out.
        // For example, you might want to navigate to the login screen.

        // Example: Navigate to WelcomeActivity
        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Close the current activity
    }
}
