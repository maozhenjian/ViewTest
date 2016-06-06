package com.example.beyong.viewtest.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beyong.viewtest.MyApplication;
import com.example.beyong.viewtest.R;

/**
 * Created by 振坚 on 2016/4/22.
 */
public class ToastUtils {
    public static class Utils {
        /**
         * 显示toast
         *
         * @param content  内容
         * @param duration 持续时间
         */
        public static void toast(String content, int duration) {
            if (content == null) {
                return;
            } else {
                MyApplication.ToastMgr.builder.display(content, duration);
            }
        }

        /**
         * 显示默认持续时间为short的Toast
         *
         * @param content 内容
         */
        public static void toast(String content) {
            if (content == null) {
                return;
            } else {
                MyApplication.ToastMgr.builder.display(content, Toast.LENGTH_SHORT);
            }
        }
    }

/**
**  这段放在Application中
* */
//    public enum ToastMgr{
//        builder;
//        private View view;
//        private TextView tv;
//        private Toast toast;
//        /**
//         * 初始化Toast
//         * @param context
//         */
//        public void init(Context context){
//            //自定义Toast布局
//            view = LayoutInflater.from(context).inflate(R.layout.toast_view, null);
//            tv = (TextView) view.findViewById(R.id.toast_textview);
//            toast = new Toast(context);
//            toast.setView(view);
//        }
//
//        /**
//         * 显示Toast
//         * @param content
//         * @param duration Toast持续时间
//         */
//        public void display(CharSequence content , int duration){
//            if (content.length()!=0) {
//                tv.setText(content);
//                toast.setDuration(duration);
////                toast.setGravity(Gravity.CENTER, 0, 0);
//                toast.show();
//            }
//        }
//    }
}
