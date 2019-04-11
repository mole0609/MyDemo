package com.example.aidlclientdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aidlserverdemo.aidl.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {
    private TextView tv_sum;
    private EditText et_num1, et_num2;
    private Button bt_sum;
    private IMyAidlInterface mStub;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mStub = IMyAidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mStub = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_sum = (TextView) findViewById(R.id.tv_sum);
        et_num1 = (EditText) findViewById(R.id.et_num1);
        et_num2 = (EditText) findViewById(R.id.et_num2);
        bt_sum = (Button) findViewById(R.id.bt_sum);
        bt_sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mStub != null) {
                    try {
                        int sum = mStub.add(Integer.parseInt(et_num1.getText().toString()), Integer.parseInt(et_num2.getText().toString()));
                        tv_sum.setText(String.valueOf(sum));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e("NYDBG", "stub is null");
                }
            }
        });
        Intent intent = new Intent();
        //由于是隐式启动Service 所以要添加对应的action，A和之前服务端的一样。
        intent.setAction("com.example.aidlservice");
        //android 5.0以后直设置action不能启动相应的服务，需要设置packageName或者Component。
        intent.setPackage("com.example.aidlserverdemo"); //packageName 需要和服务端的一致.
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
