package com.example.mvcarchitecture.Model;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import com.example.mvcarchitecture.MainActivity;

public class User implements iUser {
    private String email, password;

    public User(String email, String password) {
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public int isValid() {
        if (TextUtils.isEmpty(getEmail()))
            return 0;
        else if (!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()) {
             return 1;
        }
        else if(TextUtils.isEmpty(getPassword())){
             return 2;
        }
        else if(getPassword().length()<=6){
            return 3;
        }
        else
            return -1;

    }
}

