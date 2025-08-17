package com.example.benefits_tracker;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    private TextView textStartDate;
    private TextView textEndDate;
    private ImageButton btnPickStartDate;
    private Spinner spinnerCategory;
    private Switch dateSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        // --- Toolbar ---
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(AddActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });

        // --- Views ---
        textStartDate = findViewById(R.id.text_start_date);
        btnPickStartDate = findViewById(R.id.btn_pick_start_date);
        spinnerCategory = findViewById(R.id.spinner_category);
        dateSwitch = findViewById(R.id.switch_end_date);
        textEndDate = findViewById(R.id.text_end_date);

//      Текст для кінцевої дати (можна додати в layout окремо під Switch)
//      textEndDate = new TextView(this);
//      textEndDate.setText("");
//      textEndDate.setTextColor(getResources().getColor(R.color.black));

        // --- Start Date ---
        btnPickStartDate.setOnClickListener(v -> showDatePickerDialog(true, false));

        // --- Spinner з категоріями ---
        List<CategoryItem> categoryList = new ArrayList<>();
        categoryList.add(new CategoryItem("Побут", R.drawable.ic_nine));
        categoryList.add(new CategoryItem("Спорт", R.drawable.ic_ten));
        categoryList.add(new CategoryItem("Відпочинок", R.drawable.ic_five));
        categoryList.add(new CategoryItem("Їжа", R.drawable.ic_four));
        categoryList.add(new CategoryItem("Навчання", R.drawable.ic_two));
        categoryList.add(new CategoryItem("Здоров'я", R.drawable.ic_eight));
        categoryList.add(new CategoryItem("Інше", R.drawable.ic_three));

        CategoryAdapter adapter = new CategoryAdapter(this, categoryList);
        spinnerCategory.setAdapter(adapter);

        // --- End Date Switch ---
        dateSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                showDatePickerDialog(false, true);
            } else {
                textEndDate.setText("");
            }
        });

        // --- Create button ---
        Button btnCreate = findViewById(R.id.btn_create);
        btnCreate.setOnClickListener(v -> {
            // TODO: Логіка створення звички
        });
    }

    private void showDatePickerDialog(boolean isStartDate, boolean isEndDate) {
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

            if (isStartDate) {
                textStartDate.setText(date);
            } else {
                textEndDate.setText(date);
            }

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

    public static Intent newIntent(Context context) {
        return new Intent(context, AddActivity.class);
    }
}
