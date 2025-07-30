package com.example.benefits_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class PasswordRecoveryActivity extends AppCompatActivity {

    public AppCompatButton buttonBackSingIn, buttonRecovery;
    private EditText recoveryEmailEditText;

    private FirebaseAuth mAuth;

    public void initViews() {
        buttonBackSingIn = findViewById(R.id.buttonBackSingIn);
        buttonRecovery = findViewById(R.id.buttonRecovery);
        recoveryEmailEditText = findViewById(R.id.editTextRecoveryEmail);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);
        initViews();

        mAuth = FirebaseAuth.getInstance();

        // Отримання email з LoginActivity
        String passedEmail = getIntent().getStringExtra("email");
        if (passedEmail != null) {
            recoveryEmailEditText.setText(passedEmail);
        }


        buttonRecovery.setOnClickListener(v -> {
            String email = recoveryEmailEditText.getText().toString().trim();

            if (email.isEmpty()) {
                recoveryEmailEditText.setError("Enter email");
                recoveryEmailEditText.requestFocus();
                return;
            }

            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(PasswordRecoveryActivity.this, "Лист для скидання пароля надіслано", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(PasswordRecoveryActivity.this, LoginActivity.class));
                            finish();
                        } else {
                            Toast.makeText(PasswordRecoveryActivity.this, "Помилка: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        buttonBackSingIn.setOnClickListener(view -> {
            String email = recoveryEmailEditText.getText().toString().trim();
            Intent intent = new Intent(PasswordRecoveryActivity.this, LoginActivity.class);
            intent.putExtra("email", email);
            startActivity(intent);
            finish();
        });
    }
}