package com.example.retrofitdemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofitdemo.R;
import com.example.retrofitdemo.bean.Weather;
import com.example.retrofitdemo.contract.MainContract;
import com.example.retrofitdemo.presenter.MainPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IView{

    private EditText mEditText;
    private Button mButton;
    private MainPresenter mPresenter;
    private String sCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        mPresenter = new MainPresenter(this);
        mEditText = (EditText)findViewById(R.id.et_weather);
        mButton = (Button)findViewById(R.id.bt_weather);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sCity = mEditText.getText().toString();
                Log.d("NYDBG","city"+sCity);
                mPresenter.getWeather(sCity);
            }
        });
    }
    @Override
    public void getSuccess() {
        Toast.makeText(getApplicationContext(),"获取数据成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getFailed() {
        Toast.makeText(getApplicationContext(),"获取数据失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setWeather(List<Weather> weathers) {
        /*
        tWindSpd.setText("风速: " + weathers.get(0).getHeWeather6().get(0).getNow().getWindSpd() + "公里/小时");
        tWindSc.setText("风力: " + weathers.get(0).getHeWeather6().get(0).getNow().getWindSc() + "级");
        tWindDir.setText("风向: " + weathers.get(0).getHeWeather6().get(0).getNow().getWindDir());
        tTmp.setText("温度: " + weathers.get(0).getHeWeather6().get(0).getNow().getTmp() + "℃");
        tParentCity.setText("市: " + weathers.get(0).getHeWeather6().get(0).getBasic().getParentCity());
        tLon.setText("经度: " + weathers.get(0).getHeWeather6().get(0).getBasic().getLon());
        tLocation.setText("地区: " + weathers.get(0).getHeWeather6().get(0).getBasic().getLocation());
        tLat.setText("纬度: " + weathers.get(0).getHeWeather6().get(0).getBasic().getLat());
        tHum.setText("相对湿度: " + weathers.get(0).getHeWeather6().get(0).getNow().getHum());
        tFl.setText("体感温度: " + weathers.get(0).getHeWeather6().get(0).getNow().getFl() + "℃");
        tCondTxt.setText("天气状况: " + weathers.get(0).getHeWeather6().get(0).getNow().getCondTxt());
        tCnty.setText("国家: " + weathers.get(0).getHeWeather6().get(0).getBasic().getCnty());
        tAdminArea.setText("省: " + weathers.get(0).getHeWeather6().get(0).getBasic().getAdminArea());
        * */
        Log.d("NYDBG","Weather"+weathers.get(0).getHeWeather6().get(0).getNow().getTmp());

    }
}
