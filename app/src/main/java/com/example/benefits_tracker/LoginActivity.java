package com.example.benefits_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    public Button buttonSignIn;
    public TextView linkToRegister;

    public void initViews() {
        buttonSignIn = findViewById(R.id.buttonLoginSignIn);
        linkToRegister = findViewById(R.id.linkToLoginRegister);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

//        buttonSignIn.setOnClickListener(v -> {
//            // ----
//        });

        linkToRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }
}
