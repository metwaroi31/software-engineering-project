package com.example.c_food_main.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.c_food_main.R;
import com.example.c_food_main.adapter.FoodAdapter2;
import com.example.c_food_main.model.FoodModel;

import java.util.ArrayList;

public class SearchFoodBasedCategories extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<FoodModel> list = new ArrayList<>();
    ArrayList<FoodModel> result = new ArrayList<>();
    ProgressBar progressBar;
    String category = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);
        progressBar = findViewById(R.id.progress_bar);

        recyclerView = findViewById(R.id.recyclerViewFoodList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLiLayoutManager = layoutManager;
        recyclerView.setLayoutManager(rvLiLayoutManager);
        createList();
        filterFood();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FoodAdapter2 adapter = new FoodAdapter2(getApplicationContext(),result);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }
        },2000);
    }
    private void createList() {
        list.add(new FoodModel(R.drawable.turkey_bacon,"Turkey Bacon Sandwich",230,"fast food"));
        list.add(new FoodModel(R.drawable.spinach,"Spinach, Feta & Cage-Free Egg White Wrap",290,"meat"));
    }
    private void filterFood() {
        category = getIntent().getStringExtra("Category").toLowerCase();
        for (FoodModel food : list) {
            if(food.getCategory().equals(category.toLowerCase())){
                result.add(food);
            }
            Log.i("category",food.getCategory());
            Log.i("categoryFromIntent",category);
        }
    }

}