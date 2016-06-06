package com.example.beyong.viewtest.ViewModel;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beyong.viewtest.R;

public class LocationActivity extends AppCompatActivity {
    ImageView imageView;
    TextView widths,heights,statusBars,tops,bottoms,lefts,rights;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        imageView= (ImageView) findViewById(R.id.imageView);

        widths= (TextView) findViewById(R.id.textView5);
        heights= (TextView) findViewById(R.id.textView6);
        statusBars= (TextView) findViewById(R.id.textView7);
        tops= (TextView) findViewById(R.id.textView1);
        bottoms= (TextView) findViewById(R.id.textView2);
        lefts= (TextView) findViewById(R.id.textView3);
        rights= (TextView) findViewById(R.id.textView4);


        //获取屏幕的宽高
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        Log.e("widthPixels", widthPixels + "");
        Log.e("heightPixels",heightPixels+"");

        widths.setText("屏幕的宽: " + widthPixels);

        heights.setText("屏幕的高: "+ heightPixels);

        Log.e("onCreate","onCreate");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e("onStart","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("onResume", "onResume");
    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        Log.e("onWindowFocusChanged", "onWindowFocusChanged");
        //应用程序App区域宽高等尺寸获取
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);

        //获取状态栏高度
        Rect rect2= new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect2);
        int statusBarHeight = rect2.top;
        Log.e("statusBarHeight",statusBarHeight+"");

        statusBars.setText("状态栏高度: " + statusBarHeight);

        //View布局区域宽高等尺寸获取
        Rect rect3 = new Rect();
        getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(rect3);
        int top = rect3.top;
        int bottom = rect3.bottom;
        int right = rect3.right;
        int left = rect3.left;
        Log.e("top", top + "");
        Log.e("bottom", bottom + "");
        Log.e("right", right + "");
        Log.e("left",left+"");


        //获取View自身可见的坐标区域
        Rect rect4= new Rect();
        imageView.getLocalVisibleRect(rect4);
        int top2 = rect4.top;
        int bottom2 = rect4.bottom;
        int right2 = rect4.right;
        int left2 = rect4.left;
        Log.e("top2", top2+ "");
        Log.e("bottom2", bottom2+ "");
        Log.e("right2",right2+"");
        Log.e("left2", left2 + "");

        tops.setText("View自身可见的坐标区域 TOP: " + top2);
        bottoms.setText("View自身可见的坐标区域 bottoms: " + bottom2);
        rights.setText("View自身可见的坐标区域 rights: " + right2);
        lefts.setText("View自身可见的坐标区域 lefts: " + left2);
        //getGlobalVisibleRect(),获取View在屏幕绝对坐标系中的可视区域，坐标以屏幕左上角为原点(0,0)，另一个点为可见区域右下角相对屏幕原点(0,0)点的坐标

    }
}
