package com.eirunye.common.lib;

import android.graphics.Bitmap;
import android.util.Log;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.operators.observable.ObservableAll;
import io.reactivex.schedulers.Schedulers;

/**
 * Author Eirunye
 * Created by on 2018/10/11.
 * Describe
 */
public class ActivityTest {

    private static final String TAG = "ActivityTest";


    public static void main(String[] args) {

        Observable.interval(1, TimeUnit.SECONDS).takeUntil(new Predicate<Long>() {
            @Override
            public boolean test(Long aLong) throws Exception {
                return (aLong > 3);
            }
        }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "发送了事件 " + aLong);
                System.out.println(aLong);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "发送完成");
                System.out.println("发送完成");
            }
        });
    }


    public void tess() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Exception {

                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("hahah");
                }

                return Observable.fromIterable(list).delay(10, TimeUnit.MICROSECONDS);
            }
        }).subscribe(System.out::println);
    }

    public void setTestRequst() {


        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext(request());
            }
        });

        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        };

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };


        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);//consumer

    }

    private String request() {

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        return "aaaaaaaa";
    }


    public void test3() {
        String a[] = {"a", "b", "c"};
        Observable observable = Observable.fromArray(a);
        Observable observable1 = Observable.just(a);
        // 将会依次调用：
        // onNext("a");
        // onNext("b");
        // onNext("c");
        // onCompleted();

        Observable.just(a).subscribe(System.out::println);

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(System.out::println);


        Observable.just("abcd").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });

    }


    public void test4(){





    }

}
