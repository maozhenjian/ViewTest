package com.example.beyong.viewtest.rxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.beyong.viewtest.R;

import rx.Observer;

//      http://gank.io/post/560e15be2dca930e00da1083
public class RxAndroidAct extends AppCompatActivity {
    String TAG="RxAndroidAct";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_android);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);
            }

            @Override
            public void onCompleted() {
                Log.e(TAG, "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "Error!");
            }
        };
    }
}
