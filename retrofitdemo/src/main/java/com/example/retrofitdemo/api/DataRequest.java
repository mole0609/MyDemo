package com.example.retrofitdemo.api;

import com.example.retrofitdemo.bean.AMapLocation;
import com.example.retrofitdemo.bean.HeWeather;
import com.example.retrofitdemo.model.AMapRepository;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface DataRequest {
    //获取天气数据，baseURL在model类里声明
    @GET("now")
    Observable<HeWeather> getHeNowWeather(@Query("location") String location, @Query("key") String key);
    @GET("ip")
    Observable<AMapLocation> getLocationWithIP(@Query("114.247.50.2") String ip, @Query("key") String key);
    @GET("ip")
    Observable<AMapLocation> getLocation(@Query("key") String key);
}
