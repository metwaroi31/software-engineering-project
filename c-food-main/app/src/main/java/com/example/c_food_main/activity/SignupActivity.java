package com.example.c_food_main.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.amazonaws.amplify.generated.graphql.CreateUserMutation;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.c_food_main.R;
import com.example.c_food_main.model.User;
import com.google.android.material.textfield.TextInputEditText;

import javax.annotation.Nonnull;

import type.CreateUserInput;

public class SignupActivity extends AppCompatActivity {
    TextInputEditText usernameEditText, passwordEditText, emailEditText;
    Button signUpBtn, gobackLoginBtn;
    private AWSAppSyncClient mAWSAppSyncClient;
    private GraphQLCall.Callback<CreateUserMutation.Data> mutationUserCallback = new GraphQLCall.Callback<CreateUserMutation.Data>() {
        @Override
        public void onResponse(@Nonnull Response<CreateUserMutation.Data> response) {
            Log.i("Results", "Added Todo");
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("Error", e.toString());
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);
        usernameEditText = (TextInputEditText) findViewById(R.id.usernameSignUp);
        passwordEditText = (TextInputEditText) findViewById(R.id.passwordSignUp);
        emailEditText = (TextInputEditText) findViewById(R.id.emailSignUp);
        signUpBtn = findViewById(R.id.signUp_btn2);
        gobackLoginBtn = findViewById(R.id.loginback_btn);
        gobackLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        initDatabase();
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
                    User newUser = new User(username,email);
                   addUser(username, password, email);
                    goToConfirmCode(newUser);
                    Log.i("AuthQuickStart", "Result: " + result.toString());
                    },
                error -> Log.e("AuthQuickStart", "Sign up failed", error)
        );
        Log.i("Sign Up", "function has triggered ");
    }
    private void goToConfirmCode(User user) {
        Intent intent = new Intent(SignupActivity.this,ConfirmCodeActivity.class);
        intent.putExtra("newUser",user);
        startActivity(intent);
    }
    private void addUser (String username, String password, String email) {
        CreateUserInput createUserInput = CreateUserInput.builder()
                                                .username(username)
                                                .password(password)
                                                .email(email).build();

        mAWSAppSyncClient.mutate(CreateUserMutation.builder().input(createUserInput).build())
                .enqueue(mutationUserCallback);
    }
    private void initDatabase () {
        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();
    }
}
//    EditText usernameEditText, passwordEditText, emailEditText;
//    Button signUpBtn;
//    private AWSAppSyncClient mAWSAppSyncClient;
//    private GraphQLCall.Callback<CreateUserMutation.Data> mutationUserCallback = new GraphQLCall.Callback<CreateUserMutation.Data>() {
//        @Override
//        public void onResponse(@Nonnull Response<CreateUserMutation.Data> response) {
//            Log.i("Results", "Added Todo");
////            Toast.makeText(getApplicationContext(), "User added", Toast.LENGTH_LONG).show();
//
//        }
//
//        @Override
//        public void onFailure(@Nonnull ApolloException e) {
//            Log.e("Error", e.toString());
//        }
//    };
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup);
//        usernameEditText = findViewById(R.id.username_editText);
//        passwordEditText = findViewById(R.id.password_editText);
//        emailEditText = findViewById(R.id.email_editText);
//        signUpBtn = findViewById(R.id.signup_btn);
//        signUpBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signUp();
//            }
//        });
//        initDatabase();
//    }
//    private void signUp() {
//        String username, password, email;
//        username = usernameEditText.getText().toString();
//        password = passwordEditText.getText().toString();
//        email = emailEditText.getText().toString();
//        Amplify.Auth.signUp(
//                username,
//                password,
//                AuthSignUpOptions.builder().userAttribute(AuthUserAttributeKey.email(), email).build(),
//                result -> {
//                    Log.i("AuthQuickStart", "Result: " + result.toString());
//                    User newUser = new User(username,email);
//                    addUser(username, password, email);
//                    goToConfirmCode(newUser);
//                    },
//                error -> Log.e("AuthQuickStart", "Sign up failed", error)
//        );
//    }
//    private void goToConfirmCode(User user) {
//        Intent intent = new Intent(SignupActivity.this,ConfirmCodeActivity.class);
//        intent.putExtra("newUser",user);
//        startActivity(intent);
//    }
//    private void addUser (String username, String password, String email) {
//        CreateUserInput createUserInput = CreateUserInput.builder()
//                                                .username(username)
//                                                .password(password)
//                                                .email(email).build();
//
//        mAWSAppSyncClient.mutate(CreateUserMutation.builder().input(createUserInput).build())
//                .enqueue(mutationUserCallback);
//    }
//    private void initDatabase () {
//        mAWSAppSyncClient = AWSAppSyncClient.builder()
//                .context(getApplicationContext())
//                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
//                .build();
//    }
//}