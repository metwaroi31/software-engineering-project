package com.example.c_food_main.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.c_food_main.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class FoodDetailActivity extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView coverImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_detail);
        coverImage = findViewById(R.id.food_cover_image);
        String foodName = getIntent().getStringExtra("Food_Name");
        int image = getIntent().getIntExtra("Food_Image",0);
        String foodID = getIntent().getStringExtra("Food_id");
        String position = getIntent().getStringExtra("Position");
        collapsingToolbarLayout = findViewById(R.id.collapsingToolBar1);
        collapsingToolbarLayout.setTitle(foodName);

        coverImage.setImageResource(image);
    }

}