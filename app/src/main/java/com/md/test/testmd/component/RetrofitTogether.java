package com.md.test.testmd.component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Administrator on 2016/3/18.
 */
public class RetrofitTogether {

    private static ApiInterface serviceApi;
    private static Retrofit retrofit;


    public static void init() {

        Executor executor = Executors.newCachedThreadPool();
        Gson gson = new GsonBuilder().create();

        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).baseUrl(ApiInterface.HOST).callbackExecutor(executor).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        serviceApi = retrofit.create(ApiInterface.class);

    }

    public static ApiInterface getApiService(){
        if(serviceApi!=null) return serviceApi;
            init();

        return serviceApi;
    }


}
