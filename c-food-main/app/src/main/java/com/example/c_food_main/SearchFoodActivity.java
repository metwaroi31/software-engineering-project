package com.example.c_food_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.amazonaws.amplify.generated.graphql.ListFoodsQuery;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.c_food_main.model.FoodModel;

import java.util.ArrayList;

public class SearchFoodActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private AWSAppSyncClient mAWSAppSyncClient;
    ArrayList<FoodModel> foodList = new ArrayList<FoodModel>();

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
//        recyclerView = findViewById(R.id.recyclerViewFoodList);
//        foodList.add(new FoodModel(R.drawable.com_tam,"com tam","Calories: 50"));
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        RecyclerView.LayoutManager rvLiLayoutManager = layoutManager;
//
//        recyclerView.setLayoutManager(rvLiLayoutManager);
//        foodList.add(new FoodModel(R.drawable.com_tam,"com tam","Calories: 50"));
//        FoodAdapter adapter = new FoodAdapter(getApplicationContext(),foodList);
//        recyclerView.setAdapter(adapter);

    }
    public void query() {
        mAWSAppSyncClient.query(ListFoodsQuery.builder().build())
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

        returnedData.data().listFoods().items().forEach(item -> {
////            foodList.add(item);
//            foodList.add(new FoodModel(R.drawable.com_tam,"com tam","Calories: 50"));
//            foodList.add(new FoodModel(R.drawable.com_tam,"com tam","Calories: 50"));
            Log.i("Foodname", item.name() + item.getClass().toString());
        });
        FoodAdapter adapter = new FoodAdapter(getApplicationContext(),returnedData);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.search_bar,menu);
        return true;
    }
}