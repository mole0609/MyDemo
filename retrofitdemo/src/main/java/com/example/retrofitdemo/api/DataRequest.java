package com.example.retrofitdemo.api;

import com.example.retrofitdemo.bean.Weather;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface DataRequest {
    //获取天气数据，baseURL在model类里声明
    @GET("now")
    Observable<Weather> getNowWeather(@Query("location") String location, @Query("key") String key);
}
