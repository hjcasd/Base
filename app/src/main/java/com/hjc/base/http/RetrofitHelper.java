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
public class RetrofitHelper {
    private static RetrofitHelper mRetrofitClient;
    private static Api mAPI;

    private RetrofitHelper() {
        OkHttpClient.Builder builder = HttpClient.getInstance().getBuilder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();

        mAPI = retrofit.create(Api.class);
    }

    public static RetrofitHelper getInstance() {
        if (mRetrofitClient == null) {
            synchronized (RetrofitHelper.class) {
                if (mRetrofitClient == null) {
                    mRetrofitClient = new RetrofitHelper();
                }
            }
        }
        return mRetrofitClient;
    }

    public Api getAPI() {
        return mAPI;
    }

}
