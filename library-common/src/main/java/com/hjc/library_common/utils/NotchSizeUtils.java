package com.hjc.library_common.utils;

import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.LogUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: HJC
 * @Date: 2021/12/1 13:59
 * @Description: 刘海屏工具类
 */
@SuppressWarnings("ALL")
public class NotchSizeUtils {

    /**
     * 刘海屏全屏显示FLAG
     */
    private static final int HW_FLAG_NOTCH_SUPPORT = 0x00010000;

    /**
     * 是否为刘海屏(华为)
     *
     * @param context 上下文
     * @return result
     */
    public static boolean hasNotchInScreenHW(Context context) {
        boolean ret = false;
        try {
            ClassLoader classLoader = context.getClassLoader();
            Class loadClass = classLoader.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = loadClass.getMethod("hasNotchInScreen");
            ret = (boolean) get.invoke(loadClass);
        } catch (ClassNotFoundException e) {
            LogUtils.e("hasNotchInScreen ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            LogUtils.e("hasNotchInScreen NoSuchMethodException");
        } catch (Exception e) {
            LogUtils.e("hasNotchInScreen Exception");
        } finally {
            return ret;
        }
    }

    /**
     * 获取刘海屏大小(华为)
     *
     * @param context 上下文
     * @return result
     */
    public static int[] getNotchSizeHW(Context context) {
        int[] size = new int[]{0, 0};
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("getNotchSize");
            size = (int[]) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            LogUtils.e("getNotchSize ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            LogUtils.e("getNotchSize NoSuchMethodException");
        } catch (Exception e) {
            LogUtils.e("getNotchSize Exception");
        } finally {
            return size;
        }
    }


    /**
     * 设置华为刘海屏手机使用刘海区
     *
     * @param window 应用页面window对象区域
     */
    public static void setFullScreenWindowLayoutInDisplayCutoutHW(Window window) {
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        try {
            Class layoutParamsExCls = Class.forName("com.huawei.android.view.LayoutParamsEx");
            Constructor con = layoutParamsExCls.getConstructor(WindowManager.LayoutParams.class);
            Object layoutParamsExObj = con.newInstance(layoutParams);
            Method method = layoutParamsExCls.getMethod("addHwFlags", int.class);
            method.invoke(layoutParamsExObj, HW_FLAG_NOTCH_SUPPORT);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            LogUtils.e("hw add notch screen flag api error");
        } catch (Exception e) {
            LogUtils.e("other Exception");
        }
    }

    /**
     * 设置华为刘海屏手机不使用刘海区域
     *
     * @param window 应用页面window对象
     */
    public static void setNotFullScreenWindowLayoutInDisplayCutoutHW(Window window) {
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        try {
            Class layoutParamsExCls = Class.forName("com.huawei.android.view.LayoutParamsEx");
            Constructor con = layoutParamsExCls.getConstructor(WindowManager.LayoutParams.class);
            Object layoutParamsExObj = con.newInstance(layoutParams);
            Method method = layoutParamsExCls.getMethod("clearHwFlags", int.class);
            method.invoke(layoutParamsExObj, HW_FLAG_NOTCH_SUPPORT);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            LogUtils.e("hw clear notch screen flag api error");
        } catch (Exception e) {
            LogUtils.e("other Exception");
        }
    }

}
