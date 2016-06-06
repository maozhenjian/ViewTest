package com.example.beyong.viewtest.UIModel;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.beyong.viewtest.R;

import in.srain.cube.views.ptr.PtrFrameLayout;

public class UltraPullToRefreshAct extends Activity {
    PtrFrameLayout mPtrFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultra_pull_to_refresh);
        mPtrFrame= (PtrFrameLayout) findViewById(R.id.store_house_ptr_frame);

//        mPtrFrame.setResistance(1.7f);
//        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
//        mPtrFrame.setDurationToClose(200);
//        mPtrFrame.setDurationToCloseHeader(1000);
//// default is false
//        mPtrFrame.setPullToRefresh(false);
//// default is true
//        mPtrFrame.setKeepHeaderWhenRefresh(true);
    }
}
