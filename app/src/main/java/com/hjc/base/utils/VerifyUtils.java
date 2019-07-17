package com.hjc.base.utils;

import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;

/**
 * @Author: HJC
 * @Date: 2019/6/28 11:24
 * @Description: 校验工具类
 */
public class VerifyUtils {

    /**
     * 判断是否为手机号码
     *
     * @param etPhone 输入控件
     * @return boolean
     */
    public static boolean isPhone(TextView etPhone) {
        String phone = etPhone.getText().toString().trim();
        return RegexUtils.isMobileExact(phone);
    }

    /**
     * 判断是否为手机号码
     *
     * @param phone 手机号码
     * @return boolean
     */
    public static boolean isPhone(String phone) {
        return RegexUtils.isMobileExact(phone);
    }
}
