package com.example.c_food_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.amazonaws.amplify.generated.graphql.ListFoodsQuery;
import com.amazonaws.amplify.generated.graphql.ListIngredientsFoodsQuery;
import com.amazonaws.amplify.generated.graphql.ListIngredientssQuery;
import com.amazonaws.amplify.generated.graphql.ListMacronutrientsFoodsQuery;
import com.amazonaws.amplify.generated.graphql.ListMacronutrientssQuery;
import com.amazonaws.amplify.generated.graphql.ListVitaminsFoodsQuery;
import com.amazonaws.amplify.generated.graphql.ListVitaminssQuery;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

import type.ModelFoodFilterInput;
import type.ModelIDInput;
import type.ModelIngredientsFilterInput;
import type.ModelIngredientsFoodFilterInput;
import type.ModelMacronutrientsFilterInput;
import type.ModelMacronutrientsFoodFilterInput;
import type.ModelStringInput;
import type.ModelVitaminsFilterInput;
import type.ModelVitaminsFoodFilterInput;

public class FoodDetailActivity extends AppCompatActivity {
    private AWSAppSyncClient mAWSAppSyncClient;
    CollapsingToolbarLayout collapsingToolbarLayout;
    private ArrayList<String> macronutrients = new ArrayList<String>();
    private ArrayList<String> vitamins = new ArrayList<String>();
    private ArrayList<String> ingredients = new ArrayList<String>();
    private Response<ListMacronutrientssQuery.Data> macrotemp;
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
    private GraphQLCall.Callback<ListVitaminssQuery.Data> vitaminCallback = new GraphQLCall.Callback<ListVitaminssQuery.Data>() {
        @Override
        public void onResponse(@NonNull Response<ListVitaminssQuery.Data> response) {
            response.data().listVitaminss().items().forEach(item -> {
                String vitaminToOutput = item.name() + " " + item.value() + " " + item.unit();
                Log.i("vitaminID", vitaminToOutput );
                vitamins.add(vitaminToOutput);
            });
        }
        @Override
        public void onFailure(@NonNull ApolloException e) {
            Log.i("ERROR OF QUERY", e.toString());
        }
    };
    private GraphQLCall.Callback<ListVitaminsFoodsQuery.Data> vitaminFoodCallback = new GraphQLCall.Callback<ListVitaminsFoodsQuery.Data>() {
        @Override
        public void onResponse(@NonNull Response<ListVitaminsFoodsQuery.Data> response) {
            response.data().listVitaminsFoods().items().forEach(item -> {
                Log.i("vitaminID", item.vitaminID() );
                queryVitamins(item.vitaminID());
            });
        }
        @Override
        public void onFailure(@NonNull ApolloException e) {
            Log.i("ERROR OF QUERY", e.toString());
        }
    };
    private GraphQLCall.Callback<ListIngredientssQuery.Data> ingredientCallback = new GraphQLCall.Callback<ListIngredientssQuery.Data>() {
        @Override
        public void onResponse(@NonNull Response<ListIngredientssQuery.Data> response) {
            response.data().listIngredientss().items().forEach(item -> {
                String ingredientToOutput = item.name() + " " + item.isvegeterian();
                Log.i("vitaminID", ingredientToOutput );
                ingredients.add(ingredientToOutput);
            });
        }
        @Override
        public void onFailure(@NonNull ApolloException e) {
            Log.i("ERROR OF QUERY", e.toString());
        }
    };
    private GraphQLCall.Callback<ListIngredientsFoodsQuery.Data> ingredientFoodCallback = new GraphQLCall.Callback<ListIngredientsFoodsQuery.Data>() {
        @Override
        public void onResponse(@NonNull Response<ListIngredientsFoodsQuery.Data> response) {
            response.data().listIngredientsFoods().items().forEach(item -> {
                Log.i("vitaminID", item.ingredientID() );
                queryVitamins(item.ingredientID());
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
        initVariables();
        initDatabase();
        queryMacrosOfFood(foodID);
        queryVitaminsOfFood(foodID);
    }
    private void initVariables() {

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
        ModelIDInput modelStringInput = ModelIDInput.builder().contains(foodID).build();
        ModelMacronutrientsFoodFilterInput modelFoodFilterInput = ModelMacronutrientsFoodFilterInput.builder().foodID(modelStringInput).build();
        mAWSAppSyncClient.query(ListMacronutrientsFoodsQuery.builder().filter(modelFoodFilterInput ).build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(macroFoodCallback);
    }
    private void queryVitamins (String vitaminID) {
        ModelIDInput modelStringInput = ModelIDInput.builder().contains(vitaminID).build();
        ModelVitaminsFilterInput modelFoodFilterInput = ModelVitaminsFilterInput.builder().id(modelStringInput).build();
        mAWSAppSyncClient.query(ListVitaminssQuery.builder().filter(modelFoodFilterInput ).build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(vitaminCallback);
    }
    private void queryVitaminsOfFood(String foodID) {
        ModelIDInput modelStringInput = ModelIDInput.builder().contains(foodID).build();
        ModelVitaminsFoodFilterInput modelFoodFilterInput = ModelVitaminsFoodFilterInput.builder().foodID(modelStringInput).build();
        mAWSAppSyncClient.query(ListVitaminsFoodsQuery.builder().filter(modelFoodFilterInput ).build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(vitaminFoodCallback);
    }
    private void queryIngredients (String ingredientID) {
        ModelIDInput modelStringInput = ModelIDInput.builder().contains(ingredientID).build();
        ModelIngredientsFilterInput modelFoodFilterInput = ModelIngredientsFilterInput.builder().id(modelStringInput).build();
        mAWSAppSyncClient.query(ListIngredientssQuery.builder().filter(modelFoodFilterInput ).build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(ingredientCallback);
    }
    private void queryIngredientsOfFood(String foodID) {
        ModelIDInput modelStringInput = ModelIDInput.builder().contains(foodID).build();
        ModelIngredientsFoodFilterInput modelFoodFilterInput = ModelIngredientsFoodFilterInput.builder().foodID(modelStringInput).build();
        mAWSAppSyncClient.query(ListIngredientsFoodsQuery.builder().filter(modelFoodFilterInput ).build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(ingredientFoodCallback);
    }
}