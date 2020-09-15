package com.hjc.base.bean;

import androidx.databinding.ObservableField;

public class PersonBean {

    public final ObservableField<String> name = new ObservableField<>();

    public final ObservableField<Boolean> isShow = new ObservableField<>();

    private void setName(String name){
        this.name.set(name);
    }

    private void isShow(boolean isShow){
        this.isShow.set(isShow);
    }
}
