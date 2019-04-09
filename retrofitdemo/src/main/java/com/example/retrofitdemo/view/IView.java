package com.example.retrofitdemo.view;

import com.example.retrofitdemo.bean.Weather;

import java.util.List;

public interface IView {
    //获取数据成功
    void getSuccess();

    //获取失败
    void getFailed();

    //把数据显示到view上
    void setWeather(List<Weather> weathers);

}
