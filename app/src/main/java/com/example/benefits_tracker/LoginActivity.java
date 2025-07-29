package com.example.benefits_tracker;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    public Button buttonSignIn;
    public TextView linkToRegister;
    private EditText passwordEditText;
    private ImageView toggleImageView;
    private boolean isPasswordInVisible = false;

    public TextView forgotPassword;

    public void initViews() {
        buttonSignIn = findViewById(R.id.buttonLoginSignIn);
        linkToRegister = findViewById(R.id.linkToLoginRegister);
        passwordEditText = findViewById(R.id.editTextLoginPassword);
        toggleImageView = findViewById(R.id.imageViewToggleLoginPassword);
        forgotPassword = findViewById(R.id.linkToForgotPassword);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

        toggleImageView.setOnClickListener(v -> {
            // Збереження шрифту перед натисканням на "око"
            Typeface typeface = passwordEditText.getTypeface();

            if (isPasswordInVisible) {
                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                toggleImageView.setImageResource(R.drawable.ic_eye_off);
            } else {
                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                toggleImageView.setImageResource(R.drawable.ic_eye);
            }
            // Повернення шрифту після взаємодії з "оком"
            passwordEditText.setTypeface(typeface);

            isPasswordInVisible = !isPasswordInVisible;

            // Позиціюємо курсор в кінець тексту
            passwordEditText.setSelection(passwordEditText.length());
        });

        forgotPassword.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, PasswordRecoveryActivity.class));
        });

//        buttonSignIn.setOnClickListener(v -> {
//            // ----
//        });

        linkToRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }
}
