package com.hjc.base.http;


import com.hjc.base.http.config.HttpConfig;
import com.hjc.base.http.converter.JsonConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:55
 * @Description: Retrofit封装(添加自定义converter)
 */
public class RetrofitClient1 {
    private static RetrofitClient1 mRetrofitClient;
    private static Api mAPI;

    private RetrofitClient1() {
        OkHttpClient.Builder builder = HttpClient.getInstance().getBuilder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.BASE_URL)
                .addConverterFactory(JsonConverterFactory.create())          //添加Gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())   //添加RxJava转换器
                .client(builder.build())
                .build();

        mAPI = retrofit.create(Api.class);
    }

    public static RetrofitClient1 getInstance() {
        if (mRetrofitClient == null) {
            synchronized (RetrofitClient1.class) {
                if (mRetrofitClient == null) {
                    mRetrofitClient = new RetrofitClient1();
                }
            }
        }
        return mRetrofitClient;
    }

    public Api getAPI() {
        return mAPI;
    }

}
