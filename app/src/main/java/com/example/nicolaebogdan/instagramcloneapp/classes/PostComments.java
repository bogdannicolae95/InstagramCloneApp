package com.example.nicolaebogdan.instagramcloneapp.classes;

import com.example.nicolaebogdan.instagramcloneapp.R;

public class PostComments {

    private String cComment;
    private int cLikeImage = R.drawable.like;
    private int cLikesAtComment;
    private int userAvatarImg;

    public PostComments(String comm){
        cComment = comm;
    }

    public int getcLikeImage() {
        return cLikeImage;
    }

    public void setcLikeImage(int cLikeImage) {
        this.cLikeImage = cLikeImage;
    }

    public int getcLikesAtComment() {
        return cLikesAtComment;
    }

    public void setcLikesAtComment(int cLikesAtComment) {
        this.cLikesAtComment = cLikesAtComment;
    }

    public String getcComment() {
        return cComment;
    }

    public void setcComment(String cComment) {
        this.cComment = cComment;
    }

    public void incrementCommLike(){
        cLikesAtComment = cLikesAtComment + 1;
        setcLikesAtComment(cLikesAtComment);
    }

    public void decrementCommLike(){
        cLikesAtComment = cLikesAtComment - 1;
        setcLikesAtComment(cLikesAtComment);
    }

    public int getUserAvatarImg() {
        return userAvatarImg;
    }

    public void setUserAvatarImg(int userAvatarImg) {
        this.userAvatarImg = userAvatarImg;
    }
}
