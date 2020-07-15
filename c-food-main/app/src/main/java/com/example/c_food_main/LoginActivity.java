package com.example.c_food_main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText, passwordEditText;
    Button loginBtn, signUpBtn;
    Button callSignUp, Backlogin , SignUp;
    ImageView image;
    TextView logoText, sloganText;
    TextInputEditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //hooks

        image=findViewById(R.id.logo_image);
        logoText=findViewById(R.id.logo_name);
       // sloganText=findViewById(R.id.textView1);
        username=findViewById(R.id.username_editText);
        password=findViewById(R.id.password_editText);
        loginBtn=findViewById(R.id.login_btn);
        Backlogin =findViewById(R.id.loginback_btn);
        SignUp = findViewById(R.id.Signup_btn);
// Thụy xem lại chỗ này dùm tui nha sao gọi ra để dưới chạy animation ko đc chạy app nó ko lên
        callSignUp = findViewById(R.id.signup_btn);


        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
 //               startActivity(intent);
                Pair[] pairs = new Pair[8];
                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(logoText, "logo_text");
             //   pairs[1] = new Pair<View, String>(sloganText, "logo_desc");
                pairs[2] = new Pair<View, String>(username, "username_tran");
                pairs[3] = new Pair<View, String>(password, "password_tran");
                pairs[4] = new Pair<View, String>(loginBtn, "button_tran");
                pairs[5] = new Pair<View, String>(callSignUp, "login_signup_tran");
                pairs[6] = new Pair<View, String>(callSignUp, "button_tran");
                pairs[7] = new Pair<View, String>(callSignUp, "button_signup_tran");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this,pairs);
                startActivity(intent,options.toBundle());
            }
        });
    }
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
//    private void login() {
//        String username, password;
//        username = usernameEditText.getText().toString();
//       password = passwordEditText.getText().toString();
//        Amplify.Auth.signIn(
//                username,
//                password,
//                result -> {
//                    if (result.isSignInComplete()) {
//                        gotoMainActivity();
//                        runOnUiThread(new Runnable() {
//                            public void run() {
//                                Toasty.success(getApplicationContext(),"Login successfully" ,Toast.LENGTH_SHORT,true).show();
//                            }
//                        });
//                    }
//                    else {
//                        runOnUiThread(new Runnable() {
//                            public void run() {
//                                Toasty.error(getApplicationContext(),"Wrong username or password!", Toast.LENGTH_SHORT,true).show();
//                            }
//                       });
//
//                   }
//                    Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete");
//                },
//                error -> {
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            Toasty.error(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT,true).show();
//                        }
//                    });
//                   Log.e("AuthQuickstart", error.toString());
//                }
//        );
//
//    }
//
//    private  void gotoMainActivity() {
//        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//        startActivity(intent);
//    }
//    private void goToSignUpActivity() {
//        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
//        startActivity(intent);

