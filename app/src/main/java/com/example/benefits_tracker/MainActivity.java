package com.example.benefits_tracker;

import android.os.Bundle;
import android.widget.ListView;
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
        tasks.add(new TaskItem("Drink Water", R.drawable.ic_water, false));
        tasks.add(new TaskItem("Read Pages", R.drawable.ic_book, false));
        tasks.add(new TaskItem("Workout Session", R.drawable.ic_workout, false));
        tasks.add(new TaskItem("Doctor Appointment", R.drawable.ic_doctor, false));
        tasks.add(new TaskItem("Call Parents Weekly", R.drawable.ic_call, false));

        TaskAdapter adapter = new TaskAdapter(this, tasks);
        checklist.setAdapter(adapter);
    }
}
