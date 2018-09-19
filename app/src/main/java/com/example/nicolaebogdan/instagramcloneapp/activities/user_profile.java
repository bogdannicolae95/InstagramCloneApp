package com.example.nicolaebogdan.instagramcloneapp.activities;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.example.nicolaebogdan.instagramcloneapp.constants.Constants;
import com.example.nicolaebogdan.instagramcloneapp.adapters.GridAdapter;
import com.example.nicolaebogdan.instagramcloneapp.R;

public class user_profile extends AppCompatActivity {

    GridView gridView;
    GridAdapter adapter;
    TextView name;
    int [] pictures = new int[9];
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        name = findViewById(R.id.name_user);
        sh = PreferenceManager.getDefaultSharedPreferences(this);

        name.setText( sh.getString(Constants.USERNAME,"User Name"));

        gridView = findViewById(R.id.grid_view);

        adapter = new GridAdapter(user_profile.this,pictures);

        gridView.setAdapter(adapter);
    }
}
