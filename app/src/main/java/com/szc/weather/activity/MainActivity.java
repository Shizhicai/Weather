package com.szc.weather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.szc.weather.generator.Location;
import com.szc.weather.R;
import com.szc.weather.constant.Constant;
import com.szc.weather.constant.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {
    private MapView mapView;
    private BaiduMap mBaiduMap;
    private LatLng currentPt;
    private TextView tv_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.map_view);
        mBaiduMap = mapView.getMap();
        tv_city = (TextView) findViewById(R.id.tv_city);
        initListener();


    }

    private void initListener() {
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            /**
             * 单击地图
             */
            public void onMapClick(LatLng point) {
                currentPt = point;
                updateMapState();
            }

            /**
             * 单击地图中的POI点
             */
            public boolean onMapPoiClick(MapPoi poi) {
                currentPt = poi.getPosition();
                updateMapState();
                return false;
            }
        });
        mBaiduMap.setOnMapLongClickListener(new BaiduMap.OnMapLongClickListener() {
            /**
             * 长按地图
             */
            public void onMapLongClick(LatLng point) {
                currentPt = point;
                updateMapState();
            }
        });
        mBaiduMap.setOnMapDoubleClickListener(new BaiduMap.OnMapDoubleClickListener() {
            /**
             * 双击地图
             */
            public void onMapDoubleClick(LatLng point) {
                currentPt = point;
                updateMapState();
            }
        });


    }

    private void updateMapState() {
        if (currentPt != null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_CITY)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
            Location location = retrofit.create(Location.class);
            Call<String> query = location.getCity(
                    Constant.APP_KEY,
                    currentPt.latitude + "," + currentPt.longitude,
                    "json",
                    "0",
                    Constant.MCODE);
            query.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    System.out.println(response.body());
                    final String city = cityUitls(response.body());
                    MyApplication.getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            tv_city.setText(city);
                        }
                    });
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });

        }

    }

    private String cityUitls(String body) {
        String str = "";
        try {
            JSONObject json = new JSONObject(body);
            JSONObject result = json.getJSONObject("result");
            JSONObject addressComponent = result.getJSONObject("addressComponent");
            String country = addressComponent.getString("country");
            if("中国".equals(country)){
                String city = addressComponent.getString("city");
                if(!TextUtils.isEmpty(city)){
                    int length = city.length();
                    str = city.substring(0,length - 1);
                    return str;
                }
            }else{
                str = "国外";
            }
            return str;
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void find(View view){
        String city = (String) tv_city.getText();
        if("城市".equals(city)||"国外".equals(city)){
            Toast.makeText(MyApplication.getContext(),"请选择国内城市",Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(MyApplication.getContext(),WeatherActivity.class);
            intent.putExtra("city",city);
            startActivityForResult(intent,1);
        }
    }
    //创建菜单选项
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "保存的数据");
        return true;
    }
    //菜单点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == 1){
            Intent intent = new Intent(MyApplication.getContext(),SaveActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
