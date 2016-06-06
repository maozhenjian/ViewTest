package com.example.beyong.viewtest.ViewModel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.beyong.viewtest.BaseActivity;
import com.example.beyong.viewtest.MyApplication;
import com.example.beyong.viewtest.R;

public class ToastActivity extends BaseActivity implements View.OnClickListener {
    private Button mButton1;
    private Button mButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        initViews();
        registerListener();
    }


    private void registerListener() {
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
    }


    private void initViews() {
        mButton1 = (Button) findViewById(R.id.button3);
        mButton2 = (Button) findViewById(R.id.button4);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:
                toast("Button1");
                break;
            case R.id.button4:
                toast("Button2");
                break;
        }
    }


//    public static class Utils {
//        /**
//         * 显示toast
//         *
//         * @param content  内容
//         * @param duration 持续时间
//         */
//        public static void toast(String content, int duration) {
//            if (content == null) {
//                return;
//            } else {
//                MyApplication.ToastMgr.builder.display(content, duration);
//            }
//        }
//
//        /**
//         * 显示默认持续时间为short的Toast
//         *
//         * @param content 内容
//         */
//        public static void toast(String content) {
//            if (content == null) {
//                return;
//            } else {
//                MyApplication.ToastMgr.builder.display(content, Toast.LENGTH_SHORT);
//            }
//        }
//    }
}
