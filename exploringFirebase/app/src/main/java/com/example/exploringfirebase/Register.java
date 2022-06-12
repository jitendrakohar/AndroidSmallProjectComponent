package com.example.exploringfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private EditText password, email;
    private Button register;
    private FirebaseAuth auth;

    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        password = findViewById(R.id.etRGPassword);
        email = findViewById(R.id.etRGGmail);
        register = findViewById(R.id.register);
        auth = FirebaseAuth.getInstance();
        tvLogin=findViewById(R.id.tvlogin);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                if (TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1)) {
                    if(TextUtils.isEmpty(email1))
                    email.setError("Email and password cannot be Empty");
                    else
                        password.setError("password cannot be empty");
                } else {
                    regis(email1, password1);
                }
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,login.class));
            }
        });

    }

    private void regis(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Register.this, "Registration successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Register.this,login.class));
                }
                else
                    Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();

            }

        });


    }

}