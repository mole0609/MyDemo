package com.example.retrofitdemo.model;

import android.util.Log;

import com.example.retrofitdemo.api.DataRequest;
import com.example.retrofitdemo.bean.AMapLocation;
import com.example.retrofitdemo.presenter.AMapPresenter;
import com.example.retrofitdemo.presenter.IAMapPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AMapRepository {
    public static final String TAG = "NYDBG";

    private IAMapPresenter presenter;

    private final static String LBS_KEY = "1b9ca6b35600a9ae509fe67ca27247b9";
    private final static String LBS_KEY_WEB = "6d0da062d7a1df6f9c99fa5f949bb8a8";
    private final static String IP = "114.247.50.2";
    private final static String LBS_BASE_URL = "https://restapi.amap.com/v3/";

    private List<AMapLocation> locations = new ArrayList<>();

    public AMapRepository(AMapPresenter aMapPresenter) {
        this.presenter = aMapPresenter;
    }


    public void getLocation(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LBS_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DataRequest dataRequest = retrofit.create(DataRequest.class);
//        dataRequest.getLocationWithIP(IP,LBS_KEY_WEB)
        dataRequest.getLocation(LBS_KEY_WEB)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AMapLocation>() {

                    @Override
                    public void onCompleted() {
                        presenter.setLocation(locations);
                        Log.d(TAG, "onCompleted: " + "-------------");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(AMapLocation aMapLocation) {
                        Log.d(TAG, "onNext: " + ""+aMapLocation);
                    }
                });
        locations.clear();
    }
}
