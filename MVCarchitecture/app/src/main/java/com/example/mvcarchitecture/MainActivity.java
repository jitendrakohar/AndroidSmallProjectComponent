package com.example.mvcarchitecture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvcarchitecture.Controller.LoginController;
import com.example.mvcarchitecture.Controller.iLoginController;
import com.example.mvcarchitecture.View.iLoginView;


public class MainActivity extends AppCompatActivity implements iLoginView {
EditText email,password;
Button loginb;
iLoginController loginController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    email=findViewById(R.id.email);
    password=findViewById(R.id.password);
    loginb=findViewById(R.id.loginb);
    loginController=new LoginController(this);
    loginb.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "email "+email.getText().toString().trim(), Toast.LENGTH_SHORT).show();
            loginController.OnLogin(email.getText().toString().trim(),password.getText().toString().trim());

        }
    });


    }

    @Override
    public void OnLoginSuccess(String message) {
        Toast.makeText(this, " "+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnLoginErrror(String message) {
        Toast.makeText(this, " "+message, Toast.LENGTH_SHORT).show();

    }
}