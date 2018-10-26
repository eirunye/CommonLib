package com.eirunye.common.lib.simple.mvptest.model;

import com.eirunye.common.lib.net.BaseApi;
import com.eirunye.common.lib.net.BaseApiImpl;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Author Eirunye
 * Created by on 2018/10/11.
 * Describe
 */
public class Api extends BaseApiImpl {

    private static Api api = new Api(RetrofitService.BASE_URL);

    public Api(String baseUrl) {
        super(baseUrl);
    }

    public static RetrofitService getInstance() {
        return api.getRetrofit().create(RetrofitService.class);
    }
}
