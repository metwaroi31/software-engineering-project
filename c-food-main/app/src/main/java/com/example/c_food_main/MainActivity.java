package com.example.c_food_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.amazonaws.amplify.generated.graphql.CreateFoodMutation;
import com.amazonaws.amplify.generated.graphql.CreateIngredientsMutation;
import com.amazonaws.amplify.generated.graphql.CreateMacronutrientsMutation;
import com.amazonaws.amplify.generated.graphql.CreateVitaminsMutation;
import com.amazonaws.amplify.generated.graphql.ListFoodsQuery;
import com.amazonaws.amplify.generated.graphql.ListUsersQuery;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClientException;
import com.amazonaws.mobileconnectors.appsync.ClearCacheOptions;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.google.android.material.navigation.NavigationView;
import com.apollographql.apollo.GraphQLCall;

import com.amazonaws.amplify.generated.graphql.CreateFavoriteFoodMutation;
import com.amazonaws.amplify.generated.graphql.CreateUserMutation;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.c_food_main.model.User;

import javax.annotation.Nonnull;

import type.CreateFoodInput;
import type.CreateIngredientsInput;
import type.CreateMacronutrientsInput;
import type.CreateUserInput;
import type.CreateVitaminsInput;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private AWSAppSyncClient mAWSAppSyncClient;
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
    private GraphQLCall.Callback<CreateMacronutrientsMutation.Data> mutationMacronutrientsCallback = new GraphQLCall.Callback<CreateMacronutrientsMutation.Data>() {
        @Override
        public void onResponse(@Nonnull Response<CreateMacronutrientsMutation.Data> response) {
            Log.i("Results", "Added Todo");
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("Error", e.toString());
        }
    };
    private GraphQLCall.Callback<CreateIngredientsMutation.Data> mutationIngredientsCallback = new GraphQLCall.Callback<CreateIngredientsMutation.Data>() {
        @Override
        public void onResponse(@Nonnull Response<CreateIngredientsMutation.Data> response) {
            Log.i("Results", "Added Todo");
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("Error", e.toString());
        }
    };
    private GraphQLCall.Callback<CreateVitaminsMutation.Data> mutationVitaminsCallback = new GraphQLCall.Callback<CreateVitaminsMutation.Data>() {
        @Override
        public void onResponse(@Nonnull Response<CreateVitaminsMutation.Data> response) {
            Log.i("Results", "Added Todo");
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("Error", e.toString());
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

        navigationView.getMenu().clear();
        navigationView.inflateMenu(R.menu.main_menu);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


        final Animation animTranslate = AnimationUtils.loadAnimation(this,R.anim.anim_translate);
        addSamplingData();
        RelativeLayout RelativeSearch =(RelativeLayout) findViewById(R.id.relative_search);
        RelativeLayout RelativeLikemeal =(RelativeLayout) findViewById(R.id.relative_likemeal);
        RelativeLayout RelativeMeal =(RelativeLayout) findViewById(R.id.relative_meal);
        RelativeLayout RelativeCategory =(RelativeLayout) findViewById(R.id.relative_category);

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
            }
        });
        RelativeCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animTranslate);
            }
        });
        RelativeMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animTranslate);
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
    private void addSamplingData() {
        CreateMacronutrientsInput createMacronutrientsInput = CreateMacronutrientsInput.builder()
                                                                .name("macro")
                                                                .value("12")
                                                                .unit("unit").build();
        mAWSAppSyncClient.mutate(CreateMacronutrientsMutation.builder().input(createMacronutrientsInput).build())
                .enqueue(mutationMacronutrientsCallback);
        CreateMacronutrientsInput createMacronutrientsInput2 = CreateMacronutrientsInput.builder()
                .name("macro2")
                .value("123")
                .unit("unit2").build();
        mAWSAppSyncClient.mutate(CreateMacronutrientsMutation.builder().input(createMacronutrientsInput2).build())
                .enqueue(mutationMacronutrientsCallback);
        CreateIngredientsInput createIngredientsInput = CreateIngredientsInput.builder()
                .name("ingredients")
                .isvegeterian(false)
                .build();
        mAWSAppSyncClient.mutate(CreateIngredientsMutation.builder().input(createIngredientsInput).build())
                .enqueue(mutationIngredientsCallback);
        CreateIngredientsInput createIngredientsInput2 = CreateIngredientsInput.builder()
                .name("ingredients2")
                .isvegeterian(true)
                .build();
        mAWSAppSyncClient.mutate(CreateIngredientsMutation.builder().input(createIngredientsInput2).build())
                .enqueue(mutationIngredientsCallback);
        CreateVitaminsInput createVitaminsInput = CreateVitaminsInput.builder()
                                                    .name("A")
                                                    .unit("unit")
                                                    .value(12).build();
        mAWSAppSyncClient.mutate(CreateVitaminsMutation.builder().input(createVitaminsInput).build())
                .enqueue(mutationVitaminsCallback);
        CreateVitaminsInput createVitaminsInput2 = CreateVitaminsInput.builder()
                .name("A")
                .unit("unit")
                .value(12).build();
        mAWSAppSyncClient.mutate(CreateVitaminsMutation.builder().input(createVitaminsInput2).build())
                .enqueue(mutationVitaminsCallback);
    }
}