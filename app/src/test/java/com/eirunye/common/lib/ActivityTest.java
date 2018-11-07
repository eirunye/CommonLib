package com.eirunye.common.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.transform.Source;

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


    @Test
    public void test4() {


        int a[] = {12, 34, 1, 67, 24, 9, 18};
        quickSort(a, 0, 6);

        for (int ii = 0; ii < a.length; ii++) {
            System.out.println(a[ii]);
        }

    }


    public void quickSort(int[] a, int start, int end) {
        if (start < end) {
            int baseNum = a[start];//选基准值
            int i = start;
            int j = end;
            do {
                while ((a[i] < baseNum) && i < end) { //如果索引值小于基准值
                    i++;
                }
                while ((a[j] > baseNum) && j > start) {//从后边往前面推，如果索引值大于于基准值
                    j--;
                }
                if (i <= j) {
                    int midNum = a[i];
                    a[i] = a[j];
                    a[j] = midNum;
                    i++;
                    j--;
                }
            } while (i <= j);
            if (start < j) {
                quickSort(a, start, j);
            }
            if (end > i) {
                quickSort(a, i, end);
            }

        }
    }

}
