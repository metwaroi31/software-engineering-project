package com.example.c_food_main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import com.example.c_food_main.model.FoodModel;

import java.util.ArrayList;

public class SearchFoodActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<FoodModel> foodList = new ArrayList<FoodModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);

        recyclerView = findViewById(R.id.recyclerViewFoodList);
        foodList.add(new FoodModel(R.drawable.com_tam,"com tam","Calories: 50"));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLiLayoutManager = layoutManager;

        recyclerView.setLayoutManager(rvLiLayoutManager);
        foodList.add(new FoodModel(R.drawable.com_tam,"com tam","Calories: 50"));
        FoodAdapter adapter = new FoodAdapter(getApplicationContext(),foodList);
        recyclerView.setAdapter(adapter);



    }
}