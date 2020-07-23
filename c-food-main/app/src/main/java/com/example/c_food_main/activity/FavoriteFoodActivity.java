package com.example.c_food_main.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.c_food_main.R;
import com.example.c_food_main.adapter.FavFoodAdapter;
import com.example.c_food_main.adapter.TrendingFoodAdapter;
import com.example.c_food_main.model.FoodModel;

import java.util.ArrayList;

public class FavoriteFoodActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList <FoodModel> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_food);
        recyclerView = findViewById(R.id.rv_fav_food_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLiLayoutManager = layoutManager;
        recyclerView.setLayoutManager(rvLiLayoutManager);
        createList();
        FavFoodAdapter adapter = new FavFoodAdapter(getApplicationContext(),list);
        recyclerView.setAdapter(adapter);
    }
    private void createList() {
        list.add(new FoodModel(R.drawable.turkey_bacon,"Turkey Bacon Sandwich",230,"fast food"));
        list.add(new FoodModel(R.drawable.spinach,"Spinach, Feta & Cage-Free Egg White Wrap",290,"meat"));

    }
}