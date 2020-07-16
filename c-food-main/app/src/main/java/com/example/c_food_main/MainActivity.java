package com.example.c_food_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;



import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

     MenuItem itemlogout;
  //  Button logoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemlogout = findViewById(R.id.nav_logout);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


        final Animation animTranslate = AnimationUtils.loadAnimation(this,R.anim.anim_translate);

        RelativeLayout RelativeSearch =(RelativeLayout) findViewById(R.id.relative_search);
        RelativeLayout RelativeLikemeal =(RelativeLayout) findViewById(R.id.relative_likemeal);
        RelativeLayout RelativeMeal =(RelativeLayout) findViewById(R.id.relative_meal);
        RelativeLayout RelativeCategory =(RelativeLayout) findViewById(R.id.relative_category);

        RelativeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animTranslate);
            }
        });
        RelativeLikemeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animTranslate);
            }
        });
        RelativeCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animTranslate);
            }
        });
        RelativeMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animTranslate);
            }
        });

        /*itemlogout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
     //           logOut();
//               Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//               startActivity(intent);
                return false;
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);
         switch (item.getItemId()) {
             case R.id.nav_logout:
             {
                 logOut();
              Intent intent = new Intent(MainActivity.this, LoginActivity.class);
               startActivity(intent);
                 return true;
             }
         }
        return true;
    }

    private void logOut(){
        Amplify.Auth.signOut(
                () -> {
                    goToLoginActivity();
                    Log.i("AuthQuickstart","Signed out globally");
        },
                error ->Log.e("AuthQuickstart",error.toString())
        );
    }
    private void goToLoginActivity(){
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return true;
    }
}
//        logoutButton = findViewById(R.id.logout_btn);
//
//        logoutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                logOut();
//            }
//        });
//    }
//    private void logOut() {
//        Amplify.Auth.signOut(
//                () -> {
//                    goToLoginActivity();
//                    Log.i("AuthQuickstart", "Signed out globally");
//                },
//                error -> Log.e("AuthQuickstart", error.toString())
//        );
//    }
//    private void goToLoginActivity() {
//        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
//        startActivity(intent);
