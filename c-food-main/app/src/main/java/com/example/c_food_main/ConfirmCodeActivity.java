package com.example.c_food_main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;
import com.example.c_food_main.model.User;

public class ConfirmCodeActivity extends AppCompatActivity {
    EditText codeEditText;
    Button confirmButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_code);
        User newUser = getUserFromIntent();
        codeEditText = findViewById(R.id.verifiedCode_editText);
        confirmButton = findViewById(R.id.confirmCode_btn);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyCode(newUser);
            }
        });
    }
    private User getUserFromIntent() {
        return (User) getIntent().getSerializableExtra("newUser");
    }
    private  void verifyCode(User newUser) {
        String code = codeEditText.getText().toString();
        Amplify.Auth.confirmSignUp(
                newUser.username,
                code,
                result -> {
                    if(result.isSignUpComplete()) {
                        goToMainActivity();
                    }
                    Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
                },
                error -> Log.e("AuthQuickstart", error.toString())
        );
    }
    private  void goToMainActivity() {
        Intent intent = new Intent(ConfirmCodeActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}