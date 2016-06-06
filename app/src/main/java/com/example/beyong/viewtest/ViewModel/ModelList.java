package com.example.beyong.viewtest.ViewModel;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.beyong.viewtest.DesignModel.DesginListAct;
import com.example.beyong.viewtest.DialogAct.DialogActivity;
import com.example.beyong.viewtest.MyApplication;
import com.example.beyong.viewtest.UIModel.UIListAct;
import com.example.beyong.viewtest.UIModel.UltraPullToRefreshAct;
import com.example.beyong.viewtest.Utils.NetworkUtils;
import com.example.beyong.viewtest.Utils.ToastUtils;
import com.example.beyong.viewtest.View.ViewpagerAct;
import com.example.beyong.viewtest.model.Name;
import com.example.beyong.viewtest.R;
import com.example.beyong.viewtest.rxandroid.RxAndroidAct;
import com.example.beyong.viewtest.viewTouchTest.ViewTouchActivity;

import java.util.ArrayList;
import java.util.List;

public class ModelList extends Activity {
    private SwipeMenuListView lv;
    private List<Name> list;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_list);

        if (NetworkUtils.isNetworkConnected(this)){
            ToastUtils.Utils.toast("网络连接正常");
        }else{
            ToastUtils.Utils.toast("网络异常");
        }

        lv = (SwipeMenuListView) findViewById(R.id.Lv);
        list = new ArrayList<>();
        list.add(new Name("可拖GridView"));
        list.add(new Name("半透明Activity"));
        list.add(new Name("屏幕宽高以及View坐标获取"));
        list.add(new Name("自定义Dialog"));
        list.add(new Name("Bitmap使用"));
        list.add(new Name("Handler的使用"));
        list.add(new Name("Toast快速刷新"));
        list.add(new Name("自定义字体"));
        list.add(new Name("Material Design"));
        list.add(new Name("RecyclerViewActivity"));
        list.add(new Name("动画效果"));
        list.add(new Name("Viewpager轮播图"));
        list.add(new Name("MVVM设计模式简单实用"));
        list.add(new Name("第三方UI框架"));
        list.add(new Name("JAVA设计模式"));
        list.add(new Name("事件传递机制"));
        list.add(new Name("RxAndroid测试"));
        myAdapter = new MyAdapter(list);
        lv.setAdapter(myAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 3) {
                    MyDialog();
                } else if (position == 1) {
                    startActivity(new Intent(ModelList.this, DialogActivity.class));
                } else if (position == 2) {
                    startActivity(new Intent(ModelList.this, LocationActivity.class));
                } else if (position == 0) {
                    startActivity(new Intent(ModelList.this, DragActivity.class));
                } else if (position == 4) {
                    startActivity(new Intent(ModelList.this, BitmapActivity.class));
                } else if (position == 5) {
                    startActivity(new Intent(ModelList.this, HandlerActivity.class));
                } else if (position == 6) {
                    startActivity(new Intent(ModelList.this, ToastActivity.class));
                } else if (position == 7) {
                    startActivity(new Intent(ModelList.this, TypefaceActivity.class));
                } else if (position == 8) {
                    startActivity(new Intent(ModelList.this, Material_Design.class));
                } else if (position == 9) {
                    startActivity(new Intent(ModelList.this, RecyclerViewActivity.class));
                } else if (position == 10) {
                    startActivity(new Intent(ModelList.this, Anim2Activity.class));
                } else if (position == 11) {
                    startActivity(new Intent(ModelList.this, ViewpagerAct.class));
                } else if (position == 12) {
                    startActivity(new Intent(ModelList.this, MVVMAct.class));
                } else if (position == 13) {
                    startActivity(new Intent(ModelList.this, UIListAct.class));
                } else if (position == 14) {
                    startActivity(new Intent(ModelList.this, DesginListAct.class));
                }else if (position == 15 ){
                    startActivity(new Intent(ModelList.this, ViewTouchActivity.class));
                }else if (position == 16 ){
                    startActivity(new Intent(ModelList.this, RxAndroidAct.class));
                }

            }
        });

    }


    private void MyDialog() {
        LayoutInflater inflater = LayoutInflater.from(ModelList.this);
        View myview = inflater.inflate(R.layout.item, null);
        AlertDialog.Builder dialog = new AlertDialog.Builder(ModelList.this);
        dialog.setIcon(R.drawable.t2);
        dialog.setTitle("标题");
        dialog.setView(myview);
        dialog.setCancelable(false);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.setNeutralButton("我的天", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }


   public static class MyAdapter extends BaseAdapter {
        List<Name> lists;

        public MyAdapter(List<Name> list) {
            this.lists = list;
        }

        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder;
            Name data = lists.get(position);
            if (convertView == null) {
                convertView = View.inflate(MyApplication.getApplication(), R.layout.item_model, null);
                holder = new Holder();
                holder.Tv = (TextView) convertView.findViewById(R.id.Tv);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            holder.Tv.setText(data.getName());
            return convertView;
        }

        public class Holder {
            TextView Tv;
        }
    }


}
