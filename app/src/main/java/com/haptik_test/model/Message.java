package com.haptik_test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sameer on 7/2/2016.
 */
public class Message {

    private String body;
    @SerializedName("Name")
    @Expose
    private String name;

    private String username;
    @SerializedName("image-url")
    @Expose
    private String imageUrl;
    @SerializedName("message-time")
    @Expose
    private String messageTime;
    private Boolean isFavorite = false;

    public String getBody() {
        return body;
    }


    public void setBody(String body) {
        this.body = body;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getImageUrl() {
        return imageUrl;
    }


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getMessageTime() {
        return messageTime;
    }


    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }
}
