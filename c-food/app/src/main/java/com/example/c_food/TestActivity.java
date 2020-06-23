package com.example.c_food;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.SignInUIOptions;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobileconnectors.dynamodbv2.document.Table;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

public class TestActivity extends AppCompatActivity {
    private String COGNITO_POOL_ID = "ap-southeast-1:af7662cd-e55f-4cb6-936d-1f66082b1d72";
    private Regions COGNITO_REGION = Regions.AP_SOUTHEAST_1;
    private AmazonDynamoDBClient dbClient;
    private Table dbTable;
    private String TABLE_NAME = "testDB";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        connectDB testConnect =  new connectDB();
        testConnect.execute();
    }

    public void demoAdd(View view) {
        Document newMemo = new Document();
        newMemo.put("content", "a text");
        newMemo.put("ID", 1);
        CreateItemAsyncTask task = new CreateItemAsyncTask();
        task.execute(newMemo);
    }
    private class CreateItemAsyncTask extends AsyncTask<Document, Void, Void> {
        @Override
        protected Void doInBackground(Document... documents) {
            dbTable.putItem(documents[0]);
            return null;
        }
    }
    private class connectDB extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                    getApplicationContext(), COGNITO_POOL_ID, COGNITO_REGION);
            dbClient = new AmazonDynamoDBClient(credentialsProvider);
            dbClient.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1));

            dbTable = Table.loadTable(dbClient, TABLE_NAME);

            return null;
        }
    }
}
