package com.eirunye.common.lib;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eirunye.common.lib.simple.mvptest.mvp.activity.SimpleActivity;
import com.eirunye.common.lib.uitls.ConvertUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    private TextView mTvShowParams;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        mTvShowParams = (TextView) findViewById(R.id.tv_show_params);
//        mTvShowParams.setText(getScreenParams());
        startActivity(new Intent(this, SimpleActivity.class));
//        ImageView imageView = (ImageView) findViewById(R.id.imageView);
//
//        String url = "http://p3.pstatp.com/origin/pgc-image/15220293999066398493c24";
//        Glide.with(this)
//                .load(url)
//                .into(imageView);
////        Glide.with(this).load("http://goo.gl/gEgYUd").into(imageView);
//
//        imageView.setOnClickListener(this::okok);
//        MyHandler myHandler = new MyHandler();
//        Message message = new Message();
//        message.arg1 = 1;
//        message.obj = "asd";
//        message.what = 1;
//        myHandler.sendMessage(message);
//        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
//        }, 1000, TimeUnit.HOURS);
//        Executors.newSingleThreadExecutor().execute(()->{
//
//        });

    }

    public void startAnim(){
        ValueAnimator animator = ValueAnimator.ofInt(0,1000);
        animator.setDuration(4000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

            }
        });
        animator.setTarget(imageView);
        animator.start();
    }


    public void getScreenParams(View view) {
        mTvShowParams.setText(getScreenParams());
        dynamicSet();
    }

    /**
     * 获取屏幕相关参数
     *
     * @return
     */
    public String getScreenParams() {
        DisplayMetrics dm = new DisplayMetrics();
        //        dm = getResources().getDisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int heightPixels = dm.heightPixels;
        int widthPixels = dm.widthPixels;
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;
        int densityDpi = dm.densityDpi;
        float density = dm.density;
        float scaledDensity = dm.scaledDensity;
        float heightDP = heightPixels / density;
        float widthDP = widthPixels / density;
        float smallestWidthDP;
        if (widthDP < heightDP) {
            smallestWidthDP = widthDP;
        } else {
            smallestWidthDP = heightDP;
        }
        String str = "heightPixels: " + heightPixels + "px";
        str += "\nwidthPixels: " + widthPixels + "px";
        str += "\nxdpi: " + xdpi + "dpi";
        str += "\nydpi: " + ydpi + "dpi";
        str += "\ndensityDpi: " + densityDpi + "dpi";
        str += "\ndensity: " + density;
        str += "\nscaledDensity: " + scaledDensity;
        str += "\nheightDP: " + heightDP + "dp";
        str += "\nwidthDP: " + widthDP + "dp";
        str += "\nsmallestWidthDP: " + smallestWidthDP + "dp";
        return str;
    }

    /**
     * 动态设置dp或sp
     */
    public void dynamicSet() {

        /**
         * 注意：
         * getDimension()方法并不是直接拿到dimens.xml文件中的dp或sp值
         * 而是將dimens.xml文件中的dp或sp值乘以屏幕密度（density）来换算成px值
         * 所以拿到该值后还需要换算成对应的dp或sp
         */

        /*获取sp值*/
        float pxValue = getResources().getDimension(R.dimen.sp_20);//获取对应资源文件下的sp值
        int spValue = ConvertUtils.px2sp(this, pxValue);//将px值转换成sp值
        mTvShowParams.setTextSize(spValue);//设置文字大小

        /*获取dp值*/
        float pxValue2 = getResources().getDimension(R.dimen.dp_360);//获取对应资源文件下的dp值
        int dpValue = ConvertUtils.px2dp(this, pxValue2);//将px值转换成dp值

        Log.d(TAG, "pxValue= " + pxValue);
        Log.d(TAG, "spValue= " + spValue);
        Log.d(TAG, "pxValue2= " + pxValue2);
        Log.d(TAG, "dpValue= " + dpValue);
    }

    private void okok(View view) {
        Toast.makeText(this, "aaaa", Toast.LENGTH_LONG).show();
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println((String) msg.obj);
        }
    }
}
