package com.example.beyong.viewtest.ViewModel;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.beyong.viewtest.MVVMModel.MvvmDemoAct.MovieRecycleViewMvvmAct;
import com.example.beyong.viewtest.MVVMModel.Person;
import com.example.beyong.viewtest.MVVMModel.PersonHandle;
import com.example.beyong.viewtest.R;
import com.example.beyong.viewtest.databinding.ActivityMvvmBinding;
/**
 * http://www.open-open.com/lib/view/open1433580995390.html
 *
 *
 * MVVM，即Model-View-ViewModel，Model提供数据，View负责显示，ViewModel跟Model和View进行双向绑定；
 * 当View有用户输入后，ViewModel通知Model更新数据，同理Model数据更新后，
 ViewModel通知View更新。
 * */
public class MVVMAct extends AppCompatActivity {
   public Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定数据
        person=new Person("mao","zhen");
        PersonHandle personHandle=new PersonHandle(this);
      /**
       * 默认情况下，一个Binding类会基于layout文件的名称而产生，将其转换为Pascal case（译注：首字母大写的命名规范）并且添加“Binding”后缀。
       * 上述的layout文件是activity_mvvm.xml，因此生成的类名是ActivityMvvmBinding。
       * */
        ActivityMvvmBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
        binding.setPerson(person);
        binding.setPersonHandle(personHandle);

        binding.bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MVVMAct.this,MovieRecycleViewMvvmAct.class));
            }
        });
        /***
         * 如果你在ListView或者RecyclerView adapter使用Data Binding时，你可能会使用：
         ListItemBinding binding = ListItemBinding.inflate(layoutInflater, viewGroup,false);
         //or
         ListItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, viewGroup, false);
         * */
    }
}
