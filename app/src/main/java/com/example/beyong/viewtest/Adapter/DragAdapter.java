package com.example.beyong.viewtest.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.beyong.viewtest.R;

import java.util.List;

/**
 * Created by 振坚 on 2016/1/11.
 */
public class DragAdapter extends BaseAdapter {
    private List list;
    Context context;


    public DragAdapter(List list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

        final Animation alpha1= AnimationUtils.loadAnimation(context, R.anim.rotate);
        alpha1.setFillAfter(true);
        final Animation alpha2= AnimationUtils.loadAnimation(context, R.anim.rotate2);
        alpha2.setFillAfter(true);
        final Animation alpha3= AnimationUtils.loadAnimation(context, R.anim.translate);
        alpha3.setFillAfter(true);
        ViewHolder viewHolder;
        if (view==null) {
            view = View.inflate(context, R.layout.item, null);
            viewHolder=new ViewHolder();
            viewHolder.imageView= (ImageView) view.findViewById(R.id.imageView);
            view.setTag(viewHolder);
        }   else{
            viewHolder= (ViewHolder) view.getTag();
        }

//        if (i%2==0){
//        viewHolder.imageView.startAnimation(alpha1);}
//        else{
//            viewHolder.imageView.startAnimation(alpha3);
//        }

        return view;
    }


    class ViewHolder{
        ImageView imageView;

    }
}
