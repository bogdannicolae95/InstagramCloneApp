package com.example.nicolaebogdan.instagramcloneapp.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserPost implements Serializable{

    private int avatarImage;
    private String userName;
    private String userLocation;
    private int previewUserAddImage;
    private int likes;
    private String description;
    private int userMiniImageAtComment;
    private int comments;
    private int likeImage;
    private int commentImage;
    private int shareImage;
    private List<PostComments> listOfComments;

    public UserPost(int avatarImg,String usrNme,String location,int previewImg,int like,int userMiniImageAtCommentC,String descriptionUsr,int postComment,int likeImg,int comment,int share){
        avatarImage = avatarImg;
        userName = usrNme;
        userLocation = location;
        previewUserAddImage = previewImg;
        likes = like;
        description = descriptionUsr;
        userMiniImageAtComment = userMiniImageAtCommentC;
        comments = postComment;
        likeImage = likeImg;
        commentImage = comment;
        shareImage = share;
        listOfComments =  new ArrayList<>();
    }

    public int getAvatarImage() {
        return avatarImage;
    }

    public int getPreviewUserAddImage() {
        return previewUserAddImage;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public String getUserName() {
        return userName;
    }

    public int getLikes() {
        return likes;
    }

    public int getCommentImage() {
        return commentImage;
    }

    public void setCommentImage(int commentImage) {
        this.commentImage = commentImage;
    }

    public int getLikeImage() {
        return likeImage;
    }

    public void setLikeImage(int likeImage) {
        this.likeImage = likeImage;
    }

    public int getShareImage() {
        return shareImage;
    }

    public void setShareImage(int shareImage) {
        this.shareImage = shareImage;
    }

    public int getUserMiniImageAtComment() {
        return userMiniImageAtComment;
    }

    public String getDescription() {
        return description;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public void setAvatarImage(int avatarImage) {
        this.avatarImage = avatarImage;
    }

    public void setUserMiniImageAtComment(int userMiniImageAtComment) {
        this.userMiniImageAtComment = userMiniImageAtComment;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPreviewUserAddImage(int previewUserAddImage) {
        this.previewUserAddImage = previewUserAddImage;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public List<PostComments> getListOfComments() {
        return listOfComments;
    }

    public void setListOfComments(List<PostComments> listOfComments) {
        this.listOfComments = listOfComments;
    }

    public void incrementComments(){
        comments = comments + 1;
        setComments(comments);
    }

    public void incrementLike(){
        likes = likes + 1;
        setLikes(likes);
    }

    public void decrementLike(){
        likes = likes - 1;
        setLikes(likes);
    }

    public void addComment(PostComments comment){
        comment.setUserAvatarImg(this.avatarImage);
        listOfComments.add(comment);
    }


}
