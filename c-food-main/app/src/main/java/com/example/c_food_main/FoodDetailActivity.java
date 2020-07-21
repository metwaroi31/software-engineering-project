package com.example.c_food_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.amazonaws.amplify.generated.graphql.ListFoodsQuery;
import com.amazonaws.amplify.generated.graphql.ListMacronutrientsFoodsQuery;
import com.amazonaws.amplify.generated.graphql.ListMacronutrientssQuery;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

import type.ModelFoodFilterInput;
import type.ModelIDInput;
import type.ModelMacronutrientsFilterInput;
import type.ModelMacronutrientsFoodFilterInput;
import type.ModelStringInput;

public class FoodDetailActivity extends AppCompatActivity {
    private AWSAppSyncClient mAWSAppSyncClient;
    CollapsingToolbarLayout collapsingToolbarLayout;
    private List<String> macronutrients;
    private GraphQLCall.Callback<ListMacronutrientssQuery.Data> macroCallback = new GraphQLCall.Callback<ListMacronutrientssQuery.Data>() {
        @Override
        public void onResponse(@NonNull Response<ListMacronutrientssQuery.Data> response) {
            response.data().listMacronutrientss().items().forEach(item -> {
                String macroToOutput = item.name() + " " + item.value() + " " + item.unit();
                Log.i("macroID", macroToOutput );
                macronutrients.add(macroToOutput);
            });
        }
        @Override
        public void onFailure(@NonNull ApolloException e) {
            Log.i("ERROR OF QUERY2", e.toString());
        }
    };
    private GraphQLCall.Callback<ListMacronutrientsFoodsQuery.Data> macroFoodCallback = new GraphQLCall.Callback<ListMacronutrientsFoodsQuery.Data>() {
        @Override
        public void onResponse(@NonNull Response<ListMacronutrientsFoodsQuery.Data> response) {
            Log.i("macroID" , "run");
            response.data().listMacronutrientsFoods().items().forEach(item -> {
                Log.i("macroID", item.macronutrientID() );
                queryMacro(item.macronutrientID());
            });
        }
        @Override
        public void onFailure(@NonNull ApolloException e) {
            Log.i("ERROR OF QUERY", e.toString());
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_detail);
        String foodName = getIntent().getStringExtra("Food_Name");
        String foodID = getIntent().getStringExtra("Food_id");
        String position = getIntent().getStringExtra("Position");
        collapsingToolbarLayout = findViewById(R.id.collapsingToolBar1);
        collapsingToolbarLayout.setTitle(foodName + foodID);
        initDatabase();
        queryMacrosOfFood(foodID);
    }
    private void initDatabase () {
        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();

    }
    private void queryMacro (String macroID) {
        ModelIDInput modelStringInput = ModelIDInput.builder().contains(macroID).build();
        ModelMacronutrientsFilterInput modelFoodFilterInput = ModelMacronutrientsFilterInput.builder().id(modelStringInput).build();
        mAWSAppSyncClient.query(ListMacronutrientssQuery.builder().filter(modelFoodFilterInput ).build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(macroCallback);
    }
    private void queryMacrosOfFood(String foodID) {
        Log.i("foodID", foodID);
        ModelIDInput modelStringInput = ModelIDInput.builder().contains(foodID).build();
        ModelMacronutrientsFoodFilterInput modelFoodFilterInput = ModelMacronutrientsFoodFilterInput.builder().foodID(modelStringInput).build();
        mAWSAppSyncClient.query(ListMacronutrientsFoodsQuery.builder().filter(modelFoodFilterInput ).build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(macroFoodCallback);
    }
}