package com.example.beyong.viewtest.ViewModel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.beyong.viewtest.Adapter.DragAdapter;
import com.example.beyong.viewtest.View.DragGridView;
import com.example.beyong.viewtest.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DragActivity extends AppCompatActivity {
    private Button btn;

    private List<String> list=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);


        DragGridView mDragGridView = (DragGridView) findViewById(R.id.dragGridView);
        for (int i = 0; i < 30; i++) {
//            HashMap<String, Object> itemHashMap = new HashMap<String, Object>();
//            itemHashMap.put("item_image",R.drawable.com_tencent_open_notice_msg_icon_big);
//            itemHashMap.put("item_text", "拖拽 " + Integer.toString(i));
//            dataSourceList.add(itemHashMap);
            list.add("aaaaa");
        }

//        mDragGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(DragActivity.this, "Aaaa", Toast.LENGTH_SHORT).show();
//            }
//        });

        mDragGridView.setOnItemClickListener((AdapterView<?> adapterView, View view, int i, long l)->
                Toast.makeText(DragActivity.this, "Aaaa", Toast.LENGTH_SHORT).show());



        final DragAdapter adapter= new DragAdapter(list,this);
        mDragGridView.setAdapter(adapter);

//        final SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, dataSourceList,
//                R.layout.grid_item, new String[] { "item_image", "item_text" }, new int[] { R.id.item_image, R.id.item_text});

        btn= (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.size() > 0) {
                    list.remove(list.size() - 1);
                    adapter.notifyDataSetChanged();
                }
            }
        });



        mDragGridView.setOnChangeListener(new DragGridView.OnChanageListener() {

            @Override
            public void onChange(int from, int to) {
                String temp = list.get(from);
                if (from < to) {
                    for (int i = from; i < to; i++) {
                        Collections.swap(list, i, i + 1);
                    }
                } else if (from > to) {
                    for (int i = from; i > to; i--) {
                        Collections.swap(list, i, i - 1);
                    }
                }
                list.set(to, temp);
                adapter.notifyDataSetChanged();
            }
        });



    }
}

