package com.yjy.your.sports.server;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YourSports {
    private static final YourSports ourInstance = new YourSports();
    private final Server mServer;

    public static YourSports getInstance() {
        return ourInstance;
    }

    private YourSports() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.108.95.70")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mServer = retrofit.create(Server.class);
    }

    public Server getServer() {
        return mServer;
    }
}
