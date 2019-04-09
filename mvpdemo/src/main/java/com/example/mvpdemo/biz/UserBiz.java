package com.example.mvpdemo.biz;

import android.content.Context;
import android.database.Cursor;

import com.example.mvpdemo.bean.User;
import com.example.mvpdemo.db.DBManager;

public class UserBiz implements IUserBiz {
    @Override
    public void login(final Context context, final String username, final String password, final OnLoginListener loginListener) {
        //模拟子线程耗时操作
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                DBManager dbManager = new DBManager(context);
                Cursor c = dbManager.queryUserInfo(username, password);
                if (c.moveToFirst()) {
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    loginListener.loginSuccess(user);
                } else {
                    loginListener.loginFailed();
                }
                c.close();
            }
        }.start();
    }
}
