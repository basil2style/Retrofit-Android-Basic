package com.makeinfo.flowerpi.API;

import com.makeinfo.flowerpi.model.User;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;


public interface GitHubService {
    @GET("/users/{username}")
    Call<User> getUser(@Path("username") String username);
}
