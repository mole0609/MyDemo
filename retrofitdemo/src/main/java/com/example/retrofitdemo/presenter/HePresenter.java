package com.example.retrofitdemo.presenter;

import com.example.retrofitdemo.bean.HeWeather;
import com.example.retrofitdemo.model.HeRepository;
import com.example.retrofitdemo.view.IView;

import java.util.List;

public class HePresenter implements IHePresenter {
    private HeRepository heRepository;
    private IView mainView;

    public HePresenter(IView mainView) {
        heRepository = new HeRepository(this);
        this.mainView = mainView;
    }

    @Override
    public void getWeather(String location) {
        heRepository.getWeather(location);
    }

    @Override
    public void setWeather(List<HeWeather> weathers) {
        mainView.setWeather(weathers);
    }
}
