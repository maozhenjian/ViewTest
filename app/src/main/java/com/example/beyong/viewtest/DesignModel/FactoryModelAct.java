package com.example.beyong.viewtest.DesignModel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.beyong.viewtest.R;
import com.example.beyong.viewtest.UIModel.UIListAct;
import com.example.beyong.viewtest.UIModel.UltraPullToRefreshAct;
import com.example.beyong.viewtest.Utils.ToastUtils;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;
/**
 * JAVA工厂模式
 *
 * */
public class FactoryModelAct extends AppCompatActivity {
        EditText et;

    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory_model);
        et= (EditText) findViewById(R.id.Et);
        bt= (Button) findViewById(R.id.bt);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                way(et.getText().toString());
            }
        });
    }

/**
 * 二、简单工厂模式---（静态工厂方法模式）
 * <p>
 * 简单工厂模式又称静态工厂方法模式。重命名上就可以看出这个模式一定很简单。它存在的目的很简单：定义一个用于创建对象的接口。
 * <p>
 * 在简单工厂模式中,一个工厂类处于对产品类实例化调用的中心位置上,它决定那一个产品类应当被实例化, 如同一个交通警察站在来往的车辆流中,决定放行那一个方向的车辆向那一个方向流动一样。
 * 先来看看它的组成：
 * <p>
 * 1) 工厂类角色：这是本模式的核心，含有一定的商业逻辑和判断逻辑。在java中它往往由一个具体类实现。
 * <p>
 * 2) 抽象产品角色：它一般是具体产品继承的父类或者实现的接口。在java中由接口或者抽象类来实现。
 * <p>
 * 3) 具体产品角色：工厂类所创建的对象就是此角色的实例。在java中由一个具体类实现。
 */

//抽象产品角色
interface Car {
    void run();

    void stop();
}

    //具体实现类
    static class Benz implements Car {
        public void run() {
            ToastUtils.Utils.toast("Benz开始启动了。。。。。");
        }

        public void stop() {
            System.out.println("Benz停车了。。。。。");
        }
    }

    static class Ford implements Car {
        public void run() {
            ToastUtils.Utils.toast("Ford开始启动了。。。");
        }

        public void stop() {
            System.out.println("Ford停车了。。。。");
        }
    }

    //一般工厂
    static class Factory {
        public static Car getCarInstance(String type) {
            Car c = null;
            if ("Benz".equals(type)) {
                c = new Benz();
            }
            if ("Ford".equals(type)) {
                c = new Ford();
            }
            return c;
        }
    }
    //利用反射的工厂
//    static class Factory2{
//        public static Car getCarInstance(String type){
//            Car c=null;
//            try {
//                c=(Car)Class.forName("org.jzkangta.factorydemo03."+type).newInstance();//利用反射得到汽车类型　
//            } catch (InstantiationException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//            return c;
//        }
//    }




// JAVA
//
//    public static class FactoryDemo02 {
//
//    public static void main(String[] args) {
//        Car c=Factory.getCarInstance("Benz");
//        if(c!=null){
//            c.run();
//            c.stop();
//        }else{
//            ToastUtils.Utils.toast("造不了这种汽车。。。");
//        }
//
//
//    }
//
//}




    //Android
    public void way(String s) {
        Car c = Factory.getCarInstance(s);
        if (c != null) {
            c.run();
            c.stop();
        } else {
            ToastUtils.Utils.toast("造不了这种汽车。。。");
        }
    }



}
