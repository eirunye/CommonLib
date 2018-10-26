package com.eirunye.common.lib.simple.mvptest.mvp.presenter;

import android.annotation.SuppressLint;

import com.eirunye.common.lib.mvp.baseimpl.BasePresenterImpl;
import com.eirunye.common.lib.net.ExceptionHelper;
import com.eirunye.common.lib.simple.mvptest.bean.SimpleBean;
import com.eirunye.common.lib.simple.mvptest.model.Api;
import com.eirunye.common.lib.simple.mvptest.mvp.view.SimpleView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author Eirunye
 * Created by on 2018/10/11.
 * Describe
 */
public class SimplePresenter extends BasePresenterImpl<SimpleView.view> implements SimpleView.presenter {


    public SimplePresenter(SimpleView.view view) {
        super(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getData() {

        Api.getInstance().test()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        addDisposable(disposable);
                        view.showLoad("加载中...");
                    }
                })
                .map(new Function<SimpleBean, List<SimpleBean.StoriesBean>>() {
                    @Override
                    public List<SimpleBean.StoriesBean> apply(@NonNull SimpleBean testBean) throws Exception {
                        return testBean.getStories();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(storiesBeen -> {
                    view.dismissLoad();
                    view.setData(storiesBeen);
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        view.dismissLoad();
                        ExceptionHelper.handleException(throwable);
                    }
                });

//        Api.getInstance().test().subscribeOn(Schedulers.io())
//                .doOnSubscribe(Disposable::dispose)
//                .map(SimpleBean::getStories)
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(storiesBeen -> {
//                    view.dismissLoad();
//                    view.setData(storiesBeen);
//                },throwable->{
//                    view.dismissLoad();
//                    ExceptionHelper.handleException(throwable);
//                });
//


    }

    @Override
    public void getHello() {
        view.setHello("哈哈哈！");
    }
}
