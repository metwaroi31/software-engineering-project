package com.example.c_food_main.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.example.c_food_main.R;
import com.google.android.material.textfield.TextInputEditText;

import es.dmoral.toasty.Toasty;

public class FindPasswordActivity extends AppCompatActivity {
    TextInputEditText otpEditText, newPasswordEditText;
    Button confirmBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        otpEditText = findViewById(R.id.otp_code_editText);
        newPasswordEditText = findViewById(R.id.new_password_editText2);
        confirmBtn = findViewById(R.id.confirm_update_password_btn);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePassword();
            }
        });
    }

    private void updatePassword() {
        String otp = otpEditText.getText().toString();
        String newPassword = newPasswordEditText.getText().toString();
        Amplify.Auth.confirmResetPassword(
                newPassword,
                otp,
                () -> {

                    Intent intent = new Intent(FindPasswordActivity.this,LoginActivity.class);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toasty.success(getApplicationContext(), "Login successfully", Toast.LENGTH_SHORT, true).show();
                        }
                    });
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    Log.i("AuthQuickstart", "New password confirmed");
                },
                error -> {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toasty.error(getApplicationContext(), "Update password failed", Toast.LENGTH_SHORT, true).show();
                        }
                    });
                    Log.e("AuthQuickstart", error.toString());
                }
        );

    }
}