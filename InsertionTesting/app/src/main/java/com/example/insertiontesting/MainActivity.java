package com.example.insertiontesting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
   DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db= new DBHelper(MainActivity.this);
        db.insertUserData("jitendra",18);
        setContentView(R.layout.activity_main);
    }
}