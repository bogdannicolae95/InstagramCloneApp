package com.example.nicolaebogdan.instagramcloneapp.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nicolaebogdan.instagramcloneapp.fragments.CommentsFragment;
import com.example.nicolaebogdan.instagramcloneapp.constants.Constants;
import com.example.nicolaebogdan.instagramcloneapp.R;
import com.example.nicolaebogdan.instagramcloneapp.classes.UserPost;

public class Comments extends AppCompatActivity {

    UserPost usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        if(savedInstanceState == null){
            Bundle arguments = new Bundle();
            arguments.putAll(getIntent().getExtras());

            CommentsFragment fragment = new CommentsFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().add(R.id.container_comments, fragment).commit();

        }

        if(getIntent().getExtras().containsKey(Constants.COMMENT)){
            usr = (UserPost) getIntent().getSerializableExtra(Constants.COMMENT);
        }

    }
}
