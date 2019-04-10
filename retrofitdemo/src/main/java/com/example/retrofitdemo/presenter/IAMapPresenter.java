package com.example.retrofitdemo.presenter;

import com.example.retrofitdemo.bean.AMapLocation;

import java.util.List;

public interface IAMapPresenter {
    void getLocation();
    void setLocation(List<AMapLocation> aMapLocations);

}
