package com.example.c_food_main.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.example.c_food_main.R;
import com.google.android.material.textfield.TextInputEditText;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn, forgotButton;
    Button callSignUp;
    ImageView image;
    TextView logoText;
    TextInputEditText usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //hooks
        forgotButton  = findViewById(R.id.forgot_password_button);
        image=findViewById(R.id.logo_image);
        logoText=findViewById(R.id.logo_name);
//        image=findViewById(R.id.logo_name);
//        logoText=findViewById(R.id.logo_name);
//        sloganText=findViewById(R.id.textView1);

        usernameEditText = (TextInputEditText) findViewById(R.id.username_editTextInput);
        passwordEditText = (TextInputEditText) findViewById(R.id.password_editTextInput);
        loginBtn = findViewById(R.id.login_btn);
// Thụy xem lại chỗ này dùm tui nha sao gọi ra để dưới chạy animation ko đc chạy app nó ko lên
        callSignUp = findViewById(R.id.signup_btn);

        forgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);

                Pair[] pairs = new Pair[8];
                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(logoText, "logo_text");
                pairs[2] = new Pair<View, String>(usernameEditText, "username_tran");
                pairs[3] = new Pair<View, String>(passwordEditText, "password_tran");
                pairs[4] = new Pair<View, String>(loginBtn, "button_tran");
                pairs[5] = new Pair<View, String>(callSignUp, "login_signup_tran");
                pairs[6] = new Pair<View, String>(callSignUp, "button_tran");
                pairs[7] = new Pair<View, String>(callSignUp, "button_signup_tran");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this,pairs);
                startActivity(intent,options.toBundle());
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    // Thụy xem lại chỗ này dùm tui nha sao gọi ra để dưới chạy animation ko đc chạy app nó ko lên
//                Pair[] pairs = new Pair[7];
//                pairs[0] = new Pair<View, String>(image, "logo_image");
//                pairs[1] = new Pair<View, String>(logoText, "logo_text");
//                pairs[2] = new Pair<View, String>(sloganText, "logo_desc");
//                pairs[3] = new Pair<View, String>(username, "username_tran");
//                pairs[4] = new Pair<View, String>(password, "password_tran");
//                pairs[5] = new Pair<View, String>(loginBtn, "button_tran");
//                pairs[6] = new Pair<View, String>(callSignUp, "login_signup_tran");
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this,pairs);
//                startActivity(intent,options.toBundle());
//            }
//        });
//    }
//}
//
//        usernameEditText = findViewById(R.id.username_editText);
//      passwordEditText = findViewById(R.id.email_editText);
//        loginBtn = findViewById(R.id.login_btn);
//       signUpBtn = findViewById(R.id.signup_btn);
//
//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                login();
//            }
//        });
//
//        signUpBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToSignUpActivity();
//            }
//        });
//    }
//
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
                                Toasty.success(getApplicationContext(), "Login successfully", Toast.LENGTH_SHORT, true).show();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toasty.error(getApplicationContext(), "Wrong username or password!", Toast.LENGTH_SHORT, true).show();
                            }
                        });

                    }
                    Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete");
                },
                error -> {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toasty.error(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT, true).show();
                        }
                    });
                    Log.e("AuthQuickstart", error.toString());
                }
        );

    }
        private  void gotoMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
    }
}

//    private void goToSignUpActivity() {
//        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
//        startActivity(intent);

