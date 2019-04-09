package com.example.mvpdemo.biz;

import com.example.mvpdemo.bean.User;

public interface OnRegisterListener {
    void registerSuccess(User user);

    void registerFailed();

}
