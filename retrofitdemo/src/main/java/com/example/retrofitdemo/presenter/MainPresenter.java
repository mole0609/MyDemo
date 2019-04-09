package com.example.retrofitdemo.presenter;

import com.example.retrofitdemo.bean.Weather;
import com.example.retrofitdemo.contract.MainContract;
import com.example.retrofitdemo.model.MainRepository;
import com.example.retrofitdemo.view.IView;

import java.util.List;

public class MainPresenter implements IPresenter {
    private MainRepository mainRepository;
    private IView mainView;

    public MainPresenter(IView mainView) {
        mainRepository = new MainRepository(this);
        this.mainView = mainView;
    }
    @Override
    public void getWeather(String location) {
        mainRepository.getWeather(location);
    }

    @Override
    public void setWeather(List<Weather> weathers) {
        mainView.setWeather(weathers);
    }
}
