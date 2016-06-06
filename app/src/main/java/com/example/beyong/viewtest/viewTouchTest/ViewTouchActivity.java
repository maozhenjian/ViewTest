package com.example.beyong.viewtest.viewTouchTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.beyong.viewtest.R;

public class ViewTouchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_touch);
        /**
         * 事件传递的顺序是先经过onTouch，再传递到onClick
         * onTouch方法是有返回值的，这里我们返回的是false
         * 返回true就认为这个事件被onTouch消费掉了，因而不会再继续向下传递。
         *ACTION_DOWN             = 0;
         *ACTION_MOVE             = 2;
         *ACTION_UP               = 1;
         */
        //每个event事件都会触发onTouch
        findViewById(R.id.V).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("TAG","点击了onTouch"+event.getAction());
                return false;
            }
        });

        //在ACTION_DOWN 中触发onLongClick
        findViewById(R.id.V).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e("TAG","点击了onLongClick");
                return false;
            }
        });

        //在ACTION_UP触发onClick
        findViewById(R.id.V).setOnClickListener(v-> Log.e("TAG","点击了onClick") );








    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("TAG","Activity:::dispatchTouchEvent"+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("TAG","Activity:::onTouchEvent"+event.getAction());
        return super.onTouchEvent(event);
    }

}
