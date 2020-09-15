package com.hjc.base.bean;

public class UserBean {
    private String name;
    private int age;
    private boolean isShow;

    public UserBean(String name, int age, boolean isShow) {
        this.name = name;
        this.age = age;
        this.isShow = isShow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
