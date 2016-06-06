package com.example.beyong.viewtest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.beyong.viewtest.Utils.ToastUtils;

/**
 * Created by 振坚 on 2016/3/8.
 */
public class BaseActivity extends Activity {
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("TAG", getClass().getSimpleName());
        mContext=getApplication();
    }
    /**
     * 显示toast
     *
     * @param content  内容
     * @param duration 持续时间
     */
    public static void toast(String content){
        ToastUtils.Utils.toast(content);
    }

    public static void toast(String content , int duration){
        ToastUtils.Utils.toast(content, duration);
    }


}

