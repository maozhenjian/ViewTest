package com.example.beyong.viewtest.UIModel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.beyong.viewtest.MyApplication;
import com.example.beyong.viewtest.R;
import com.example.beyong.viewtest.ViewModel.RecyclerViewActivity;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class UIListAct extends AppCompatActivity {
    private RecyclerView uiRv;
    private List<String> list;
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uilist);
        uiRv= (RecyclerView) findViewById(R.id.uiList);
        list=new ArrayList<>();
        list.add("UltraPullToRefresh");
        list.add("ConvenientBanner");//轮播图

        myAdapter=new MyAdapter(list);
        uiRv.setLayoutManager(new LinearLayoutManager(this));
        uiRv.setAdapter(myAdapter);



        myAdapter.setOnItemClickLitener(new MyAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position==0){
                    startActivity(new Intent(UIListAct.this,UltraPullToRefreshAct.class));
                }
                if (position==1){
                    startActivity(new Intent(UIListAct.this,ConvenientBannerAct.class));
                }
            }
        });

    }


    public static class MyAdapter extends RecyclerView.Adapter <MyAdapter.MyViewHolder>{
        private List<String> mDatas;

         public MyAdapter( List<String> mDatas) {
         this.mDatas = mDatas;
         }

         /**
         * ItemClick的回调接口
         * @author zhenjian
         *
         */

        public interface OnItemClickLitener
        {
            void onItemClick(View view, int position);
        }

        private OnItemClickLitener mOnItemClickLitener;

        public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
        {
            this.mOnItemClickLitener = mOnItemClickLitener;
        }

        /**
         * 创建新View，被LayoutManager所调用
         * */
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view  = LayoutInflater.from(MyApplication.getApplication()).inflate(R.layout.uiitem, parent, false);
            MyViewHolder holder =   new MyViewHolder(view);
            return holder;
        }

        //将数据绑定到布局上，以及一些逻辑的控制就写这啦)
        @Override
        public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {

            viewHolder.tv.setText(mDatas.get(position));
            //如果设置了回调，则设置点击事件
            if (mOnItemClickLitener != null)
            {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        mOnItemClickLitener.onItemClick(viewHolder.itemView, position);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mDatas==null?0:mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {
            TextView tv;
            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.tv);

            }
        }

    }
}
