package com.example.c_food_main.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amazonaws.amplify.generated.graphql.ListFavoriteFoodsQuery;
import com.amazonaws.amplify.generated.graphql.ListFoodsQuery;
import com.amazonaws.amplify.generated.graphql.ListUsersQuery;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.amplifyframework.core.Amplify;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.c_food_main.R;
import com.example.c_food_main.adapter.FavFoodAdapter;
import com.example.c_food_main.adapter.TrendingFoodAdapter;
import com.example.c_food_main.model.FoodModel;

import java.util.ArrayList;

import type.ModelFavoriteFoodFilterInput;
import type.ModelFoodFilterInput;
import type.ModelIDInput;
import type.ModelStringInput;
import type.ModelUserFilterInput;

public class FavoriteFoodActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private AWSAppSyncClient mAWSAppSyncClient;
    ArrayList <FoodModel> list = new ArrayList<>();
    private String currentUser;

    private GraphQLCall.Callback<ListUsersQuery.Data> userCallback = new GraphQLCall.Callback<ListUsersQuery.Data>() {
        @Override
        public void onResponse(@NonNull Response<ListUsersQuery.Data> response) {
            response.data().listUsers().items().forEach(item -> {
                Log.i("user", item.id());
                currentUser = item.id();
            });
            ModelIDInput modelIDInput = ModelIDInput.builder().eq(currentUser).build();
            ModelFavoriteFoodFilterInput modelFavoriteFoodFilterInput = ModelFavoriteFoodFilterInput.builder()
                                                                        .userID(modelIDInput).build();
            mAWSAppSyncClient.query(ListFavoriteFoodsQuery.builder().filter(modelFavoriteFoodFilterInput).build())
                    .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                    .enqueue(FavFoodCallback);
        }
        @Override
        public void onFailure(@NonNull ApolloException e) {
            Log.i("ERROR OF QUERY", e.toString());
        }
    };

    private GraphQLCall.Callback<ListFoodsQuery.Data> todoCallback = new GraphQLCall.Callback<ListFoodsQuery.Data>() {
        @Override
        public void onResponse(@NonNull Response<ListFoodsQuery.Data> response) {
            response.data().listFoods().items().forEach(item -> {
                Log.i("Foodname", item.name());
                list.add(new FoodModel (R.drawable.beans_nuts, item.name(),(int) item.weight(), "fast food"));
            });
            FavoriteFoodActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    displayData();
                }
            });
        }
        @Override
        public void onFailure(@NonNull ApolloException e) {
            Log.i("ERROR OF QUERY", e.toString());
        }
    };

    private GraphQLCall.Callback<ListFavoriteFoodsQuery.Data> FavFoodCallback = new GraphQLCall.Callback<ListFavoriteFoodsQuery.Data>() {
        @Override
        public void onResponse(@NonNull Response<ListFavoriteFoodsQuery.Data> response) {

            response.data().listFavoriteFoods().items().forEach(item -> {
                String id = item.foodID();
                ModelIDInput modelIDInput = ModelIDInput.builder().eq(id).build();
                ModelFoodFilterInput modelFavoriteFoodFilterInput = ModelFoodFilterInput.builder()
                        .id(modelIDInput).build();
                mAWSAppSyncClient.query(ListFoodsQuery.builder().filter(modelFavoriteFoodFilterInput).build())
                        .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                        .enqueue(todoCallback);

            });
            for (int i = 0 ; i < 2000;i++){}


        }
        @Override
        public void onFailure(@NonNull ApolloException e) {
            Log.i("ERROR OF QUERY", e.toString());
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_food);
        initDatabase();
        queryUser();
    }

    public void queryUser() {
        String username = Amplify.Auth.getCurrentUser().getUsername();
        ModelStringInput modelStringInput = ModelStringInput.builder().eq(username).build();
        ModelUserFilterInput modelFoodFilterInput = ModelUserFilterInput.builder().username(modelStringInput).build();
        mAWSAppSyncClient.query(ListUsersQuery.builder().filter(modelFoodFilterInput).build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(userCallback);

        displayData();
    }
    private void initDatabase() {
        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();
    }
    private void displayData () {
        Log.i("Foodname", "run");
        recyclerView = findViewById(R.id.rv_fav_food_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLiLayoutManager = layoutManager;
        recyclerView.setLayoutManager(rvLiLayoutManager);
        FavFoodAdapter adapter = new FavFoodAdapter(getApplicationContext(),list);
        recyclerView.setAdapter(adapter);
    }
}