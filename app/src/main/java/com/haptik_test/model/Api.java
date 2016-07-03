package com.haptik_test.model;


import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Sameer on 7/2/2016.
 */

public interface Api {

    //get chat messages
    @GET("/test_data/")
    void getChat(Callback<ChatMessages> callback);

}
