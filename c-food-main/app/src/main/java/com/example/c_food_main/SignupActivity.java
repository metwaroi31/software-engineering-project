package com.example.c_food_main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.example.c_food_main.model.User;

public class SignupActivity extends AppCompatActivity {
    EditText usernameEditText, passwordEditText, emailEditText;
    Button signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        usernameEditText = findViewById(R.id.username_editText);
        passwordEditText = findViewById(R.id.password_editText);
        emailEditText = findViewById(R.id.email_editText);
        signUpBtn = findViewById(R.id.signup_btn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }
    private void signUp() {
        String username, password, email;
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();
        email = emailEditText.getText().toString();
        Amplify.Auth.signUp(
                username,
                password,
                AuthSignUpOptions.builder().userAttribute(AuthUserAttributeKey.email(), email).build(),
                result -> {
                    Log.i("AuthQuickStart", "Result: " + result.toString());
                    User newUser = new User(username,email);
                    goToConfirmCode(newUser);
                    },
                error -> Log.e("AuthQuickStart", "Sign up failed", error)
        );
    }
    private void goToConfirmCode(User user) {
        Intent intent = new Intent(SignupActivity.this,ConfirmCodeActivity.class);
        intent.putExtra("newUser",user);
        startActivity(intent);
    }
}