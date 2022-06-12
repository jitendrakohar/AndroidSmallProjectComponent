package com.example.authenticationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email,password;
    private Button btnRegister;
    private TextView textLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        email=findViewById(R.id.RegisterEmail);
        password=findViewById(R.id.RegisterPassword);
        btnRegister=findViewById(R.id.Register);
        textLogin=findViewById(R.id.text_login);
        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class)) ;
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
    }
    public void Register(){
        String user=email.getText().toString().trim();
        String pass=password.getText().toString().trim();
        if(user.isEmpty())
        {
            email.setError("Email cannot be Empty");
        }
        if(pass.isEmpty())
        {
            password.setError("password cannot be empty");
        }
        else {
           mAuth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){
                       Toast.makeText(RegisterActivity.this, "User Registered successfully", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                   }
                   else{
                       Toast.makeText(RegisterActivity.this, "Registered Failed "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                   }
               }
           });
        }
    }

}