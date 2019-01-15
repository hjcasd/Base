package com.hjc.base.http;


import com.hjc.base.http.config.HttpConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:55
 * @Description: Retrofit封装
 */
public class RetrofitClient {
    private static RetrofitClient mRetrofitClient;
    private static Api mAPI;

    private RetrofitClient() {
        OkHttpClient.Builder builder = HttpClient.getInstance().getBuilder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())          //添加Gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())   //添加RxJava转换器
                .client(builder.build())
                .build();

        mAPI = retrofit.create(Api.class);
    }

    public static RetrofitClient getInstance() {
        if (mRetrofitClient == null) {
            synchronized (RetrofitClient.class) {
                if (mRetrofitClient == null)
                    mRetrofitClient = new RetrofitClient();
            }
        }
        return mRetrofitClient;
    }

    public Api getAPI() {
        return mAPI;
    }

}
