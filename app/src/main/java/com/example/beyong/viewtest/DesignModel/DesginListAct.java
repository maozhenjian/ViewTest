package com.example.beyong.viewtest.DesignModel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.beyong.viewtest.R;
import com.example.beyong.viewtest.UIModel.UIListAct;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class DesginListAct extends AppCompatActivity {
    @ViewInject(R.id.uiList)
    private RecyclerView uiRv;


    private List<String> list;
    private UIListAct.MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uilist);

        uiRv= (RecyclerView) findViewById(R.id.uiList);
        list=new ArrayList<>();
        list.add("工厂模式");
        list.add("单例模式");
        list.add("观察者模式");
        myAdapter=new UIListAct.MyAdapter(list);
        uiRv.setLayoutManager(new LinearLayoutManager(this));
        uiRv.setAdapter(myAdapter);


        myAdapter.setOnItemClickLitener(new UIListAct.MyAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position==0){
                    startActivity(new Intent(DesginListAct.this,FactoryModelAct.class));
                }else if (position==1){
                    startActivity(new Intent(DesginListAct.this,SingleAct.class));
                }
            }
        });
    }
}
