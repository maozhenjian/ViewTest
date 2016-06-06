package com.example.beyong.viewtest.ViewModel;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.beyong.viewtest.BaseActivity;
import com.example.beyong.viewtest.MyApplication;
import com.example.beyong.viewtest.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);

        Button btn= (Button) findViewById(R.id.bt);
        mDatas=new ArrayList<>();
        mDatas.add("q");
        mAdapter=new MyAdapter(mDatas);
//设置布局管理器
        //流式布局
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,
//                StaggeredGridLayoutManager.VERTICAL));
        //水平线性布局
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
//设置adapter
        mRecyclerView.setAdapter(mAdapter);
//设置Item增加、移除动画
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//添加分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(
//                getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
//        mRecyclerView.setHasFixedSize(true);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.adds("a",0);
            }
        });
        /**
         * 子项点击事件
        *
         * **/
        mAdapter.setOnItemClickLitener(new MyAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
               toast(position+"");
            }
        });

    }


    /**
     *自定义Aapter
     * @author zhenjian
     * */
    public static class MyAdapter extends RecyclerView.Adapter <MyAdapter.MyViewHolder>{
        private List<String> mDatas;
        //添加数据,添加后出现角标错乱问题//// TODO: 2016/3/9 已解决
        public void adds(String data, int position) {
            mDatas.add(position, data);
            notifyItemInserted(position);
        }

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
            View view  = LayoutInflater.from(MyApplication.getApplication()).inflate(R.layout.item, parent, false);
            MyViewHolder holder =new MyViewHolder(view);
            return holder;
        }

        //将数据绑定到布局上，以及一些逻辑的控制就写这啦)
        @Override
        public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {

            viewHolder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   toast("aaa");
                }
            });
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
          return mDatas == null ? 0 : mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {
            Button tv;
            public MyViewHolder(View view)
            {
                super(view);
                tv = (Button) view.findViewById(R.id.bt);
            }
        }


//    //删除数据
//    public void removeItem(String data) {
//        int position = mDatas.indexOf(data);
//        mDatas.remove(position);
//        notifyItemRemoved(position);
//    }

    }

}
