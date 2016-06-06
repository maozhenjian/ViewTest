package com.example.beyong.viewtest.ViewModel;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.beyong.viewtest.AnimTest.Activity_Transition;
import com.example.beyong.viewtest.AnimTest.Circular_RevealActivity;
import com.example.beyong.viewtest.AnimTest.RevealColorViewAct;
import com.example.beyong.viewtest.DialogAct.DialogActivity;
import com.example.beyong.viewtest.R;
import com.example.beyong.viewtest.model.Name;

import java.util.ArrayList;
import java.util.List;

public class Material_Design extends Activity {
    private ListView lv;
    private List<Name> list;
    private ModelList.MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        lv= (ListView) findViewById(R.id.animLv);
        list=new ArrayList<Name>();
        list.add(new Name("Material Design：Circular_Reveal"));
        list.add(new Name("Material Design：Activity Transition"));
        list.add(new Name("Ripple"));
        list.add(new Name("RevealColorViewAct"));
        myAdapter=new ModelList.MyAdapter(list);
        lv.setAdapter(myAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    startActivity(new Intent(Material_Design.this, Circular_RevealActivity.class));
                }
                if (position == 1) {
                    startActivity(new Intent(Material_Design.this, Activity_Transition.class));
                }if (position == 2) {
                startActivity(new Intent(Material_Design.this, ToastActivity.class));
                }if (position == 3) {
                    startActivity(new Intent(Material_Design.this, RevealColorViewAct.class));
                }
            }
        });

    }
}
