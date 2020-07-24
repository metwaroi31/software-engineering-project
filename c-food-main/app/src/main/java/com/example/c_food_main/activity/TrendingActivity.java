package com.example.c_food_main.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.amazonaws.amplify.generated.graphql.ListFavoriteFoodsQuery;
import com.amazonaws.amplify.generated.graphql.ListFoodsQuery;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClientException;
import com.amazonaws.mobileconnectors.appsync.ClearCacheOptions;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.c_food_main.R;
import com.example.c_food_main.adapter.TrendingFoodAdapter;
import com.example.c_food_main.model.FoodModel;

import java.util.ArrayList;

import type.ModelFavoriteFoodFilterInput;
import type.ModelFoodFilterInput;
import type.ModelIDInput;

public class TrendingActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<FoodModel> list = new ArrayList<>();
    private AWSAppSyncClient mAWSAppSyncClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending);
        recyclerView = findViewById(R.id.rv_trending_food);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLiLayoutManager = layoutManager;
        recyclerView.setLayoutManager(rvLiLayoutManager);
        createList();
        TrendingFoodAdapter adapter = new TrendingFoodAdapter(getApplicationContext(),list);
        recyclerView.setAdapter(adapter);
    }

    private void createList() {
        list.add(new FoodModel(R.drawable.turkey_bacon,"Turkey Bacon Sandwich",230,"fast food"));
        list.add(new FoodModel(R.drawable.spinach,"Spinach, Feta & Cage-Free Egg White Wrap",290,"meat"));

    }
}