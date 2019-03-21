package com.hjc.base.utils;

import com.blankj.utilcode.util.LogUtils;

import java.util.List;


/**
 * @Author: HJC
 * @Date: 2019/3/7 16:33
 * @Description: 格式化电影工具类
 */

public class FormatUtils {
    /**
     * 将json格式化并在logcat中输出
     * @param jsonStr json字符串
     */
    public static void formatJsonAndLog(String jsonStr) {
        int level = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < jsonStr.length(); i++) {
            char c = jsonStr.charAt(i);
            if (level > 0 && '\n' == sb.charAt(sb.length() - 1)) {
                sb.append(getLevelStr(level));
            }
            switch (c) {
                case '{':
                case '[':
                    sb.append(c + "\n");
                    level++;
                    break;
                case ',':
                    sb.append(c + "\n");
                    break;
                case '}':
                case ']':
                    sb.append("\n");
                    level--;
                    sb.append(getLevelStr(level));
                    sb.append(c);
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }

        String formatJson = sb.toString();

        int index = 0; // 当前位置
        int max = 3800;// 需要截取的最大长度,别用4000
        String sub;    // 进行截取操作的string
        while (index < formatJson.length()) {
            if (formatJson.length() < max) { // 如果长度比最大长度小
                max = formatJson.length();   // 最大长度设为length,全部截取完成.
                sub = formatJson.substring(index, max);
            } else {
                sub = formatJson.substring(index, max);
            }
            LogUtils.e(sub);         // 进行输出
            index = max;
            max += 3800;
        }
    }

    private static String getLevelStr(int level) {
        StringBuffer levelStr = new StringBuffer();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }

}
