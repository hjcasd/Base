package com.hjc.library_net.converter;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.hjc.library_net.bean.BaseResponse;
import com.hjc.library_net.exception.ServerCode;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:45
 * @Description: Gson对响应体进行处理
 */
public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson mGson;
    private final Type mType;

    JsonResponseBodyConverter(Gson gson, Type type) {
        this.mGson = gson;
        this.mType = type;
    }

    @Override
    public T convert(@NonNull ResponseBody value) throws IOException {
        String json = value.string();
        LogUtils.e("response json: " + json);

        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonPrimitive jsonPrimitive = jsonObject.getAsJsonPrimitive("code");

        if (jsonPrimitive != null) {
            String code = jsonPrimitive.getAsString();
            if (ServerCode.CODE_SUCCESS.equals(code)) {
                //返回结果正确,可以转换为对应的bean
                return mGson.fromJson(json, mType);
            } else {
                //接口请求失败,则直接转为BaseResponse
                return (T) mGson.fromJson(json, BaseResponse.class);
            }
        }
        return null;
    }
}
