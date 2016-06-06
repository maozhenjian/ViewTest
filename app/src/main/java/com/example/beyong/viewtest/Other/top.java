package com.example.beyong.viewtest.Other;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.beyong.viewtest.R;

/**
 * Created by 振坚 on 2016/1/22.
 */
public class top extends RelativeLayout {

    private Button leftButton;
    private TextView Title;

    private int leftTextColor;
    private Drawable leftBackground;
    private String leftText;

    private float titleTextSize;
    private int titleTextColor;
    private String title;

    private LayoutParams leftParams,titleParams;

    private topClickListener listener;

    public interface topClickListener{
        public void leftClick();
    }

    public void setOnTopClickListener(topClickListener listener){
        this.listener=listener;
    }

    public top(final Context context, AttributeSet attrs) {
        super(context, attrs,0);
//赋值属性
        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.top);
        leftTextColor=ta.getColor(R.styleable.top_leftTextColor, 0);
        leftBackground=ta.getDrawable(R.styleable.top_leftBackground);
        leftText=ta.getString(R.styleable.top_leftText);

        titleTextSize=ta.getDimension(R.styleable.top_titleSize, 0);

        titleTextColor=ta.getColor(R.styleable.top_titleColor, 0);
        title=ta.getString(R.styleable.top_titles);

        ta.recycle();

        leftButton=new Button(context);
        Title=new TextView(context);

        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(leftBackground);
        leftButton.setText(leftText);

        Title.setText(title);
        Title.setTextSize(titleTextSize);
        Title.setTextColor(titleTextColor);

        setBackgroundColor(0xFFF59563);

        leftParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        addView(leftButton, leftParams);

        titleParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(Title, titleParams);


        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
             listener.leftClick();
            }
        });
   }

    public top(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.top);
//        bac=ta.getColor(R.styleable.top_buttonc,0);
    }

    public top(Context context) {
        super(context,null);
    }


}
