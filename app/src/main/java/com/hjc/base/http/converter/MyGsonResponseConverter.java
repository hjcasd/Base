package com.hjc.base.http.converter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.hjc.base.http.bean.BaseResponse;
import com.hjc.base.http.exception.ServerCode;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:45
 * @Description: Gson对响应体进行处理
 */
public class MyGsonResponseConverter<T> implements Converter<ResponseBody, T> {
    private Gson mGson;
    private final Type mType;

    public MyGsonResponseConverter(Gson gson, Type type) {
        this.mGson = gson;
        this.mType = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String json = value.string();
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonPrimitive jsonPrimitive = jsonObject.getAsJsonPrimitive("code");
        String code = null;
        if (jsonPrimitive != null) {
            code = jsonPrimitive.getAsString();
        }

        if (ServerCode.CODE_SUCCESS.equals(code)) {
            //返回结果成功
            return mGson.fromJson(json, mType);
        } else {
            //接口请求失败
            return (T) mGson.fromJson(json, BaseResponse.class);
        }
    }
}
