package com.example.retrofitdemo.model;

import android.util.Log;

import com.example.retrofitdemo.api.DataRequest;
import com.example.retrofitdemo.bean.HeWeather;
import com.example.retrofitdemo.presenter.IHePresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HeRepository {
    private final static String HE_KEY = "b917a84e33b9435990246c429a580f23";

    private final static String TAG = "HeRepository";

    private final static String HE_BASE_URL = "https://free-api.heweather.net/s6/weather/";

    private List<HeWeather> weathers = new ArrayList<>();

    private IHePresenter presenter;

    public HeRepository(IHePresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * 获取天气数据
     * @param location 城市
     * @return Weather类的数据
     */
    public void getWeather(String location){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HE_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DataRequest dataRequest = retrofit.create(DataRequest.class);
        dataRequest.getHeNowWeather(location, HE_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HeWeather>() {
                    @Override
                    public void onCompleted() {
                        presenter.setWeather(weathers);
                        Log.d(TAG, "onCompleted: " + weathers.get(0).getHeWeather6().get(0).getBasic().getLocation());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(HeWeather weather) {
                        weathers.add(weather);
                        Log.d(TAG, "onNext: " + weather.getHeWeather6().get(0).getBasic().getLocation());
                    }
                });
        weathers.clear();
    }
}
