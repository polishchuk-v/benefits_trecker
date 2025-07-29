package com.example.benefits_tracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PasswordRecoveryActivity extends AppCompatActivity {

    public AppCompatButton buttonBackSingIn;

    public void initViews() {
        buttonBackSingIn = findViewById(R.id.buttonBackSingIn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);
        initViews();

        buttonBackSingIn.setOnClickListener(view -> {
            startActivity(new Intent(PasswordRecoveryActivity.this, LoginActivity.class));

        });
    }
}