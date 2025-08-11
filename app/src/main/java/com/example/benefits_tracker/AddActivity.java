package com.example.benefits_tracker;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    private TextView textStartDate;
    private ImageButton btnPickStartDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        textStartDate = findViewById(R.id.text_start_date);
        btnPickStartDate = findViewById(R.id.btn_pick_start_date);

        btnPickStartDate.setOnClickListener(v -> showDatePickerDialog());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> {
            startActivity(new Intent(AddActivity.this, MainActivity.class));
            finish();
        });
        
        Button btnCreate = findViewById(R.id.btn_create);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Тут буде логіка створення звички
                // Наприклад, збереження даних або перехід на інший екран
            }
        });
    }

    private void showDatePickerDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_calendar);

        DatePicker datePicker = dialog.findViewById(R.id.datePicker);
        Button btnOk = dialog.findViewById(R.id.btn_ok);

        Calendar calendar = Calendar.getInstance();
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null);

        btnOk.setOnClickListener(view -> {
            int year = datePicker.getYear();
            int month = datePicker.getMonth();
            int day = datePicker.getDayOfMonth();

            String date = String.format("%02d %s %d", day, getMonthName(month), year);
            textStartDate.setText(date);

            dialog.dismiss();
        });

        dialog.show();
    }

    private String getMonthName(int monthIndex) {
        String[] months = {
                "січ.", "лют.", "бер.", "квіт.", "трав.", "черв.",
                "лип.", "серп.", "вер.", "жовт.", "лист.", "груд."
        };
        return months[monthIndex];
    }
}