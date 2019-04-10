package com.example.retrofitdemo.presenter;

import com.example.retrofitdemo.bean.HeWeather;

import java.util.List;

public interface IHePresenter {
    //获取天气数据
    void getWeather(String location);

    //设置天气到view上
    void setWeather(List<HeWeather> weathers);

}
