package com.example.benefits_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class CalendarActivity extends AppCompatActivity {

    private LinearLayout linearLayoutMain;

    private CalendarView calendarView;
    private ListView taskListView;

    // Зразок даних: завдання для кожної дати у форматі "dd/MM/yyyy"
    private HashMap<String, ArrayList<String>> tasksByDate = new HashMap<>();
    private ArrayList<String> currentTasks = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    public void initViews() {
        linearLayoutMain = findViewById(R.id.linearLayoutMain);

        calendarView = findViewById(R.id.calendarView);
        taskListView = findViewById(R.id.tasklist);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initViews();

        linearLayoutMain.setOnClickListener(view -> {
            startActivity(new Intent(CalendarActivity.this, MainActivity.class));
            finish();
        });

//        addTaskButton.setOnClickListener(view -> {
//            startActivity(new Intent(CalendarActivity.this, AddActivity.class));
//        });



        // Ініціалізуємо адаптер для ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, currentTasks);
        taskListView.setAdapter(adapter);

        // Заповнюємо приклад завдань
        initSampleTasks();

        // Встановлюємо початкові завдання для поточної дати
        long todayMillis = calendarView.getDate();
        updateTasksForDate(todayMillis);

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = String.format("%02d/%02d/%d", dayOfMonth, month + 1, year);
            Toast.makeText(CalendarActivity.this, "Обрана дата: " + selectedDate, Toast.LENGTH_SHORT).show();
            updateTasksForKey(selectedDate);
        });
    }

    private void initSampleTasks() {
        ArrayList<String> tasksForToday = new ArrayList<>();
        tasksForToday.add("Купити продукти");
        tasksForToday.add("Закінчити проект");
        tasksByDate.put(getTodayDateKey(), tasksForToday);

        ArrayList<String> tasksForOtherDay = new ArrayList<>();
        tasksForOtherDay.add("Зустріч з друзями");
        tasksByDate.put("15/08/2025", tasksForOtherDay);
    }

    private String getTodayDateKey() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new java.util.Date());
    }

    private void updateTasksForDate(long millis) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String dateKey = sdf.format(new java.util.Date(millis));
        updateTasksForKey(dateKey);
    }

    private void updateTasksForKey(String dateKey) {
        currentTasks.clear();
        if (tasksByDate.containsKey(dateKey)) {
            currentTasks.addAll(tasksByDate.get(dateKey));
        } else {
            currentTasks.add("Завдань немає");
        }
        adapter.notifyDataSetChanged();
    }
}
