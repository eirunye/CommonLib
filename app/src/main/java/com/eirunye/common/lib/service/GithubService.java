package com.eirunye.common.lib.service;

import com.eirunye.common.lib.MyTest;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Author Eirunye
 * Created by on 2018/10/11.
 * Describe
 */
public interface GithubService {

    //GET
    @GET("user/{id}/req")
    Call<List<String>> listRequest(@Path("id") String id);


    //add header
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("hello/{id}")
    Call<List<String>> listCallReq(@Path("id") int id);

    //POST
    @POST("user/new")
    Call<List<String>> listPost(@Body String s);



}
