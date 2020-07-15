package com.example.c_food_main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.core.Amplify;

import es.dmoral.toasty.Toasty;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    //Variables
    Animation topAnim , bottomAnim;
    ImageView image;
    TextView logo, slogan;
    boolean isAuth;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        // animations
        topAnim = AnimationUtils.loadAnimation(this ,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this ,R.anim.top_animation);
        //Hooks
        image = findViewById(R.id.splash_imageView);
        logo = findViewById(R.id.textView);
        slogan = findViewById(R.id.textView1);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              checkSession();
            }
        },SPLASH_SCREEN);

    }
    private void checkSession() {
        Amplify.Auth.fetchAuthSession(
                result -> {
                    AWSCognitoAuthSession cognitoAuthSession = (AWSCognitoAuthSession) result;
                    switch (cognitoAuthSession.getIdentityId().getType()) {
                        case SUCCESS:
                            goToMainActivity();
                            Log.i("AuthQuickStart", "IdentityId: " + cognitoAuthSession.getIdentityId().getValue());
                            break;
                        case FAILURE:
                            goToLoginActivity();
                            Log.i("AuthQuickStart", "IdentityId not present because: " + cognitoAuthSession.getIdentityId().getError().toString());

                    }
                },

                error -> Log.e("AuthQuickStart", error.toString())
        );
    }
    private void goToMainActivity() {

        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toasty.success(getApplicationContext(), "Welcome, " + Amplify.Auth.getCurrentUser().getUsername() + " !", Toast.LENGTH_SHORT, true).show();
            }
        });

    }

    private void goToLoginActivity() {
        Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
        //  startActivity(intent);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(logo, "logo_text");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this,pairs);
                startActivity(intent,options.toBundle());
            }
        });
    }
}

//  @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//        Amplify.Auth.fetchAuthSession(
//                result -> {
 //                   AWSCognitoAuthSession cognitoAuthSession = (AWSCognitoAuthSession) result;
//                    switch(cognitoAuthSession.getIdentityId().getType()) {
 //                       case SUCCESS:
  //                          goToMainActivity();
//                            Log.i("AuthQuickStart", "IdentityId: " + cognitoAuthSession.getIdentityId().getValue());
 //                           break;
 //                       case FAILURE:
 //                           goToLoginActivity();
 //                           Log.i("AuthQuickStart", "IdentityId not present because: " + cognitoAuthSession.getIdentityId().getError().toString());
  //                  }
 //               },
 //               error -> Log.e("AuthQuickStart", error.toString())
//        );
 //   }
 //   private void goToLoginActivity() {
 //       Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
  //      startActivity(intent);
 //   }
  //  private void goToMainActivity() {
  //      Intent intent = new Intent(SplashActivity.this, MainActivity.class);
 //       startActivity(intent);
 //   }
//}