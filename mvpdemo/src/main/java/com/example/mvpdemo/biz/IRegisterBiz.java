package com.example.mvpdemo.biz;

import android.content.Context;

public interface IRegisterBiz {
    public void register(Context context, String username, String password, OnRegisterListener registerListener);
}
