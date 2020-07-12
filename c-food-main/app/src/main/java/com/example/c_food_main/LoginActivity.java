package com.example.c_food_main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText, passwordEditText;
    Button loginBtn, signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEditText = findViewById(R.id.username_editText);
        passwordEditText = findViewById(R.id.email_editText);
        loginBtn = findViewById(R.id.login_btn);
        signUpBtn = findViewById(R.id.signup_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignUpActivity();
            }
        });
    }

    private void login() {
        String username, password;
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();
        Amplify.Auth.signIn(
                username,
                password,
                result -> {
                    if (result.isSignInComplete()) {
                        gotoMainActivity();
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toasty.success(getApplicationContext(),"Login successfully" ,Toast.LENGTH_SHORT,true).show();
                            }
                        });
                    }
                    else {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toasty.error(getApplicationContext(),"Wrong username or password!", Toast.LENGTH_SHORT,true).show();
                            }
                        });

                    }
                    Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete");
                },
                error -> {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toasty.error(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT,true).show();
                        }
                    });
                    Log.e("AuthQuickstart", error.toString());
                }
        );

    }

    private  void gotoMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
    private void goToSignUpActivity() {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }
}