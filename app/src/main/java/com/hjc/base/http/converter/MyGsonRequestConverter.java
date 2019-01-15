package com.hjc.base.http.converter;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:43
 * @Description: Gson对请求体进行处理
 */
public class MyGsonRequestConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    MyGsonRequestConverter() {
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        try {
            return RequestBody.create(MEDIA_TYPE, getRequestParams(value));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getRequestParams(T value) throws IllegalAccessException {
        JSONObject jsonObject = new JSONObject();
        Field[] fields = value.getClass().getDeclaredFields();
        Field[] parentFields = value.getClass().getSuperclass().getDeclaredFields();

        Field[] concatFields = concat(fields, parentFields);
        sortField(concatFields);

        for (Field field : concatFields) {
            field.setAccessible(true);
            String key = field.getName();
            if (TextUtils.equals(key, "serialVersionUID")) {
                continue;
            } else if (key.contains("$")) {
                continue;
            }
            String paramValue = (String) field.get(value);
            try {
                jsonObject.put(key, paramValue);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject.toString();
    }


    /**
     * 把参数按照参数名排序
     *
     * @param fields
     */
    private void sortField(Field[] fields) {
        Arrays.sort(fields, (o1, o2) -> {
            String key1 = o1.getName();
            String key2 = o2.getName();
            return key1.compareTo(key2);
        });
    }

    private <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
