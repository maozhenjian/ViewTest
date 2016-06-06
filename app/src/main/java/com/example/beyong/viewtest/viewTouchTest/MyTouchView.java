package com.example.beyong.viewtest.viewTouchTest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 振坚 on 2016/6/4.
 */
public class MyTouchView extends View {
    public MyTouchView(Context context) {
        super(context);
    }

    public MyTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 只要你触摸到了任何一个控件，就一定会调用该控件的dispatchTouchEvent方法
     * */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("TAG","MyTouchView: "+"dispatchTouchEvent"+super.dispatchTouchEvent(event));
        return super.dispatchTouchEvent(event);
    }

     @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("TAG","MyTouchView: "+"onTouchEvent"+super.onTouchEvent(event));
      return true;
    }

}
