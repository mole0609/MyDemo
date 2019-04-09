package com.example.mvpdemo.presenter;

import android.content.Context;
import android.os.Handler;

import com.example.mvpdemo.bean.User;
import com.example.mvpdemo.biz.OnRegisterListener;
import com.example.mvpdemo.biz.RegisterBiz;
import com.example.mvpdemo.view.IRegisterView;

public class RegisterPresenter {
    private IRegisterView registerView;
    private RegisterBiz registerBiz;
    private Context mContext;

    private Handler mHandler = new Handler();

    public RegisterPresenter(IRegisterView registerView, Context context) {
        mContext = context;
        this.registerView = registerView;
        this.registerBiz = new RegisterBiz();
    }


    public void register() {
        registerBiz.register(mContext, registerView.getUserName(), registerView.getPassword(), new OnRegisterListener() {
            @Override
            public void registerSuccess(final User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        registerView.register(user);
                        registerView.hideLoading();
                    }
                });

            }

            @Override
            public void registerFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        registerView.showFailedError();
                    }
                });
            }
        });
    }


}
