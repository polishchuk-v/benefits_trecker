package com.example.benefits_tracker;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText, passwordEditText;
    private ImageView toggleImageView;
    private boolean isPasswordInVisible = false;
    private Button buttonSignUp;
    private TextView linkToLogin;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    public void initViews() {
        nameEditText = findViewById(R.id.editTextRegisterName);
        emailEditText = findViewById(R.id.editTextRegisterEmail);
        passwordEditText = findViewById(R.id.editTextRegisterPassword);
        toggleImageView = findViewById(R.id.imageViewToggleRegisterPassword);
        buttonSignUp = findViewById(R.id.buttonRegisterSignUp);
        linkToLogin = findViewById(R.id.linkToRegisterLogin);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

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

        buttonSignUp.setOnClickListener(v -> {
            String username = nameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (username.isEmpty()) {
                nameEditText.setError("Enter name");
                nameEditText.requestFocus();
                return;
            }

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

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();


                            Toast.makeText(RegisterActivity.this, "Реєстрація успішна", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            finish();


                            Map<String, Object> userData = new HashMap<>();
                            userData.put("username", username);
                            userData.put("email", email);
                            userData.put("uid", user.getUid());

                            db.collection("users").document(user.getUid()).set(userData)
                                    .addOnFailureListener(e -> {
                                        // Це повідомлення користувач не побачить, бо вже на іншій сторінці
                                        Log.e("FirestoreError", "Помилка збереження даних: " + e.getMessage());
                                    });
                        } else {

                            Exception exception = task.getException();
                            if (exception instanceof com.google.firebase.auth.FirebaseAuthUserCollisionException) {
                                Toast.makeText(RegisterActivity.this, "Цей email вже використовується", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Помилка реєстрації: " + exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        });

        linkToLogin.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });


    }
}
