package com.example.mvpdemo.view;

import com.example.mvpdemo.bean.User;

public interface IRegisterView {
    String getUserName();

    String getPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();

    void register(User user);

}
