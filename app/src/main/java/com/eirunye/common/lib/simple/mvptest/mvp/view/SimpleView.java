package com.eirunye.common.lib.simple.mvptest.mvp.view;

import com.eirunye.common.lib.mvp.BasePresenter;
import com.eirunye.common.lib.mvp.BaseView;
import com.eirunye.common.lib.simple.mvptest.bean.SimpleBean;

import java.util.List;

/**
 * Author Eirunye
 * Created by on 2018/10/11.
 * Describe
 */
public interface SimpleView {

    interface view extends BaseView {
        void setData(List<SimpleBean.StoriesBean> data);
        void setHello(String data);
    }

    interface presenter extends BasePresenter {
        void getData();
        void getHello();
    }
}
