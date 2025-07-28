package com.example.benefits_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    public Button buttonSignUp;
    public TextView linkToLogin;

    public void initViews(){
        buttonSignUp = findViewById(R.id.buttonRegisterSignUp);
        linkToLogin = findViewById(R.id.linkToLoginRegister);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();

//        buttonSignUp.setOnClickListener(v -> {
//            // ----
//        });

        linkToLogin.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }
}
