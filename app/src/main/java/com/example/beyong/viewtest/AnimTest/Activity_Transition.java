package com.example.beyong.viewtest.AnimTest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.beyong.viewtest.MyApplication;
import com.example.beyong.viewtest.R;
import com.example.beyong.viewtest.ViewModel.ToastActivity;

public class Activity_Transition extends Activity {


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
            普通Transition：
            explode：从场景的中心移入或移出
            slide：从场景的边缘移入或移出
            fade：调整透明度产生渐变效果
        * */
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode().setDuration(1000));
        getWindow().setExitTransition(new Explode().setDuration(1000));

//        Transition ts = new ChangeTransform();
//        ts.setDuration(3000);
//        getWindow().setExitTransition(ts);

//        要想尽快进行transitions过渡，可在Activity中调用
//        Window.setAllowEnterTransitionOverlap();

        setContentView(R.layout.activity_activity__transition);

         final View btn=findViewById(R.id.change);
         final Button btn2 = (Button) findViewById(R.id.change2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_Transition.this, ToastActivity.class),
                        ActivityOptions.makeSceneTransitionAnimation(Activity_Transition.this).toBundle());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Transition.this, Shared_transitionActivityTest.class);
                 View Tv = findViewById(R.id.changeTv);
//                ActivityOptions options = ActivityOptions
//                        .makeSceneTransitionAnimation(Activity_Transition.this, btn, "trans");
                ActivityOptions options2 = ActivityOptions
                        .makeSceneTransitionAnimation(Activity_Transition.this, Pair.create(btn,"trans"),Pair.create(Tv, "trans2"));
                startActivity(intent, options2.toBundle());


//                ActivityOptions s = ActivityOptions.makeSceneTransitionAnimation(this,
//                        Pair.create(view1, "agreedName1"),
//                        Pair.create(view2, "agreedName2"));
            }
        });

    }
}
