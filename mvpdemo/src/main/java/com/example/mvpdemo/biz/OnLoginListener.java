package com.example.mvpdemo.biz;

import com.example.mvpdemo.bean.User;

public interface OnLoginListener {

    void loginSuccess(User user);

    void loginFailed();
}
