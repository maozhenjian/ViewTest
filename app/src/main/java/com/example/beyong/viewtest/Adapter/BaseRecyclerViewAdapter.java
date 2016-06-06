package com.example.beyong.viewtest.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.beyong.viewtest.R;
import com.example.beyong.viewtest.model.Name;
import java.util.List;

/**
 * Created by 振坚 on 2016/3/11.
 */
public class BaseRecyclerViewAdapter extends RecyclerView.Adapter <BaseRecyclerViewAdapter.BaseViewHolder> {
    private List<Name> mDatas;
    private Context context;
    public BaseRecyclerViewAdapter( Context context,List<Name> mDatas) {
        this.mDatas = mDatas;
        this.context=context;

    }


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
    public BaseRecyclerViewAdapter.BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.recy_item, parent, false);
        BaseViewHolder holder = new BaseViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(final BaseRecyclerViewAdapter.BaseViewHolder holder, final int position) {
        Name name=mDatas.get(position);
        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    mOnItemClickLitener.onItemClick(holder.itemView, position);
                }
            });
        }
        holder.tv.setText(name.getName());
    }

    @Override
    public int getItemCount() {
        return mDatas!=null?mDatas.size():0;
    }

    class BaseViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv;
        public BaseViewHolder(View view)
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv);
        }
    }

}
