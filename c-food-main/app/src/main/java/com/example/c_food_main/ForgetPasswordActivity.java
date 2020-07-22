package com.example.c_food_main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.google.android.material.textfield.TextInputEditText;

import es.dmoral.toasty.Toasty;

public class ForgetPasswordActivity extends AppCompatActivity {
    TextInputEditText usernameEditText;
    Button checkUserNameBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        usernameEditText = findViewById(R.id.username_forgot_password);
        checkUserNameBtn = findViewById(R.id.check_username_btn);

        checkUserNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkExistUser();
            }
        });
    }

    private void checkExistUser() {
        String username = usernameEditText.getText().toString();
        Amplify.Auth.resetPassword(
                username,
                result -> {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toasty.success(getApplicationContext(), "Check your email to get OTP code", Toast.LENGTH_SHORT, true).show();
                        }
                    });
                    Intent intent = new Intent(ForgetPasswordActivity.this,FindPasswordActivity.class);
                    startActivity(intent);
                    Log.i("AuthQuickstart", result.toString());

                },
                error -> {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toasty.error(getApplicationContext(), "Username does not exist", Toast.LENGTH_SHORT, true).show();
                        }
                    });
                    Log.e("AuthQuickstart", error.toString());
                }
        );
    }
}