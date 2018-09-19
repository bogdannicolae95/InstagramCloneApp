package com.example.nicolaebogdan.instagramcloneapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.nicolaebogdan.instagramcloneapp.constants.Constants;
import com.example.nicolaebogdan.instagramcloneapp.R;
import com.example.nicolaebogdan.instagramcloneapp.adapters.TimelineAdapter;
import com.example.nicolaebogdan.instagramcloneapp.classes.UserPost;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Timeline extends AppCompatActivity{

    Toolbar toolbar;
    SharedPreferences sharedPreferences;
    ImageView avatarImage;
    ImageView previewImage;
    ImageView goToProfile;

    SharedPreferences myPrefs;

    RecyclerView recyclerView;
    TimelineAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    String userName;

    List<UserPost> usersPost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        Bundle bundle = getIntent().getExtras();

        userName = bundle.getString(Constants.USERNAME);

        previewImage = findViewById(R.id.preview_avatar_image);
        avatarImage = findViewById(R.id.avatar_image_view);
        goToProfile = findViewById(R.id.avatar_toolbar);

        myPrefs = getSharedPreferences(Constants.PREFSHAREDDOMAIN,MODE_PRIVATE);

        usersPost = new ArrayList<>();

        if(myPrefs.contains(Constants.PREFLIST)){
            usersPost = loadListFromSharedPref(Constants.PREFLIST,getApplicationContext());
        }else {

            usersPost.add(new UserPost(R.drawable.test, userName, "Romania", R.drawable.testimg, 100, R.drawable.test, "Hello Guys!", 0, R.drawable.like, R.drawable.comment, R.drawable.share));
            usersPost.add(new UserPost(R.drawable.test, userName, "S.U.A", R.drawable.usr2, 5, R.drawable.test, "Good Time", 0, R.drawable.like, R.drawable.comment, R.drawable.share));
            usersPost.add(new UserPost(R.drawable.test, userName, "Germany", R.drawable.testimg, 0, R.drawable.test, "Heyyyyy", 0, R.drawable.like, R.drawable.comment, R.drawable.share));
            saveListToSharedPref(this,Constants.PREFLIST,usersPost);
        }

        recyclerView = findViewById(R.id.timeline_recyclerview_view);
        adapter = new TimelineAdapter(this,usersPost);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        goToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Timeline.this,user_profile.class);
                startActivity(i);
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        myPrefs = getSharedPreferences(Constants.PREFLIST,MODE_PRIVATE);
        usersPost = new ArrayList<>();

        if(myPrefs.contains(Constants.PREFLIST)){
            usersPost = loadListFromSharedPref(Constants.PREFLIST,getApplicationContext());
        }else{
            usersPost.add(new UserPost(R.drawable.test, userName, "Romania", R.drawable.testimg, 100, R.drawable.test, "Hello Guys!", 15, R.drawable.like, R.drawable.comment, R.drawable.share));
            usersPost.add(new UserPost(R.drawable.test, userName, "S.U.A", R.drawable.usr2, 5, R.drawable.test, "Good Time", 4, R.drawable.like, R.drawable.comment, R.drawable.share));
            usersPost.add(new UserPost(R.drawable.test, userName, "Germany", R.drawable.testimg, 0, R.drawable.test, "Heyyyyy", 196, R.drawable.like, R.drawable.comment, R.drawable.share));

        }

        if(recyclerView != null){

            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

        }
    }

    public static List<UserPost> loadListFromSharedPref(String key, Context context){
       List<UserPost> outputList = new ArrayList<>();
        SharedPreferences pSharedPref = context.getSharedPreferences(Constants.PREFSHAREDDOMAIN, Context.MODE_PRIVATE);
        try{
            //get from shared prefs
            String storedListString = pSharedPref.getString(key, (new JSONObject()).toString());
            java.lang.reflect.Type type = new TypeToken<List<UserPost>>(){}.getType();
            Gson gson = new Gson();
            return  gson.fromJson(storedListString, type);
        }catch(Exception e){
            e.printStackTrace();
        }
        return outputList;
    }

    //To save List of Posts to Shared preferences
    public static void saveListToSharedPref(Context context,String key,List<UserPost> list){
        SharedPreferences pSharedPref = context.getSharedPreferences(Constants.PREFSHAREDDOMAIN, Context.MODE_PRIVATE);
        if (pSharedPref != null){
            Gson gson = new Gson();
            String listString = gson.toJson(list);
            //save in shared prefs
            pSharedPref.edit().putString(key, listString).apply();
        }
    }

}
