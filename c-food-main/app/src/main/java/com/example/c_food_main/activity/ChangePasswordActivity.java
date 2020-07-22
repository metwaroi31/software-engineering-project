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

public class ChangePasswordActivity extends AppCompatActivity {
    TextInputEditText newPasswordEditText, oldPasswordEditText;
    Button changePassBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
        newPasswordEditText = findViewById(R.id.new_password_editText);
        oldPasswordEditText = findViewById(R.id.old_password_editText);
        changePassBtn = findViewById(R.id.confirm_change_password_btn);

        changePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePassword();
            }
        });
    }
    private void updatePassword() {
        String oldPassword = oldPasswordEditText.getText().toString();
        String newPassword = newPasswordEditText.getText().toString();
        Amplify.Auth.updatePassword(
                oldPassword,
                newPassword,
                () -> {
                    goBackProfileActivity();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toasty.success(getApplicationContext(), "Change password successfully", Toast.LENGTH_SHORT, true).show();
                        }
                    });
                    Log.i("AuthQuickstart", "Updated password successfully");
                },
                error -> {
                    Toasty.error(getApplicationContext(), "Change password failed", Toast.LENGTH_SHORT, true).show();

                    Log.e("AuthQuickstart", error.toString());
                }
        );
    }
    private void goBackProfileActivity() {
        Intent intent = new Intent(ChangePasswordActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}