package com.example.beyong.viewtest.HTTP;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.beyong.viewtest.R;


/**
 * Created by zhenjian_mao on 2015/11/29.
 */
public class MyAsyncTask extends AsyncTask<String,Integer,String>{

    ImageView img;
    TextView textView;
    ProgressBar pro;

    public MyAsyncTask(ImageView img  , TextView textView) {
        this.img = img;
        this.textView = textView;
    }

    //首先执行，用于做准备工作
    // 该方法执行在主线程当中
    @Override
    protected void onPreExecute() {
        Log.e("onPreExecute","onPreExecute");
        Log.e("onPreExecute当前线程：",Thread.currentThread().getName().toString());
    }

    //最后执行，在主线程中执行
    @Override
    protected void onPostExecute(String Void) {
        Log.e("onPostExecute", Void);
        Log.e("onPostExecute当前线程：",Thread.currentThread().getName().toString());
    }

    //更新操作
    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.e("onProgressUpdate：",values+"");
        Log.e("onProgressUpdate当前线程：",Thread.currentThread().getName().toString());

    }

    @Override
    protected String doInBackground(String... params) {

        publishProgress(3);

        Log.e("doInBackground当前线程：",Thread.currentThread().getName().toString());
        return params[0];
    }




}
