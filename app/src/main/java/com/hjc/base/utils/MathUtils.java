package com.hjc.base.utils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:33
 * @Description: 数值计算工具类(主要用于对小数的计算)
 */
public class MathUtils {
    /**
     * 小数点后保留的位数
     */
    public static final int SCALE = 1;

    /**
     * 0
     */
    public static final int ZERO = 0;


    /**
     * BigDecimal大小比较
     *
     * @param a
     * @param b
     * @return 返回1， 表示a大于b <br/>
     * 返回0 ，表示a等于b <br/>
     * 返回-1，表示a小于b
     */
    public static int compareTo(BigDecimal a, BigDecimal b) {
        return a.compareTo(b);
    }

    /**
     * double大小比较
     *
     * @param a
     * @param b
     * @return 返回1， 表示a大于b <br/>
     * 返回0 ，表示a等于b <br/>
     * 返回-1，表示a小于b
     */
    public static int compareTo(double a, double b) {
        return compareTo(String.valueOf(a), String.valueOf(b));
    }

    /**
     * float大小比较
     *
     * @param a
     * @param b
     * @return 返回1， 表示a大于b <br/>
     * 返回0 ，表示a等于b <br/>
     * 返回-1，表示a小于b
     */
    public static int compareTo(float a, float b) {
        return compareTo(String.valueOf(a), String.valueOf(b));
    }


    /**
     * 数值大小比较
     *
     * @param a
     * @param b
     * @return 返回1， 表示a大于b <br/>
     * 返回0 ，表示a等于b <br/>
     * 返回-1，表示a小于b
     */
    public static int compareTo(String a, String b) {
        return compareTo(new BigDecimal(a), new BigDecimal(b));
    }

