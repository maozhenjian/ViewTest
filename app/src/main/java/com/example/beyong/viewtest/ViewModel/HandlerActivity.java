package com.example.beyong.viewtest.ViewModel;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.beyong.viewtest.R;

public class HandlerActivity extends AppCompatActivity {
    private TextView textView;
    private Button btn;
    private android.os.Handler handler=new Handler() {

        public void handleMessage(Message msg){
            switch (msg.what){
                case 0:
                    textView.setText("这是从子线程传递来的数据");
                    break;
            }
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        textView= (TextView) findViewById(R.id.textView11);
        btn= (Button) findViewById(R.id.btn1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 0;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
    }
}
