package com.example.c_food_main.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

<<<<<<< HEAD:c-food-main/app/src/main/java/com/example/c_food_main/activity/MainActivity.java
import com.amazonaws.amplify.generated.graphql.ListFoodsQuery;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClientException;
import com.amazonaws.mobileconnectors.appsync.ClearCacheOptions;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
=======
import com.amazonaws.amplify.generated.graphql.CreateFoodMutation;
import com.amazonaws.amplify.generated.graphql.CreateIngredientsFoodMutation;
import com.amazonaws.amplify.generated.graphql.CreateIngredientsMutation;
import com.amazonaws.amplify.generated.graphql.CreateMacronutrientsFoodMutation;
import com.amazonaws.amplify.generated.graphql.CreateMacronutrientsMutation;
import com.amazonaws.amplify.generated.graphql.CreateVitaminsFoodMutation;
import com.amazonaws.amplify.generated.graphql.CreateVitaminsMutation;
import com.amazonaws.amplify.generated.graphql.ListFoodsQuery;
import com.amazonaws.amplify.generated.graphql.ListUsersQuery;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClientException;
import com.amazonaws.mobileconnectors.appsync.ClearCacheOptions;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.amazonaws.mobileconnectors.dynamodbv2.document.Table;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.auth.options.AuthSignOutOptions;
>>>>>>> displayFoodDetail:c-food-main/app/src/main/java/com/example/c_food_main/MainActivity.java
import com.amplifyframework.core.Amplify;
import com.example.c_food_main.R;
import com.google.android.material.navigation.NavigationView;
import com.apollographql.apollo.GraphQLCall;

import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
<<<<<<< HEAD:c-food-main/app/src/main/java/com/example/c_food_main/activity/MainActivity.java
=======
import com.example.c_food_main.model.User;

import javax.annotation.Nonnull;

import type.CreateFoodInput;
import type.CreateIngredientsFoodInput;
import type.CreateIngredientsInput;
import type.CreateMacronutrientsFoodInput;
import type.CreateMacronutrientsInput;
import type.CreateUserInput;
import type.CreateVitaminsFoodInput;
import type.CreateVitaminsInput;
>>>>>>> displayFoodDetail:c-food-main/app/src/main/java/com/example/c_food_main/MainActivity.java

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private AWSAppSyncClient mAWSAppSyncClient;
    private String COGNITO_POOL_ID = "ap-southeast-1:60ab7509-7a59-415e-9169-34911ac99c65";
    private Regions COGNITO_REGION = Regions.AP_SOUTHEAST_1;
    private AmazonDynamoDBClient dbClient;
    private Table dbTable;
    private String TABLE_NAME = "FavoriteFood-cxpgpugphfgqfhvznc67xk5wye-dev";
    private GraphQLCall.Callback<ListFoodsQuery.Data> todosCallback = new GraphQLCall.Callback<ListFoodsQuery.Data>() {
        @Override
        public void onResponse(@NonNull Response<ListFoodsQuery.Data> response) {
            response.data().listFoods().items().forEach(item -> {
            Log.i("Foodname", item.name() + item.getClass().toString());
            });
        }
        @Override
        public void onFailure(@NonNull ApolloException e) {
            Log.i("ERROR OF QUERY", e.toString());
        }
    };

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

     MenuItem itemlogout;
  //  Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatabase();
        clearCache();
        query();

        itemlogout = findViewById(R.id.nav_logout);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("");
        navigationView.getMenu().clear();
        navigationView.inflateMenu(R.menu.main_menu);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


        final Animation animTranslate = AnimationUtils.loadAnimation(this,R.anim.anim_translate);
        RelativeLayout RelativeSearch =(RelativeLayout) findViewById(R.id.relative_search);
        RelativeLayout RelativeLikemeal =(RelativeLayout) findViewById(R.id.relative_likemeal);
        RelativeLayout RelativeMeal =(RelativeLayout) findViewById(R.id.relative_meal);
        RelativeLayout RelativeCategory =(RelativeLayout) findViewById(R.id.relative_category);
        RelativeLayout RelativeFeedback =(RelativeLayout) findViewById(R.id.relative_feedback);
        RelativeLayout Relativetrending =(RelativeLayout) findViewById(R.id.relative_trend);


        RelativeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animTranslate);
                Intent intent = new Intent(MainActivity.this,SearchFoodActivity.class);
                startActivity(intent);
            }
        });
        RelativeLikemeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animTranslate);
                Intent intent = new Intent(MainActivity.this,FavoriteFoodActivity.class);
                startActivity(intent);
            }
        });
        RelativeCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animTranslate);
                Intent intent = new Intent(MainActivity.this,CategoriesActivity.class);
                startActivity(intent);
            }
        });
        RelativeMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animTranslate);

            }
        });
        RelativeFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animTranslate);
                Intent intent = new Intent(MainActivity.this,FeedbackActivity.class);
                startActivity(intent);
            }
        });
        Relativetrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animTranslate);
                Intent intent = new Intent(MainActivity.this,TrendingActivity.class);
                startActivity(intent);
            }
        });

    }



    private void logOut(){
        Amplify.Auth.signOut(
                () -> {
                    goToLoginActivity();
                    Log.i("AuthQuickstart","Signed out globally");
        },
                error ->Log.e("AuthQuickstart",error.toString())
        );
    }
    private void goToLoginActivity(){
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void initDatabase () {
        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();

    }
    public void query() {
        mAWSAppSyncClient.query(ListFoodsQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(todosCallback);
    }
    public void clearCache() {
        try {
            mAWSAppSyncClient.clearCaches(
                    ClearCacheOptions.builder()
                            .clearQueries() // clear the query cache
                            .clearMutations() // clear the mutations queue
                            .clearSubscriptions() // clear the subscriptions metadata stored for Delta Sync
                            .build());
        } catch (AWSAppSyncClientException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onBackPressed()
    {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_logout:
                logOut();
                break;
            case R.id.nav_frofile:
                Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

}