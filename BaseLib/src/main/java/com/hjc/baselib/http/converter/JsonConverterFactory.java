package com.hjc.baselib.http.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:43
 * @Description: Gson数据转换
 */
public class JsonConverterFactory extends Converter.Factory {
    private final Gson mGson;

    private JsonConverterFactory(Gson gson) {
        if (gson == null) {
            throw new NullPointerException("gson == null");
        }
        this.mGson = gson;
    }

    public static JsonConverterFactory create() {
        return new JsonConverterFactory(new Gson());
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = mGson.getAdapter(TypeToken.get(type));
        return new JsonRequestBodyConverter<>(mGson, adapter);  //请求
    }


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new JsonResponseBodyConverter<>(mGson, type);  //响应
    }
}
