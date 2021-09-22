package com.hjc.library_net.converter;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:43
 * @Description: Gson对请求体进行处理
 */
public class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    private final Gson mGson;
    private final TypeAdapter<T> mAdapter;

    JsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.mGson = gson;
        this.mAdapter = adapter;
    }

    @Override
    public RequestBody convert(@NonNull T value) throws IOException {
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        JsonWriter jsonWriter = mGson.newJsonWriter(writer);
        mAdapter.write(jsonWriter, value);
        jsonWriter.flush();

        String json = buffer.readString(UTF_8);

        return RequestBody.create(MEDIA_TYPE, generateSignedJson(json));
    }

    /**
     * 生成带sign字段的新json
     *
     * @param json 原始json
     * @return 新json
     */
    private String generateSignedJson(String json) {
        try {
            List<String> list = new ArrayList<>();

            JSONObject jsonObject = new JSONObject(json);

            //遍历key和value
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String paramKey = keys.next();
                Object paramValue = jsonObject.get(paramKey);
                String str = paramKey + "=" + paramValue;
                list.add(str);
            }

            // 以&拼接集合中的字符串
            StringBuilder sb = new StringBuilder();
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    if (i < list.size() - 1) {
                        sb.append(list.get(i)).append("&");
                    } else {
                        sb.append(list.get(i));
                    }
                }
            }
            // 加密字符串
            String sign = md5(sb.toString());
            jsonObject.put("sign", sign);

            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * MD5加密
     *
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    private static String md5(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(str.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
