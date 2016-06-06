package com.example.beyong.viewtest.viewTouchTest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by 振坚 on 2016/6/4.
 */
public class MyViewGroup extends RelativeLayout {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    /**
     * 只要你触摸到了任何一个控件，就一定会调用该控件的dispatchTouchEvent方法
     *
     * */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("TAG","MyViewGroup: "+"dispatchTouchEvent"+super.dispatchTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("TAG","MyViewGroup: "+"onInterceptTouchEvent"+super.onInterceptTouchEvent(ev));
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("TAG","MyViewGroup: "+"onTouchEvent"+ super.onTouchEvent(event));
        return super.onTouchEvent(event);
    }
}
