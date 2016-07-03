package com.haptik_test.model;


import com.squareup.okhttp.OkHttpClient;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Sameer on 7/2/2016.
 */

public class RestClient {

    private static Api REST_CLIENT;
    private static String ROOT = "http://haptik.co/android";

    static {
        setupRestClient();
    }

    private RestClient() {
    }

    public static Api get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(ROOT)
                .setClient(new OkClient(new OkHttpClient()));
        builder.setLogLevel(RestAdapter.LogLevel.FULL);

        RestAdapter restAdapter = builder.build();
        REST_CLIENT = restAdapter.create(Api.class);
    }
}