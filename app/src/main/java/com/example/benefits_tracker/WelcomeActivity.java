package com.example.benefits_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    public Button buttonLogin;
    public TextView registerLink;

    public void initViews() {
        buttonLogin = findViewById(R.id.buttonLoginWelcome);
        registerLink = findViewById(R.id.linkToRegister);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initViews();

        //Перехід на сторінку login
        buttonLogin.setOnClickListener(v ->  {
            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        });

        //Перехід на сторінку register
        registerLink.setOnClickListener(v -> {
            startActivity(new Intent(WelcomeActivity.this, RegisterActivity.class));
        });
    }
}

