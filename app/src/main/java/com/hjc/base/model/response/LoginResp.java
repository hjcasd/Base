package com.hjc.base.model.response;

import android.text.TextUtils;

import java.io.Serializable;

public class LoginResp implements Serializable {

    private String id;
    private String token;
    private VmlSales vml_sales;
    private String phoneNo;
    private String nickName;
    private String icon_url;
    private String name;
    private String identifying;   //00: 客户经理，01: 一般客户
    private String validTime;
    private String nextPageNo;
    private String customerId;
    private String managerId;

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getNextPageNo() {
        return nextPageNo;
    }

    public void setNextPageNo(String nextPageNo) {
        this.nextPageNo = nextPageNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIdentifying() {
        return identifying;
    }

    public void setIdentifying(String identifying) {
        this.identifying = identifying;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public VmlSales getVml_sales() {
        return vml_sales;
    }

    public void setVml_sales(VmlSales vml_sales) {
        this.vml_sales = vml_sales;
    }

    public boolean isSalesman() {
        return vml_sales != null && !TextUtils.isEmpty(vml_sales.name);
    }

    public static class VmlSales implements Serializable {

        private String partner;
        private String sales;
        private String name;
        private String unit;
        private String dept;

        public String getPartner() {
            return partner;
        }

        public void setPartner(String partner) {
            this.partner = partner;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getDept() {
            return dept;
        }

        public void setDept(String dept) {
            this.dept = dept;
        }
    }
}
