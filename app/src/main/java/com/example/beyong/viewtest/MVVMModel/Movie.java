package com.example.beyong.viewtest.MVVMModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import java.io.Serializable;
import java.util.List;


/**
 * Created by Chenyc on 15/7/10.
 */
public class Movie extends BaseObservable {

    private String id;
    public String title;
    private String original_title;
    private String year;
    List<String> list;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }





}
