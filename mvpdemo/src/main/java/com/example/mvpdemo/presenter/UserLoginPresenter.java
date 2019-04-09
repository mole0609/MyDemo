package com.example.mvpdemo.presenter;

import android.content.Context;
import android.os.Handler;

import com.example.mvpdemo.bean.User;
import com.example.mvpdemo.biz.IUserBiz;
import com.example.mvpdemo.biz.OnLoginListener;
import com.example.mvpdemo.biz.UserBiz;
import com.example.mvpdemo.db.DBManager;
import com.example.mvpdemo.db.DBOpenHelper;
import com.example.mvpdemo.view.IUserLoginView;

public class UserLoginPresenter {
    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private DBOpenHelper dbOpenHelper;
    private DBManager dbManager;
    private Context mContext;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView, Context context) {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
        mContext = context;
    }

    public void login() {
        userLoginView.showLoading();
        userBiz.login(mContext, userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.toMainActivity(user);
                        userLoginView.hideLoading();
                    }
                });

            }

            @Override
            public void loginFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });

            }
        });
    }

    public void clear() {
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }

    public void register() {
        userLoginView.register();
    }
}
