package com.example.c_food_main.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.amplifyframework.core.Amplify;
import com.example.c_food_main.R;
import com.google.android.material.textfield.TextInputEditText;

public class ProfileActivity extends AppCompatActivity {
    TextInputEditText usernameTextEdit, emailTextEdit, phoneNumberTextEdit;
    Button changePassButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        usernameTextEdit = findViewById(R.id.username_feedback);
        emailTextEdit = findViewById(R.id.email_userfeedback);
        phoneNumberTextEdit = findViewById(R.id.contentfeedback);
        changePassButton = findViewById(R.id.changePass_btn);
        changePassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        usernameTextEdit.setText(Amplify.Auth.getCurrentUser().getUsername());
    }
}