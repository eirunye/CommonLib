package com.eirunye.common.lib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.eirunye.common.lib.R;

/**
 * Author Eirunye
 * Created by on 2018/10/15.
 * Describe
 */
public class MyView extends View {

    private String text;

    private int textColor;

    private int textSize;

    private Paint paint = null;
    private Rect rect;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyView, defStyleAttr, 0);

        paint = new Paint();
        paint.setAntiAlias(true);
        text = typedArray.getString(R.styleable.MyView_Text);
        textColor = typedArray.getColor(R.styleable.MyView_TextColor, Color.BLUE);
        textSize = typedArray.getInt(R.styleable.MyView_TextSize,80);
        typedArray.recycle();//回收
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        rect=new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        System.out.println(MeasureSpec.getMode(MeasureSpec.EXACTLY));

        int width;
        int height ;
        if (widthMode == MeasureSpec.EXACTLY)
        {
            width = widthSize;
        } else
        {
            paint.setTextSize(textSize);
           Path path = new Path();
//           path.rQuadTo();
            paint.getTextBounds(text, 0, text.length(), rect);
            float textWidth = rect.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        } else
        {
            paint.setTextSize(textSize);
            paint.getTextBounds(text, 0, text.length(), rect);
            float textHeight = rect.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }


        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(text, getWidth() / 2 , getHeight() / 4, paint);
//        canvas.restoreToCount();
//        canvas.saveLayer()
    }
}
