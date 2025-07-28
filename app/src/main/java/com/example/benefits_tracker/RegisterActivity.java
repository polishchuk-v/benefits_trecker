package com.example.benefits_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private Button buttonSignUp;
    private TextView linkToLogin;
    private EditText passwordEditText;
    private ImageView toggleImageView;
    private boolean isPasswordInVisible = false;

    public void initViews() {
        buttonSignUp = findViewById(R.id.buttonRegisterSignUp);
        linkToLogin = findViewById(R.id.linkToLoginRegister);
        passwordEditText = findViewById(R.id.editTextLoginPassword);
        toggleImageView = findViewById(R.id.imageViewTogglePassword);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();

        toggleImageView.setOnClickListener(v -> {
            if (isPasswordInVisible) {
                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                toggleImageView.setImageResource(R.drawable.ic_eye_off);
            } else {
                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                toggleImageView.setImageResource(R.drawable.ic_eye);
            }
            isPasswordInVisible = !isPasswordInVisible;

            // Позиціюємо курсор в кінець тексту
            passwordEditText.setSelection(passwordEditText.length());
        });

        buttonSignUp.setOnClickListener(v -> {
            // ----
        });

        linkToLogin.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }
}
