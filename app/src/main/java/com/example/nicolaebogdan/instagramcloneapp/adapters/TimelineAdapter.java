package com.example.nicolaebogdan.instagramcloneapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.nicolaebogdan.instagramcloneapp.constants.Constants;
import com.example.nicolaebogdan.instagramcloneapp.R;
import com.example.nicolaebogdan.instagramcloneapp.activities.Comments;
import com.example.nicolaebogdan.instagramcloneapp.activities.Timeline;
import com.example.nicolaebogdan.instagramcloneapp.classes.UserPost;
import com.google.gson.Gson;

import java.util.List;


import de.hdodenhof.circleimageview.CircleImageView;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.RecyclerViewHolder>{

    private List<UserPost> posts;
    private Context context;
    private SharedPreferences prefs;
    private LayoutInflater mInflater;
    private int i = 0;

    public TimelineAdapter(Context context , List<UserPost> post){
        this.context = context;
        this.posts = post;
        prefs = context.getSharedPreferences(Constants.PREFLIST, Timeline.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public TimelineAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = mInflater.inflate(R.layout.list_item_timeline,parent,false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder,  int position) {
        UserPost currentUsr = posts.get(position);
        holder.userAvatar.setImageResource(currentUsr.getAvatarImage());
        holder.userName.setText(currentUsr.getUserName());
        holder.location.setText(currentUsr.getUserLocation());
        holder.addImagePreview.setImageResource(currentUsr.getPreviewUserAddImage());
        holder.likeImage.setImageResource(currentUsr.getLikeImage());
        holder.commentImage.setImageResource(currentUsr.getCommentImage());
        holder.shareImage.setImageResource(currentUsr.getShareImage());
        holder.likes.setText(currentUsr.getLikes() + " likes");
        holder.description.setText(currentUsr.getUserName() + ": " + currentUsr.getDescription() + " !");
        holder.comments.setText(currentUsr.getComments() + " comments");
        holder.userAvatarCommentLittle.setImageResource(currentUsr.getUserMiniImageAtComment());

        holder.likeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(posts.get(holder.getAdapterPosition()).getLikeImage() == R.drawable.like) {
                    posts.get(holder.getAdapterPosition()).setLikeImage(R.drawable.like_red);
                    posts.get(holder.getAdapterPosition()).incrementLike();
                    holder.likeImage.setImageResource(posts.get(holder.getAdapterPosition()).getLikeImage());
                    holder.likes.setText(String.valueOf(posts.get(holder.getAdapterPosition()).getLikes()) + " likes");

                    holder.likeDoubleClick.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            holder.likeDoubleClick.setVisibility(View.GONE);
                        }
                    }, 1000);


                } else if(posts.get(holder.getAdapterPosition()).getLikeImage() == R.drawable.like_red){
                    posts.get(holder.getAdapterPosition()).setLikeImage(R.drawable.like);
                    posts.get(holder.getAdapterPosition()).decrementLike();
                    holder.likeImage.setImageResource(posts.get(holder.getAdapterPosition()).getLikeImage());
                    holder.likes.setText(String.valueOf(posts.get(holder.getAdapterPosition()).getLikes()) + " likes");
                }

                saveListToSharedPref(context,Constants.PREFLIST,posts);
            }
        });


        holder.addImagePreview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if(posts.get(holder.getAdapterPosition()).getLikeImage() == R.drawable.like) {
                    posts.get(holder.getAdapterPosition()).setLikeImage(R.drawable.like_red);
                    posts.get(holder.getAdapterPosition()).incrementLike();
                    holder.likeImage.setImageResource(posts.get(holder.getAdapterPosition()).getLikeImage());
                    holder.likes.setText(String.valueOf(posts.get(holder.getAdapterPosition()).getLikes()) + " likes");

                    holder.likeDoubleClick.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            holder.likeDoubleClick.setVisibility(View.GONE);
                        }
                    }, 2000);

                }else if(posts.get(holder.getAdapterPosition()).getLikeImage() == R.drawable.like_red){
                    Toast.makeText(context,"You already like this post!",Toast.LENGTH_SHORT).show();
                }

                saveListToSharedPref(context,Constants.PREFLIST,posts);
                return true;
            }
        });

        holder.commentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posts.get(holder.getAdapterPosition()) != null) {
                    Intent intent = new Intent(context, Comments.class);
                    intent.putExtra(Constants.COMMENT, posts.get(holder.getAdapterPosition()));
                    intent.putExtra("index", holder.getAdapterPosition());
                    context.startActivity(intent);
                }
            }
        });

        holder.editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posts.get(holder.getAdapterPosition()) != null) {
                    Intent intent = new Intent(context, Comments.class);
                    intent.putExtra(Constants.COMMENT, posts.get(holder.getAdapterPosition()));
                    intent.putExtra("index", holder.getAdapterPosition());
                    context.startActivity(intent);
                }
            }
        });

        holder.comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posts.get(holder.getAdapterPosition()) != null) {
                    Intent intent = new Intent(context, Comments.class);
                    intent.putExtra(Constants.COMMENT, posts.get(holder.getAdapterPosition()));
                    intent.putExtra("index", holder.getAdapterPosition());
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView userName,location,likes,description,comments,editText;
        private CircleImageView userAvatar,userAvatarCommentLittle;
        private ImageView addImagePreview,likeImage,commentImage,shareImage,likeDoubleClick;


        public RecyclerViewHolder(View view) {
            super(view);
            userAvatar = view.findViewById(R.id.avatar_image_view);
            userName = view.findViewById(R.id.username_timeline);
            location = view.findViewById(R.id.user_region_text_view);
            addImagePreview = view.findViewById(R.id.preview_avatar_image);
            likeImage = view.findViewById(R.id.like);
            commentImage = view.findViewById(R.id.comment);
            shareImage = view.findViewById(R.id.share);
            likes = view.findViewById(R.id.likes);
            description = view.findViewById(R.id.description);
            comments = view.findViewById(R.id.preview_comments_text_view);
            userAvatarCommentLittle = view.findViewById(R.id.avatar_user_comment);
            editText = view.findViewById(R.id.edit_text);
            likeDoubleClick = view.findViewById(R.id.like_double_click);
        }
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
