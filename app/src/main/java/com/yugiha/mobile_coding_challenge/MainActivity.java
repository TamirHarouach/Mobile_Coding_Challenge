package com.yugiha.mobile_coding_challenge;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RepoAdapter mAdapter;
    private List<RepoClass> mClass;
    private GetDataTask mTask = null;

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true); // Fixe the size of the recyclerView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // use a new Linear Layout Manager
        mClass = new ArrayList<>();
        mTask = new GetDataTask("https://api.github.com/search/repositories?q=created:%3E2017-10-22&sort=stars&order=desc"); // Use this Link to get data
        mTask.execute((Void) null); // execute the task
        mAdapter = new RepoAdapter(MainActivity.this, mClass); // create the adapter
        mRecyclerView.setAdapter(mAdapter); // set the adapter to the recyclerView

    }
    public class GetDataTask extends AsyncTask<Void, Void, Boolean> {
        private final String url;
        String Name,Desc,OwnerName,AvatarUrl_Owner;
        int Stars;
        JsonElement JsonEl;
        GetDataTask(String Url) {
            url = Url;
        }
        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                // Connect to the URL using java's native library
                URL url1 = new URL(url);
                URLConnection request = url1.openConnection();
                request.connect();
                // Convert to a JSON object to print data
                JsonParser jp = new JsonParser(); //from gson
                JsonEl = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a Json element
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success){
            JsonArray JsonItemsArray = JsonEl.getAsJsonObject().get("items").getAsJsonArray(); //Get Json Array.
            for (int i = 0 ; i < JsonItemsArray.size() ; i++){
                Name = JsonItemsArray.get(i).getAsJsonObject().get("name").getAsString(); // grab the Name
                Desc = JsonItemsArray.get(i).getAsJsonObject().get("description").getAsString(); //grab the Description
                Stars = JsonItemsArray.get(i).getAsJsonObject().get("stargazers_count").getAsInt(); //grab the number of stars
                OwnerName = JsonItemsArray.get(i).getAsJsonObject().get("owner").getAsJsonObject().get("login").getAsString(); //grab the owner name
                AvatarUrl_Owner = JsonItemsArray.get(i).getAsJsonObject().get("owner").getAsJsonObject().get("avatar_url").getAsString(); //grab the url of the avatar owner
                RepoClass repo = new RepoClass(Name,Desc,AvatarUrl_Owner,OwnerName,Stars); // call the constructor
                mClass.add(repo); // add the Item to the list
                mAdapter.notifyDataSetChanged(); // notify the adapter

            }
            }else{

            }


        }
        @Override
        protected void onCancelled() {


        }
    }

}
