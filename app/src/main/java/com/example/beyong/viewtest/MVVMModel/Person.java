package com.example.beyong.viewtest.MVVMModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.beyong.viewtest.BR;

/**
 * Created by 振坚 on 2016/4/21.
 *
 *添加一个POJO类--person 以及他们的属性的get好set
 *
 */

public class Person extends BaseObservable {
    private  String firstName;
    private  String lastName;
    private String isVisible;

    public String getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(String isVisible) {
        this.isVisible = isVisible;
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }

   @Bindable
    public String getFirstName() {
        return firstName;
    }
    @Bindable
    public String getLastName() {
        return lastName;
    }

}
