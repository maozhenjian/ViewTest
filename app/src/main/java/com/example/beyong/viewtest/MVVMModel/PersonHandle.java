package com.example.beyong.viewtest.MVVMModel;

import android.content.Intent;
import android.view.View;

import com.example.beyong.viewtest.MVVMModel.MvvmDemoAct.MovieRecycleViewMvvmAct;
import com.example.beyong.viewtest.ViewModel.MVVMAct;


/**
 * Created by 振坚 on 2016/4/21.
 */
public class PersonHandle {
    MVVMAct mvvmAct;

    public PersonHandle(MVVMAct mvvmAct) {
        this.mvvmAct = mvvmAct;
    }


    public void onClick(View view) {
        if (mvvmAct != null && mvvmAct.person != null) {
            mvvmAct.person.setFirstName((int)(Math.random()*50)+"");
        }
    }


}
