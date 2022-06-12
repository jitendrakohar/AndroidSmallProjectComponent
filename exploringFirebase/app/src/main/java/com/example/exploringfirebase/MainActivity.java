package com.example.exploringfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
TextView tvUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvUser=findViewById(R.id.tvUser);
        Intent i=getIntent();
        String email=i.getStringExtra("email");
        tvUser.setText("Hello "+ email+ ", "+tvUser.getText());

    }
}