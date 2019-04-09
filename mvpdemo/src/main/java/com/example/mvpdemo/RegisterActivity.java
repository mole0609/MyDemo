package com.example.mvpdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvpdemo.bean.User;
import com.example.mvpdemo.db.DBManager;
import com.example.mvpdemo.presenter.RegisterPresenter;
import com.example.mvpdemo.view.IRegisterView;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements IRegisterView {

    private EditText mEtUsername, mEtPassword;
    private Button mBtnRegister;
    private ProgressBar mPbLoading;
    private Context mContext;

    private RegisterPresenter mRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = getApplicationContext();
        mRegisterPresenter = new RegisterPresenter(this, mContext);
        initViews();
    }

    private void initViews() {
        mEtUsername = (EditText) findViewById(R.id.id_et_username);
        mEtPassword = (EditText) findViewById(R.id.id_et_password);
        mPbLoading = (ProgressBar) findViewById(R.id.id_pb_loading);
        mBtnRegister = (Button) findViewById(R.id.id_btn_register);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRegisterPresenter.register();
            }
        });
    }

    @Override
    public String getUserName() {
        return mEtUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEtPassword.getText().toString();
    }

    @Override
    public void showLoading() {
        mPbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mPbLoading.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this, user.getUsername() +
                " login success , to MainActivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this,
                "register failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void register(User user) {
        List<User> users = new ArrayList<>();
        user.username = getUserName();
        user.password = getPassword();
        users.add(user);
        DBManager dbManager = new DBManager(mContext);
        dbManager.add(users);
    }
}
