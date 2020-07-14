package com.example.c_food_main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {
    TextInputLayout regName, regUsername, regEmail, regPassword;
    Button regBtn, regToLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

    }
    private Boolean validateName(){
        String val = regName.getEditText().getText().toString();
        if(val.isEmpty()){
            regName.setError("Field cannot be empty");
            return false;
        }
        else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateUsername(){
        String val = regName.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if(val.isEmpty()){
            regName.setError("Field cannot be empty");
            return false;
        }
        else if(val.length()>=15){
            regName.setError("User too long");
            return false;
        }
        else if(!val.matches(noWhiteSpace)){
            regName.setError("White Space are not allowed");
            return false;

        }
        else {
            regName.setError(null);
            return true;
        }
    }
    private Boolean validateEmail(){
        String val = regName.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-] +\\.+[a-z]+";
        if(val.isEmpty()){
            regName.setError("Field cannot be empty");
            return false;
        }
        else if( !val.matches(emailPattern))
        {
            regName.setError("Invalid email address");
            return false;
        }


        else {
            regName.setError(null);
            return true;
        }
    }
    private Boolean validatePassword(){
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^"+"(?=.*[a-zA-z])" +"(?=.*[@#$%^&+=])" + "(?=\\s+$)" + ".{4,}" +"$";
        if(val.isEmpty()){
            regName.setError("Field cannot be empty");
            return false;
        }
        else if( !val.matches(passwordVal))
        {
            regName.setError("Password is to week");
            return false;
        }
        else {
            regName.setError(null);
            return true;
        }
    }
    public void registerUser(View view)
    {
        if(!validateName() |! validatePassword() | !validateEmail() | !validateUsername())
        {
            return;
        }
        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();
    }

}