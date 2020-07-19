package com.example.c_food_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amazonaws.amplify.generated.graphql.ListFoodsQuery;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.c_food_main.model.FoodModel;

import java.util.ArrayList;

import type.CreateFavoriteFoodInput;
import type.ModelFoodFilterInput;
import type.ModelStringInput;

public class SearchFoodActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private AWSAppSyncClient mAWSAppSyncClient;
    ArrayList<FoodModel> foodList = new ArrayList<FoodModel>();
    private String inputSearch = "asdkjlfbqiuwlrengfl/awjfhguiawheriuwerhf";
    private Response<ListFoodsQuery.Data> returnedData;
    private GraphQLCall.Callback<ListFoodsQuery.Data> todosCallback = new GraphQLCall.Callback<ListFoodsQuery.Data>() {
        @Override
        public void onResponse(@NonNull Response<ListFoodsQuery.Data> response) {
            returnedData = response;
            response.data().listFoods().items().forEach(item -> {
                Log.i("Foodname", item.name());
            });
            SearchFoodActivity.this.runOnUiThread(new Runnable() {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);

        initDatabase();
        query();
    }
    public void query() {
        ModelStringInput modelStringInput = ModelStringInput.builder().contains(inputSearch).build();
        ModelFoodFilterInput modelFoodFilterInput = ModelFoodFilterInput.builder().name(modelStringInput).build();
        mAWSAppSyncClient.query(ListFoodsQuery.builder().filter(modelFoodFilterInput ).build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(todosCallback);
    }
    private void initDatabase() {
        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();
    }
    private void displayData () {
        recyclerView = findViewById(R.id.recyclerViewFoodList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLiLayoutManager = layoutManager;
        recyclerView.setLayoutManager(rvLiLayoutManager);

        FoodAdapter adapter = new FoodAdapter(getApplicationContext(),returnedData);
        recyclerView.setAdapter(adapter);

    }

    public void search(View view) {
        TextView helloTextView = (TextView) findViewById(R.id.search_bar);
        inputSearch = helloTextView.getText().toString();
        query();
    }
}