package com.example.beyong.viewtest.Other;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 振坚 on 2016/1/21.
 */
public class myView extends View {

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.e("2onTouchEvent","onTouchEvent"+event.getAction());
        return super.onTouchEvent(event);
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("2dispatchTouchEvent","dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }
    public myView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public myView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public myView(Context context) {
        super(context);
    }


    /**
     * measure()方法接收两个参数，widthMeasureSpec和heightMeasureSpec，这两个值分别用于确定视图的宽度和高度的规格和大小。
     MeasureSpec的值由specSize和specMode共同组成的，其中specSize记录的是大小，specMode记录的是规格。

     specMode一共有三种类型
     UNSPECIFIED(未指定),父元素不对子元素施加任何束缚，子元素可以得到任意想要的大小；
     EXACTLY(完全):父元素决定自元素的确切大小，子元素将被限定在给定的边界里而忽略它本身大小；
     AT_MOST(至多)，子元素至多达到指定大小的值。
     如果要让自定义控件支持WarpContent那就必须重写onMeasure来指定WarpContent时的大小
     */

    /*
        得到当前视图的宽高

    * 重写该方法目的就是给View在Warp_content属性下默认的大小
    * */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //如果不重写该方法， 在Warp_content属性下View填充父布局
        setMeasuredDimension(measureW(widthMeasureSpec), measureH(heightMeasureSpec));
    }

    /**
 * 这个方法是用于给视图进行布局的，也就是确定视图的位置
     * //控制子View的布局
 * */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private int measureW(int measureSpec){
        int reslut=0;
        int specMode=MeasureSpec.getMode(measureSpec);
        int specSize=MeasureSpec.getSize(measureSpec);
        if (specMode==MeasureSpec.EXACTLY){
            reslut=specSize;

        }else {
            reslut=200;
            if (specMode==MeasureSpec.AT_MOST){
                reslut=Math.min(reslut,specSize);
            }
        }

        return reslut;
    }


    private int measureH(int measureSpec){
        int reslut=0;
        int specMode=MeasureSpec.getMode(measureSpec);
        int specSize=MeasureSpec.getSize(measureSpec);
        if (specMode==MeasureSpec.EXACTLY){
            reslut=specSize;

        }else {
            reslut=200;
            if (specMode==MeasureSpec.AT_MOST){
                reslut=Math.min(reslut,specSize);
            }
        }
        return reslut;
    }

/**
 * 只有布局的方式才会调用
 *用于得到子View对象
 * */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
/**
 * 无论new还是布局都能调用
 * */

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }


}
