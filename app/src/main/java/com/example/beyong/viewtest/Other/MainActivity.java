package com.example.beyong.viewtest.Other;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.beyong.viewtest.R;

public class MainActivity extends Activity {
    private int lastX;
    private int lastY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        LinearLayout main= (LinearLayout) findViewById(R.id.out);

//         LayoutInflater layoutInflater = LayoutInflater.from(this);
/*
//     2:   LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//第一个参数就是要加载的布局id，第二个参数是指给该布局的外部再嵌套一层父布局，如果不需要就直接传null
*/
//        View buttonLayout= layoutInflater.inflate(R.layout.layout2, null);

//        main.addView(buttonLayout);


        final myView b= (myView) findViewById(R.id.my);
//        final ImageView b= (ImageView) findViewById(R.id.igg);

        b.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("onTouchaaaaa", "acty");
                int eventX = (int) event.getRawX();
                int eventY = (int) event.getRawY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = eventX;
                        lastY = eventY;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int dx = eventX - lastX;
                        int dy = eventY - lastY;

                        int left = b.getLeft() + dx;
                        int tops = b.getTop() + dy;
                        int right = b.getRight() + dx;
                        int bottom = b.getBottom() + dy;
                        if (left < 0) {
                            right += -left;
                            left = 0;
                        }
                        if (tops < 0) {
                            bottom += -tops;
                            tops = 0;
                        }

                        //对视图重新定位
                        b.layout(left, tops, right, bottom);

                        lastX = eventX;
                        lastY = eventY;
                        break;
                }

                return true;
            }
        });
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("1onTouchEvent", "acty");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        Log.e("1dispatchTouchEvent", "acty");
        return super.dispatchTouchEvent(ev);
    }



}
