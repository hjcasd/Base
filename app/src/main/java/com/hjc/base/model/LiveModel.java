package com.hjc.base.model;

import com.hjc.base.bean.UserBean;
import com.hjc.baselib.model.BaseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("key0", "111");
        map.put("key1", "222");
        return map;
    }

    public String[] getArray() {
        return new String[]{"字符串1", "字符串2"};
    }
}
