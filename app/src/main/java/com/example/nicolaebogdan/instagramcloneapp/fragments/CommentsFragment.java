package com.example.nicolaebogdan.instagramcloneapp.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolaebogdan.instagramcloneapp.R;
import com.example.nicolaebogdan.instagramcloneapp.adapters.CommentsAdapter;
import com.example.nicolaebogdan.instagramcloneapp.classes.PostComments;
import com.example.nicolaebogdan.instagramcloneapp.classes.UserPost;
import com.example.nicolaebogdan.instagramcloneapp.constants.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class CommentsFragment extends Fragment {

    UserPost mUser;
    CircleImageView userAvatar,userAvatarAtCommentFooter;
    TextView userNameComments;
    Button postComment;
    EditText addCommentEditText;
    int position;

    RecyclerView recyclerView;
    CommentsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    SharedPreferences myPrefs;

    List<UserPost> posts;
    List<PostComments> comments;

    public CommentsFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments().containsKey(Constants.COMMENT)){
            mUser = (UserPost) getArguments().getSerializable(Constants.COMMENT);
        }
        if(getArguments().containsKey("index")) {
            position = getArguments().getInt("index");
            Log.d("position", "onCreate: " + position);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.preview_comments,container,false);

        userAvatar = rootView.findViewById(R.id.avatar_image_view_comment_preview_description);
        userAvatar.setImageResource(mUser.getAvatarImage());

        userNameComments = rootView.findViewById(R.id.description_user_in_comment_preview);
        userNameComments.setText(mUser.getUserName() + " : " + mUser.getDescription());

        userAvatarAtCommentFooter = rootView.findViewById(R.id.avatar_image_comment_preview);
        userAvatarAtCommentFooter.setImageResource(mUser.getAvatarImage());

        postComment = rootView.findViewById(R.id.post_comment);
        addCommentEditText = rootView.findViewById(R.id.add_a_comment_edit_text);


        myPrefs = getActivity().getSharedPreferences(Constants.PREFSHAREDDOMAIN,getContext().MODE_PRIVATE);

        if(myPrefs.contains(Constants.PREFLIST)){
            posts = loadListFromSharedPref(Constants.PREFLIST,getActivity());
            Log.d("posts size", "onCreateView: " + posts.size());
            comments = posts.get(position).getListOfComments();
        }

        recyclerView = rootView.findViewById(R.id.recicler_view_comments);
        adapter = new CommentsAdapter(getActivity(),comments,position);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        postComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!addCommentEditText.getText().toString().equals("")) {
                    try {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {

                    }
                    posts.get(position).addComment(new PostComments(addCommentEditText.getText().toString()));
                    adapter.notifyDataSetChanged();
                    addCommentEditText.setText("");
                    posts.get(position).incrementComments();
                    saveListToSharedPref(getContext(), Constants.PREFLIST, posts);

                }else{
                    Toast.makeText(getContext(),"You need to enter something!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return  rootView;
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
        SharedPreferences pSharedPref = context.getSharedPreferences(Constants.PREFLIST, Context.MODE_PRIVATE);
        if (pSharedPref != null){
            Gson gson = new Gson();
            String listString = gson.toJson(list);
            //save in shared prefs
            pSharedPref.edit().putString(key, listString).apply();
        }
    }
}