    /**
     * 累加运算
     *
     * @param scale 小数点后保留几位
     * @param vals
     * @return 四舍五入后的结果
     */
    public static BigDecimal add(int scale, BigDecimal... vals) {
        if (vals == null || vals.length <= 0) {
            return null;
        }

        BigDecimal sum = new BigDecimal("0");
        for (BigDecimal val : vals) {
            sum = sum.add(val);
        }

        return sum.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 累加运算
     *
     * @param vals
     * @return
     */
    public static int add(int... vals) {
        if (vals == null || vals.length <= 0) {
            return 0;
        }

        int sum = 0;
        for (int val : vals) {
            sum = sum + val;
        }

        return sum;
    }

    /**
     * 累加运算
     *
     * @param scale 小数点后保留几位
     * @param vals
     * @return 四舍五入后的结果
     */
    public static double add(int scale, double... vals) {
        if (vals == null || vals.length <= 0) {
            return 0;
        }

        BigDecimal sum = new BigDecimal("0");
        for (double val : vals) {
            sum = sum.add(new BigDecimal(val));
        }

        return sum.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 累加运算
     *
     * @param scale 小数点后保留几位
     * @param vals
     * @return 四舍五入后的结果
     */
    public static float add(int scale, float... vals) {
        if (vals == null || vals.length <= 0) {
            return 0;
        }

        BigDecimal sum = new BigDecimal("0");
        for (float val : vals) {
            sum = sum.add(new BigDecimal(val));
        }

        return sum.setScale(scale, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 累加运算
     *
     * @param scale 小数点后保留几位
     * @param list
     * @return 四舍五入后的结果
     */
    public static float add(int scale, List<Float> list) {
        if (list == null || list.size() <= 0) {
            return 0;
        }

        BigDecimal sum = new BigDecimal("0");
        for (float val : list) {
            sum = sum.add(new BigDecimal(val));
        }

        return sum.setScale(scale, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 加法运算
     *
     * @param a     被加数
     * @param b     加数
     * @param scale 小数点后保留几位, 默认保留5位小数点
     * @return 四舍五入后的结果
     */
    public static BigDecimal add(BigDecimal a, BigDecimal b, int scale) {
        if (scale < 0) {
            scale = SCALE;
        }
        return a.add(b).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 加法运算
     *
     * @param a 被加数
     * @param b 加数
     * @return 四舍五入后的结果
     */
    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        return add(a, b, SCALE);
    }

    /**
     * 加法运算
     *
     * @param a     被加数
     * @param b     加数
     * @param scale 小数点后保留几位, 默认保留5位小数点
     * @return 四舍五入后的结果
     */
    public static double add(double a, double b, int scale) {
        if (scale < 0) {
            scale = SCALE;
        }

        return add(new BigDecimal(a), new BigDecimal(b), scale).doubleValue();
    }

    /**
     * 加法运算
     *
     * @param a 被加数
     * @param b 加数
     * @return 四舍五入后的结果
     */
    public static double add(double a, double b) {
        return add(new BigDecimal(a), new BigDecimal(b), SCALE).doubleValue();
    }

    /**
     * 减法运算
     *
     * @param a     被减数
     * @param b     减数
     * @param scale 小数点后保留几位, 默认保留5位小数点
     * @return 四舍五入后的结果
     */
    public static BigDecimal subtract(BigDecimal a, BigDecimal b, int scale) {
        if (scale < 0) {
            scale = SCALE;
        }
        return a.subtract(b).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 减法运算
     *
     * @param a 被减数
     * @param b 减数
     * @return 四舍五入后的结果
     */
    public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return subtract(a, b, SCALE);
    }

    /**
     * 减法运算
     *
     * @param a     被减数
     * @param b     减数
     * @param scale 小数点后保留几位, 默认保留5位小数点
     * @return 四舍五入后的结果
     */
    public static double subtract(double a, double b, int scale) {
        if (scale < 0) {
            scale = SCALE;
        }

        return subtract(new BigDecimal(a), new BigDecimal(b), scale).doubleValue();
    }

    /**
     * 减法运算
     *
     * @param a 被减数
     * @param b 减数
     * @return 四舍五入后的结果
     */
    public static double subtract(double a, double b) {
        return subtract(new BigDecimal(a), new BigDecimal(b), SCALE).doubleValue();
    }

    /**
     * 乘法运算
     *
     * @param a     被乘数
     * @param b     乘数
     * @param scale 小数点后保留几位, 默认保留5位小数点
     * @return 四舍五入后的结果
     */
    public static BigDecimal multiply(BigDecimal a, BigDecimal b, int scale) {
        if (scale < 0) {
            scale = SCALE;
        }
        return a.multiply(b).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 乘法运算
     *
     * @param a 被乘数
     * @param b 乘数
     * @return 四舍五入后的结果
     */
    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return multiply(a, b, SCALE);
    }

    /**
     * 乘法运算
     *
     * @param a     被乘数
     * @param b     乘数
     * @param scale 小数点后保留几位, 默认保留5位小数点
     * @return 四舍五入后的结果
     */
    public static double multiply(double a, double b, int scale) {
        if (scale < 0) {
            scale = SCALE;
        }

        return multiply(new BigDecimal(a), new BigDecimal(b), scale).doubleValue();
    }

    /**
     * 乘法运算
     *
     * @param a 被乘数
     * @param b 乘数
     * @return 四舍五入后的结果
     */
    public static double multiply(double a, double b) {
        return multiply(a, b, SCALE);
    }


    /**
     * 除法运算
     *
     * @param a     被除数
     * @param b     除数
     * @param scale 保留小数点后多少位, 默认保留5位小数点
     * @return 四舍五入后的结果
     */
    public static BigDecimal divide(BigDecimal a, BigDecimal b, int scale) {
        if (scale < 0) {
            scale = SCALE;
        }

        return a.divide(b, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 除法运算，默认保留5位小数点
     *
     * @param a 被除数
     * @param b 除数
     * @return 四舍五入后的结果
     */
    public static BigDecimal divide(BigDecimal a, BigDecimal b) {
        return divide(a, b, SCALE);
    }

    /**
     * 除法运算
     *
     * @param a     被除数
     * @param b     除数
     * @param scale 保留小数点后多少位, 默认保留5位小数点
     * @return 四舍五入后的结果
     */
    public static double divide(double a, double b, int scale) {
        if (scale < 0) {
            scale = SCALE;
        }

        return divide(new BigDecimal(a), new BigDecimal(b), scale).doubleValue();
    }

    /**
     * 除法运算，默认保留5位小数点
     *
     * @param a 被除数
     * @param b 除数
     * @return 四舍五入后的结果
     */
    public static double divide(double a, double b) {
        return divide(a, b, SCALE);
    }
}
