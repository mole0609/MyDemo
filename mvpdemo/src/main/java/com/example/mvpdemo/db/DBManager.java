package com.example.mvpdemo.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mvpdemo.bean.User;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private DBOpenHelper dbHelper;
    private SQLiteDatabase db;

    public DBManager(Context context) {

    }

    /**
     * add User List
     * */
    public void add(List<User> users){
        db.beginTransaction();//开始事务
        for (User user : users){
            db.execSQL("insert into usertable values(null,?,?)",new Object[]{
                    user.username,user.password
            });
        }
        db.setTransactionSuccessful();//设置事务完成
        db.endTransaction();//结束事务
    }

    /**
     * add UserInfo String.etc
     * */
/*
    public void add(String username,String userpassword){
        db.beginTransaction();
        db.execSQL("insert into usertable value(null,?,?)",new Object[]{
                username,userpassword
        });
    }
*/
    /**
     * update info
     **/
    public void update(){

    }

    /**
     * delete previous info
     * 根据age的范围来删除使用者信息
     * */
    public void delete(User users){
        db.delete("usertable","age >= ?",new String[]{String.valueOf(users.age)});
    }

    /**
     * query all userInfo return list
     * */
    public List<User> query(){
        ArrayList<User> users = new ArrayList<>();
        Cursor c  = queryTheCursor();
        while (c.moveToNext()){
            User user = new User();
            user.username = c.getString(c.getColumnIndex("username"));
            user.password = c.getString(c.getColumnIndex("password"));
            users.add(user);
        }
        c.close();
        return users;
    }

    /**
     * query all userInfo return cursor
     * */
    public Cursor queryTheCursor(){
        Cursor c = db.rawQuery("SELECT * FROM usertable", null);
        return c;
    }

    /**
     * close database
     * */
    public void closeDB(){
        db.close();
    }
}
