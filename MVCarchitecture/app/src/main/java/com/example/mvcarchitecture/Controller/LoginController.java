package com.example.mvcarchitecture.Controller;

import com.example.mvcarchitecture.Model.User;
import com.example.mvcarchitecture.View.iLoginView;

public class LoginController implements iLoginController {
    iLoginView loginView;

    public LoginController(iLoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void OnLogin(String email, String password) {
        User user = new User(email, password);
        int logincode = user.isValid();

        if (logincode == 0)
            loginView.OnLoginErrror("Please Enter Email");

        else if (logincode == 1){
            loginView.OnLoginErrror ("Please Enter a Valid Email:");
        }
        else if(logincode==2){
            loginView.OnLoginErrror("Please enter your password");
        }
        else if(logincode==3){
            loginView.OnLoginErrror("Password should be more than 6 character");
        }
        else{
            loginView.OnLoginErrror("Login Success.");
        }
    }


}