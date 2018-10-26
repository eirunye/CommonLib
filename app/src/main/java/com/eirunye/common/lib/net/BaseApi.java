package com.eirunye.common.lib.net;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Author Eirunye
 * Created by on 2018/10/11.
 * Describe
 */
public interface BaseApi {
    Retrofit getRetrofit();
    OkHttpClient.Builder setInterceptor(Interceptor interceptor);
    Retrofit.Builder setConverterFactory(Converter.Factory factory);
}
