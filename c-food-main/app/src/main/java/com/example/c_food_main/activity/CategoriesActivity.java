package com.example.c_food_main.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.c_food_main.R;
import com.example.c_food_main.adapter.CategoriesAdapter;
import com.example.c_food_main.model.CategoriesModel;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<CategoriesModel> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        recyclerView = findViewById(R.id.rv_food_categories);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLiLayoutManager = layoutManager;
        recyclerView.setLayoutManager(rvLiLayoutManager);
        createCategoryList();

        CategoriesAdapter adapter = new CategoriesAdapter(getApplicationContext(),list);
        recyclerView.setAdapter(adapter);
    }

    private void createCategoryList() {
        list.add(new CategoriesModel(R.drawable.vegetables,"Vegetables"));
        list.add(new CategoriesModel(R.drawable.fruits,"Fruits"));
        list.add(new CategoriesModel((R.drawable.beans_nuts),"Grains, Beans and Nuts"));
        list.add(new CategoriesModel((R.drawable.meat),"Meat"));
        list.add(new CategoriesModel(R.drawable.seafood,"Fish and Seafood"));
        list.add(new CategoriesModel(R.drawable.fast_food,"Fast Food"));
    }
}