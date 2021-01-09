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
public class RetrofitClient {
    private static RetrofitClient mRetrofitClient;
    private static Api mAPI;

    private RetrofitClient() {
        OkHttpClient.Builder builder = HttpClient.getInstance().getBuilder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.BASE_URL)
                .addConverterFactory(JsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();

        mAPI = retrofit.create(Api.class);
    }

    public static RetrofitClient getInstance() {
        if (mRetrofitClient == null) {
            synchronized (RetrofitClient.class) {
                if (mRetrofitClient == null) {
                    mRetrofitClient = new RetrofitClient();
                }
            }
        }
        return mRetrofitClient;
    }

    public Api getAPI() {
        return mAPI;
    }

}
