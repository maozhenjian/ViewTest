package com.example.beyong.viewtest.NormalAnim;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.beyong.viewtest.R;

public class ObjAnimActivity extends Activity implements View.OnClickListener{
   private Button bt1,bt2,bt3,bt4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obj_anim);
        bt1= (Button) findViewById(R.id.animbt1);
        bt2= (Button) findViewById(R.id.animbt2);
        bt3= (Button) findViewById(R.id.animbt3);
        bt4= (Button) findViewById(R.id.animbt4);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.animbt1:
                ObjectAnimator objectAnimator6=ObjectAnimator.ofFloat(bt1, "rotationX", 360);
                ObjectAnimator objectAnimator7=ObjectAnimator.ofFloat(bt1, "rotationY", 180);
                ObjectAnimator objectAnimator8=ObjectAnimator.ofFloat(bt1, "rotation",-90);
                ObjectAnimator objectAnimator1=ObjectAnimator.ofFloat(bt1, "translationX", 90);
                ObjectAnimator objectAnimator2=ObjectAnimator.ofFloat(bt1, "translationY", 90);
                ObjectAnimator objectAnimator3=ObjectAnimator.ofFloat(bt1, "scaleX", 1, 1.5F);
                ObjectAnimator objectAnimator4=ObjectAnimator.ofFloat(bt1, "scaleY", 1, 0.5F);
                ObjectAnimator objectAnimator5=ObjectAnimator.ofFloat(bt1,"alpha", 1, 0.25F,1);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(objectAnimator1,objectAnimator2, objectAnimator3, objectAnimator4, objectAnimator5, objectAnimator6, objectAnimator7, objectAnimator8);
                set.setDuration(5000);
                set.start();
//                objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
//                objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
//                objectAnimator.setStartDelay(200);
//                objectAnimator.start();
                break;
            case R.id.animbt2:
                ValueAnimator colorAnim=ObjectAnimator.ofInt(bt2, "backgroundColor",/* */0xffffffff, 0xff00ddff);
                colorAnim.setDuration(3000);
                colorAnim.setEvaluator(new ArgbEvaluator());
                colorAnim.setRepeatCount(ValueAnimator.INFINITE);
                colorAnim.setRepeatMode(ValueAnimator.REVERSE);
                colorAnim.start();
                break;
            case R.id.animbt3:

                break;
            case R.id.animbt4:

                break;

        }
    }
}
