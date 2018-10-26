package com.eirunye.common.lib.mvp.baseimpl;

import com.eirunye.common.lib.mvp.BasePresenter;
import com.eirunye.common.lib.mvp.BaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Author Eirunye
 * Created by on 2018/10/11.
 * Describe
 */
public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter {

    protected V view;

    public BasePresenterImpl(V view) {

        this.view = view;

        create();
        start();
    }

    @Override
    public void create() {

    }

    @Override
    public void start() {

    }


    @Override
    public void detach() {
        this.view = null;
        unDisposable();
    }

    private CompositeDisposable compositeDisposable;

    @Override
    public void addDisposable(Disposable disposable) {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    @Override
    public void unDisposable() {

        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }

    }
}
