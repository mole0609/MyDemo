package com.example.mvpdemo.biz;

import android.content.Context;
import android.database.Cursor;

import com.example.mvpdemo.bean.User;
import com.example.mvpdemo.db.DBManager;

public class RegisterBiz implements IRegisterBiz {
    @Override
    public void register(final Context context, final String username, final String password, final OnRegisterListener registerListener) {
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
                Cursor c = dbManager.queryTheUsername(username);
                if (!c.moveToFirst()) {
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    registerListener.registerSuccess(user);
                } else {
                    registerListener.registerFailed();
                }
                c.close();
            }
        }.start();
    }
}
