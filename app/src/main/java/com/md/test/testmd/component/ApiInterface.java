package com.md.test.testmd.component;


import com.md.test.testmd.bean.Weather;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/3/18.
 */
public interface ApiInterface {

    String HOST = "https://api.heweather.com/x3/";


    @GET("weather")
    Observable<Weather> mWeatherApi(@Query("city") String city,@Query("key") String key);
}
