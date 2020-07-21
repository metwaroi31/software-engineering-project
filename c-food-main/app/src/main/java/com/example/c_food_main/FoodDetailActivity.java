package com.example.c_food_main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class FoodDetailActivity extends AppCompatActivity {
    private AWSAppSyncClient mAWSAppSyncClient;
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_detail);
        String foodName = getIntent().getStringExtra("Food_Name");
        String foodID = getIntent().getStringExtra("Food_id");
        String position = getIntent().getStringExtra("Position");
        collapsingToolbarLayout = findViewById(R.id.collapsingToolBar1);
        collapsingToolbarLayout.setTitle(foodName + foodID);
    }
    private void initDatabase () {
        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();

    }
}