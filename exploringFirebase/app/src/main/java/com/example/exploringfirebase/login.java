package com.example.exploringfirebase;

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

public class login extends AppCompatActivity {
EditText gmail,password;
Button login;;
TextView tvRegister;
FirebaseAuth auth=FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    gmail=findViewById(R.id.etGmail);
    password=findViewById(R.id.etPassword);
    login=findViewById(R.id.login);
    tvRegister=findViewById(R.id.tvRegister);
    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String sGmail=gmail.getText().toString();
            String sPassword=password.getText().toString();
//            Toast.makeText(login.this, "Login pressed", Toast.LENGTH_SHORT).show();

            login(sGmail,sPassword);
        }
    });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,Register.class));
            }
        });
    }
    private void login(String gmail,String password){
        auth.signInWithEmailAndPassword(gmail,password).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
           if(task.isSuccessful()){
               Toast.makeText(login.this, "Login Successfully", Toast.LENGTH_SHORT).show();

               Intent i=new Intent(login.this,MainActivity.class);
               i.putExtra("email",gmail);
               i.putExtra("password",password);
               startActivity(i);
           }
           else{
               Toast.makeText(login.this, "Login failed", Toast.LENGTH_SHORT).show();
           }
            }
        });

    }


}