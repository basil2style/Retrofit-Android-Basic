package com.makeinfo.flowerpi.API;

import com.makeinfo.flowerpi.model.gitmodel;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;


public interface gitapi {
    @GET("/users/{user}")
   public void getFeed(@Path("user") String user,Callback<gitmodel> response);

}
