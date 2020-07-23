package com.example.c_food_main;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amazonaws.amplify.generated.graphql.CreateFavoriteFoodMutation;
import com.amazonaws.amplify.generated.graphql.ListFoodsQuery;
import com.amazonaws.amplify.generated.graphql.ListUsersQuery;
import com.amazonaws.amplify.generated.graphql.ListVitaminssQuery;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.amazonaws.mobileconnectors.cognitoauth.Auth;
import com.amazonaws.mobileconnectors.dynamodbv2.document.Table;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amplifyframework.core.Amplify;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;


import java.util.UUID;

import es.dmoral.toasty.Toasty;
import type.CreateFavoriteFoodInput;
import type.ModelIDInput;
import type.ModelStringInput;
import type.ModelUserFilterInput;
import type.ModelVitaminsFilterInput;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    private AWSAppSyncClient mAWSAppSyncClient;
    private String currentUser;
    private Context mContext;
    private Response<ListFoodsQuery.Data> foodList;
    private String COGNITO_POOL_ID = "ap-southeast-1:60ab7509-7a59-415e-9169-34911ac99c65";
    private Regions COGNITO_REGION = Regions.AP_SOUTHEAST_1;
    private AmazonDynamoDBClient dbClient;
    private Table dbTable;
    private String TABLE_NAME = "FavoriteFood-cxpgpugphfgqfhvznc67xk5wye-dev";
    int currentPosition;
    private GraphQLCall.Callback<ListUsersQuery.Data> userCallback = new GraphQLCall.Callback<ListUsersQuery.Data>() {
        @Override
        public void onResponse(@NonNull Response<ListUsersQuery.Data> response) {
            response.data().listUsers().items().forEach(item -> {
                Log.i("user", item.id());
                currentUser = item.id();
            });
        }
        @Override
        public void onFailure(@NonNull ApolloException e) {
            Log.i("ERROR OF QUERY", e.toString());
        }
    };
    private GraphQLCall.Callback<CreateFavoriteFoodMutation.Data> favFoodCallback = new GraphQLCall.Callback<CreateFavoriteFoodMutation.Data>() {
        @Override
        public void onResponse(@NonNull Response<CreateFavoriteFoodMutation.Data> response) {
            Log.i("fav food", "added" + " " + response.data().toString());

        }
        @Override
        public void onFailure(@NonNull ApolloException e) {
            Log.i("ERROR OF QUERY", e.toString());
        }
    };
    FoodAdapter(Context context, Response<ListFoodsQuery.Data> list) {
        this.mContext = context;
        this.foodList = list;
        initDatabase();
        connectDB connect = new connectDB();
        connect.execute();
        queryUser();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(R.layout.rv_food_item,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        ImageView image = holder.item_image;
        TextView name, calories;
        Button likeBtn;
        name = holder.item_name;
        calories = holder.item_calories;
        likeBtn = holder.likeBtn;
        currentPosition = position;
//      image.setImageResource(foodList.data().listFoods().items().get(position).getImage());
        name.setText(foodList.data().listFoods().items().get(position).name());
        calories.setText(Double.toString((foodList.data().listFoods().items().get(position).weight())));
        Log.i("BindViewHolder",Integer.toString(currentPosition));
    }



    @Override
    public int getItemCount() {
        return foodList.data().listFoods().items().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView item_image;
        TextView item_name, item_calories;
        Button likeBtn;
        String food_id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_image = itemView.findViewById(R.id.food_image);
            item_name = itemView.findViewById(R.id.food_name_text);
            item_calories = itemView.findViewById(R.id.food_calories);
            likeBtn = itemView.findViewById(R.id.like_food_btn);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentPosition = getAdapterPosition();
                    Log.i("ViewHolderPosition", Integer.toString(currentPosition));
                    String foodName = foodList.data().listFoods().items().get(currentPosition).name();
                    String foodId = foodList.data().listFoods().items().get(currentPosition).id();
                    Intent intent = new Intent(v.getContext(),FoodDetailActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("Food_Name", foodName);
                    intent.putExtra("Food_id",foodId);
                    v.getContext().startActivity(intent);
                }
            });
            likeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentPosition = getAdapterPosition();
                    String foodName = foodList.data().listFoods().items().get(currentPosition).name();
                    String foodId = foodList.data().listFoods().items().get(currentPosition).id();
                    addFavFood(foodId, currentUser);
                    Toasty.success(v.getContext(), "Add " + foodName + " into favorite food" , Toast.LENGTH_SHORT, true).show();
                }
            });
        }
    }
    private void initDatabase () {
        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(mContext)
                .awsConfiguration(new AWSConfiguration(mContext))
                .build();
//        try {
//        connectDB connect = new connectDB();
//        connect.execute();
//        } catch (Exception e) {
//            Log.i("Error Init", e.toString());
//        }
    }
    private void addFavFood (String foodID, String userID) {
        try {
            Document newMemo = new Document();
            newMemo.put("id", UUID.randomUUID().toString());
            newMemo.put("createdAt", "2020-07-21T07:48:37.265Z");
            newMemo.put("foodID", foodID);
            newMemo.put("userID", userID);
            newMemo.put("updatedAt", "2020-07-14T17:50:49.304Z");

            CreateItemAsyncTask task = new CreateItemAsyncTask();
            task.execute(newMemo);
        } catch (Exception e) {
            Log.i("error add Fav", e.toString());
        }
//        CreateFavoriteFoodInput createFavoriteFoodInput = CreateFavoriteFoodInput.builder()
//                                                            .foodID(foodID)
//                                                            .userID(userID)
//                                                            .build();
//        mAWSAppSyncClient.mutate(CreateFavoriteFoodMutation.builder().input(createFavoriteFoodInput).build())
//                .enqueue(favFoodCallback);
    }
    private void queryUser () {
        Log.i("user", Amplify.Auth.getCurrentUser().getUsername());
        String username = Amplify.Auth.getCurrentUser().getUsername();
        ModelStringInput modelStringInput = ModelStringInput.builder().eq(username).build();
        ModelUserFilterInput modelFoodFilterInput = ModelUserFilterInput.builder().username(modelStringInput).build();
        mAWSAppSyncClient.query(ListUsersQuery.builder().filter(modelFoodFilterInput ).build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(userCallback);

    }
    private class connectDB extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                    mContext, COGNITO_POOL_ID, COGNITO_REGION);
            dbClient = new AmazonDynamoDBClient(credentialsProvider);
            dbClient.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1));

            dbTable = Table.loadTable(dbClient, TABLE_NAME);

            return null;
        }
    }
    private class CreateItemAsyncTask extends AsyncTask<Document, Void, Void> {
        @Override
        protected Void doInBackground(Document... documents) {
            dbTable.putItem(documents[0]);
            return null;
        }
    }
}
