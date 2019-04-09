package com.example.mvpdemo.biz;

import android.content.Context;

public interface IUserBiz {
    public void login(Context context, String username, String password, OnLoginListener loginListener);
}
