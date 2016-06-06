package com.example.beyong.viewtest.ViewModel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.beyong.viewtest.Adapter.BaseRecyclerViewAdapter;
import com.example.beyong.viewtest.NormalAnim.ObjAnimActivity;
import com.example.beyong.viewtest.R;
import com.example.beyong.viewtest.model.Name;

import java.util.ArrayList;
import java.util.List;

public class Anim2Activity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Name> mDatas;
    private BaseRecyclerViewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim2);
        mDatas=new ArrayList<>();
        mDatas.add(new Name("属性动画"));
        mDatas.add(new Name("啥米动画"));
        mRecyclerView = (RecyclerView) findViewById(R.id.ryv);
        mAdapter=new BaseRecyclerViewAdapter(this,mDatas);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,1));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickLitener(new BaseRecyclerViewAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position==0){
                    startActivity(new Intent(Anim2Activity.this, ObjAnimActivity.class));

                }
            }
        });
    }




}
