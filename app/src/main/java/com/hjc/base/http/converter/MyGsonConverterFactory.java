package com.hjc.base.http.converter;

import com.google.gson.Gson;

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
public class MyGsonConverterFactory extends Converter.Factory {
    private final Gson mGson;

    private MyGsonConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.mGson = gson;
    }

    public static MyGsonConverterFactory create() {
        return new MyGsonConverterFactory(new Gson());
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new MyGsonRequestConverter<>();
    }


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new MyGsonResponseConverter<>(mGson, type);
    }
}
