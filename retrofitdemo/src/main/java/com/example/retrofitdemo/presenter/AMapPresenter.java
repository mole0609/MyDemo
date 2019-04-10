package com.example.retrofitdemo.presenter;


import com.example.retrofitdemo.bean.AMapLocation;
import com.example.retrofitdemo.model.AMapRepository;
import com.example.retrofitdemo.view.IView;

import java.util.List;

public class AMapPresenter implements IAMapPresenter {
    private IView mainView;
    private AMapRepository mapRepository;

    public AMapPresenter(IView mainView) {
        mapRepository = new AMapRepository(this);
        this.mainView = mainView;
    }

    @Override
    public void getLocation() {
        mapRepository.getLocation();
    }

    @Override
    public void setLocation(List<AMapLocation> aMapLocations) {
        mainView.setLocation(aMapLocations);
    }
}
