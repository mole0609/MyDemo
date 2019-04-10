package com.example.retrofitdemo.view;

import com.example.retrofitdemo.bean.AMapLocation;
import com.example.retrofitdemo.bean.HeWeather;
import com.example.retrofitdemo.model.AMapRepository;

import java.util.List;

public interface IView {
    //获取数据成功
    void getSuccess();

    //获取失败
    void getFailed();

    //把数据显示到view上
    void setWeather(List<HeWeather> weathers);

    void setLocation(List<AMapLocation> aMapLocations);
}
