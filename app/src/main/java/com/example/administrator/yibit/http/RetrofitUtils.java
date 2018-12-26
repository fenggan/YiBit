package com.example.administrator.yibit.http;

import android.content.Context;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static final int DEFAULT_TIME_OUT = 10;

    private Context context;

    private static String baseUrl;

    public RetrofitUtils(Context context, String baseUrl){
        this.context = context;
        this.baseUrl = baseUrl;
    }

    public  Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
                .build();
    }
    public  OkHttpClient getOkHttpClient(){
        return new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .build();
    }
    public  APIService getAPIService(){
        return getRetrofit().create(APIService.class);
    }
}
