package com.example.c_food_main.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.amazonaws.amplify.generated.graphql.ListCategoriessQuery;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.c_food_main.R;
import com.example.c_food_main.adapter.CategoriesAdapter;
import com.example.c_food_main.model.CategoriesModel;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity {
    private AWSAppSyncClient mAWSAppSyncClient;
    RecyclerView recyclerView;
    ArrayList<CategoriesModel> list = new ArrayList<>();
    private GraphQLCall.Callback<ListCategoriessQuery.Data> todoCallback = new GraphQLCall.Callback<ListCategoriessQuery.Data>() {
        @Override
        public void onResponse(@NonNull Response<ListCategoriessQuery.Data> response) {
            response.data().listCategoriess().items().forEach(item -> {
                Log.i("Foodname", item.name());
                list.add(new CategoriesModel((R.drawable.meat),item.name()));
            });
            CategoriesActivity.this.runOnUiThread(new Runnable() {
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
        setContentView(R.layout.activity_categories);
        initDatabase();
        query();
    }

    private void initDatabase() {
        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();
    }
    private void query () {
        mAWSAppSyncClient.query(ListCategoriessQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(todoCallback);
    }
    private void displayData () {
        recyclerView = findViewById(R.id.rv_food_categories);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLiLayoutManager = layoutManager;
        recyclerView.setLayoutManager(rvLiLayoutManager);
        CategoriesAdapter adapter = new CategoriesAdapter(getApplicationContext(),list);
        recyclerView.setAdapter(adapter);
    }
}