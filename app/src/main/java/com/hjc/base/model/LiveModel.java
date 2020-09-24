package com.hjc.base.model;

import com.hjc.base.bean.UserBean;
import com.hjc.baselib.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class LiveModel extends BaseModel {

    public UserBean getUser() {
        return new UserBean("张三", 20, true);
    }

    public List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("哈哈哈");
        list.add("呵呵呵");
        return list;
    }
}
