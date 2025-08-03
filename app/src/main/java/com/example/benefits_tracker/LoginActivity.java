package com.example.benefits_tracker;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private boolean isPasswordInVisible = false;
    private ImageView toggleImageView;
    public TextView forgotPassword;
    public Button buttonSignIn;
    public TextView linkToRegister;

    private FirebaseAuth mAuth;

    public void initViews() {
        passwordEditText = findViewById(R.id.editTextLoginPassword);
        toggleImageView = findViewById(R.id.imageViewToggleLoginPassword);
        forgotPassword = findViewById(R.id.linkToForgotPassword);
        buttonSignIn = findViewById(R.id.buttonLoginSignIn);
        linkToRegister = findViewById(R.id.linkToLoginRegister);
        emailEditText = findViewById(R.id.editTextLoginEmail);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

        mAuth = FirebaseAuth.getInstance();

        // Повертаємо email із PasswordRecoveryActivity, якщо він є
        String emailFromRecovery = getIntent().getStringExtra("email");
        if (emailFromRecovery != null) {
            emailEditText.setText(emailFromRecovery);
        }

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
            String email = emailEditText.getText().toString().trim();
            Intent intent = new Intent(LoginActivity.this, PasswordRecoveryActivity.class);
            intent.putExtra("email", email);
            startActivity(intent);
        });

        buttonSignIn.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailEditText.setError("Enter the correct email address");
                emailEditText.requestFocus();
                return;
            }

            if (password.isEmpty() || password.length() < 6) {
                passwordEditText.setError("The password must be at least 6 characters long");
                passwordEditText.requestFocus();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Вхід успішний", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));
                            finish(); //
                        } else {
                            Toast.makeText(LoginActivity.this, "Помилка входа: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });


        linkToRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

        buttonSignIn.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        });
    }
}
