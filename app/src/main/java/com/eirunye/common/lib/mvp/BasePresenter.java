package com.eirunye.common.lib.mvp;

import io.reactivex.disposables.Disposable;

/**
 * Author Eirunye
 * Created by on 2018/10/11.
 * Describe
 */
public interface BasePresenter {

    void start();

    void create();

    void detach();

    void addDisposable(Disposable disposable);

    void unDisposable();
}
