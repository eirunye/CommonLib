package com.eirunye.common.lib.simple.mvptest.model;

import com.eirunye.common.lib.simple.mvptest.bean.SimpleBean;


import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Author Eirunye
 * Created by on 2018/10/11.
 * Describe
 */
public interface RetrofitService {

    String BASE_URL = "https://news-at.zhihu.com/api/4/";

    @GET("news/latest")
    Observable<SimpleBean> test();

}
