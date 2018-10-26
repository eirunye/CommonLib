package com.eirunye.common.lib.net;

import android.util.Log;

import com.google.gson.GsonBuilder;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Author Eirunye
 * Created by on 2018/10/11.
 * Describe
 */
public class BaseApiImpl implements BaseApi {

    private volatile static Retrofit retrofit = null;
    protected Retrofit.Builder rb = new Retrofit.Builder();
    protected OkHttpClient.Builder ob = new OkHttpClient.Builder();

    public BaseApiImpl(String baseUrl) {

        rb.addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .setLenient()
                        .create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(ob.addInterceptor(getLoggerInterceptor()).build())
                .baseUrl(baseUrl);
    }

    private Interceptor getLoggerInterceptor() {

        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.HEADERS;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("ApiUrl", "--->" + message);
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }

    @Override
    public Retrofit getRetrofit() {

        if (retrofit == null) {
            synchronized (BaseApiImpl.class) {
                if (retrofit == null) {
                    retrofit = rb.build();
                }
            }
        }
        return retrofit;
    }

    @Override
    public OkHttpClient.Builder setInterceptor(Interceptor interceptor) {
        return ob.addInterceptor(interceptor);
    }

    @Override
    public Retrofit.Builder setConverterFactory(Converter.Factory factory) {
        return rb.addConverterFactory(factory);
    }
}
