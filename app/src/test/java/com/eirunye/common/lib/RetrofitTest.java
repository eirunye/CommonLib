package com.eirunye.common.lib;

import android.util.ArrayMap;

import com.eirunye.common.lib.service.GithubService;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.RxThreadFactory;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Author Eirunye
 * Created by on 2018/10/11.
 * Describe
 */
public class RetrofitTest {

    ArrayMap<Integer, String> arrayMap = new ArrayMap();
    HashMap

    public void test() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://1234.ok/hello")
                .build();

        GithubService githubService = retrofit.create(GithubService.class);
        Call<List<String>> list = githubService.listRequest("1");

    }
}
