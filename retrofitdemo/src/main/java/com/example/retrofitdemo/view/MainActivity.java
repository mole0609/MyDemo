package com.example.retrofitdemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitdemo.R;
import com.example.retrofitdemo.bean.AMapLocation;
import com.example.retrofitdemo.bean.HeWeather;
import com.example.retrofitdemo.presenter.AMapPresenter;
import com.example.retrofitdemo.presenter.HePresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IView{

    private EditText mEditText;
    private Button mButtonGetWeather,mButtonGetLocation;
    private TextView mTextView,mEtLocationWeather;
    private HePresenter mHePresenter;
    private AMapPresenter mAMapPresenter;
    private String sCity;
    private String location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        mHePresenter = new HePresenter(this);
        mAMapPresenter = new AMapPresenter(this);
        mEditText = (EditText)findViewById(R.id.et_weather);
        mTextView = (TextView) findViewById(R.id.tv_weather);
        mEtLocationWeather = (TextView) findViewById(R.id.tv_location_weather);
        mButtonGetWeather = (Button)findViewById(R.id.bt_current_weather);
        mButtonGetLocation = (Button)findViewById(R.id.bt_location);
        mButtonGetWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sCity = mEditText.getText().toString();
                if (sCity.isEmpty()){
                    sCity = location;
                    Log.d("NYDBG","city is null");
                }
                mHePresenter.getWeather(sCity);
            }
        });
        mButtonGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAMapPresenter.getLocation();
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
    public void setWeather(List<HeWeather> weathers) {
        /*
        tWindSpd.setText("风速: " + weathers.get(0).getHeWeather6().get(0).getNow().getWindSpd() + "公里/小时");
        tWindSc.setText("风力: " + weathers.get(0).getHeWeather6().get(0).getNow().getWindSc() + "级");
        tWindDir.setText("风向: " + weathers.get(0).getHeWeather6().get(0).getNow().getWindDir());
        tTmp.setText("温度: " + weathers.get(0).getHeWeather6().get(0).getNow().getTmp() + "℃");
        tParentCity.setText("市: " + weathers.get(0).getHeWeather6().get(0).getBasic().getParentCity());
        tLon.setText("经度: " + weathers.get(0).getHeWeather6().get(0).getBasic().getLon());
        tLocation.setText("地区: " + weathers.get(0).getHeWeather6().get(0).getBasic().getLocationWithIP());
        tLat.setText("纬度: " + weathers.get(0).getHeWeather6().get(0).getBasic().getLat());
        tHum.setText("相对湿度: " + weathers.get(0).getHeWeather6().get(0).getNow().getHum());
        tFl.setText("体感温度: " + weathers.get(0).getHeWeather6().get(0).getNow().getFl() + "℃");
        tCondTxt.setText("天气状况: " + weathers.get(0).getHeWeather6().get(0).getNow().getCondTxt());
        tCnty.setText("国家: " + weathers.get(0).getHeWeather6().get(0).getBasic().getCnty());
        tAdminArea.setText("省: " + weathers.get(0).getHeWeather6().get(0).getBasic().getAdminArea());
        * */
        Log.d("NYDBG",sCity+"天气："+weathers.get(0).getHeWeather6().get(0).getNow().getTmp()+"℃");
        Toast.makeText(getApplicationContext(),sCity+"天气："+weathers.get(0).getHeWeather6().get(0).getNow().getTmp()+"℃",Toast.LENGTH_LONG).show();
        mTextView.setText(sCity+"天气："+weathers.get(0).getHeWeather6().get(0).getNow().getTmp()+"℃");
//        mEtLocationWeather.setText(location+"天气："+weathers.get(0).getHeWeather6().get(0).getNow().getTmp()+"℃");
    }

    @Override
    public void setLocation(List<AMapLocation> aMapLocations) {
        Log.d("NYDBG setLocation : ",aMapLocations.get(0).getCity());
        location = aMapLocations.get(0).getCity();
        mButtonGetLocation.setText((CharSequence) aMapLocations.get(0).getCity());
        mHePresenter.getWeather(aMapLocations.get(0).getCity());
    }

}
