package com.example.retrofitdemo.presenter;

import com.example.retrofitdemo.bean.Weather;

import java.util.List;

public interface IPresenter {
    //获取天气数据
    void getWeather(String location);

    //设置天气到view上
    void setWeather(List<Weather> weathers);

}
