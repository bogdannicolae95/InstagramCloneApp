package com.example.nicolaebogdan.instagramcloneapp.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nicolaebogdan.instagramcloneapp.constants.Constants;
import com.example.nicolaebogdan.instagramcloneapp.R;
import com.example.nicolaebogdan.instagramcloneapp.activities.Comments;
import com.example.nicolaebogdan.instagramcloneapp.classes.PostComments;
import com.example.nicolaebogdan.instagramcloneapp.classes.UserPost;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.RecyclerViewHolder> {


    private List<PostComments> comments;
    private Context context;
    private SharedPreferences prefsComm;
    private LayoutInflater mInflater;
    private List<UserPost> posts;
    private int postsIndex;
    private List<PostComments> auxListOfComments;
    private UserPost auxPost;

    public CommentsAdapter(Context context , List<PostComments> comm,int postsIndex){
        this.context = context;
        this.comments = comm;
        prefsComm = context.getSharedPreferences(Constants.PREFLIST, Comments.MODE_PRIVATE);
        this.postsIndex = postsIndex;
    }


    @Override
    public CommentsAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = mInflater.inflate(R.layout.comments_list_item,parent,false);

       RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder,  int position) {
         PostComments currentComment = comments.get(position);

        holder.usersCommAvatar.setImageResource(currentComment.getUserAvatarImg());
        holder.commText.setText(currentComment.getcComment());
        holder.commLike.setImageResource(currentComment.getcLikeImage());
        holder.commLikes.setText(String.valueOf(currentComment.getcLikesAtComment()) + " likes");

        posts = loadListFromSharedPref(Constants.PREFLIST,context);

        holder.commLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comments.get(holder.getAdapterPosition()).getcLikeImage() == R.drawable.like){
                    comments.get(holder.getAdapterPosition()).setcLikeImage(R.drawable.like_red);
                    comments.get(holder.getAdapterPosition()).incrementCommLike();
                    holder.commLike.setImageResource(comments.get(holder.getAdapterPosition()).getcLikeImage());
                    holder.commLikes.setText(String.valueOf(comments.get(holder.getAdapterPosition()).getcLikesAtComment()) + " likes");
                }else if(comments.get(holder.getAdapterPosition()).getcLikeImage() == R.drawable.like_red){
                    comments.get(holder.getAdapterPosition()).setcLikeImage(R.drawable.like);
                    comments.get(holder.getAdapterPosition()).decrementCommLike();
                    holder.commLike.setImageResource(comments.get(holder.getAdapterPosition()).getcLikeImage());
                    holder.commLikes.setText(String.valueOf(comments.get(holder.getAdapterPosition()).getcLikesAtComment()) + " likes");
                }

                //auxPost = posts.get(postsIndex);
               // auxListOfComments = auxPost.getListOfComments();
               // auxListOfComments.get(holder.getAdapterPosition());
                //posts.set(postsIndex,auxListOfComments.get(position));
            }
        });

    }



    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        CircleImageView usersCommAvatar;
        TextView commText,commLikes;
        ImageView commLike;


        public RecyclerViewHolder(View view) {
            super(view);
            usersCommAvatar = view.findViewById(R.id.user_avatar_comment_list_item);
            commText = view.findViewById(R.id.comment_from_user);
            commLike = view.findViewById(R.id.comment_like_list_item);
            commLikes = view.findViewById(R.id.comm_likes);

        }
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
}
