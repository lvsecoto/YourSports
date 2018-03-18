package com.yjy.your.sports.server;

import io.reactivex.Observable;
import retrofit2.http.POST;

public interface Server {
    @POST("situp")
    Observable<Result> addSitup();
}
