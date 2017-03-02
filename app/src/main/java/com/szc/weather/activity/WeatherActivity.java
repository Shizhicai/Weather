package com.szc.weather.activity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.szc.weather.DaoMaster;
import com.szc.weather.DaoSession;
import com.szc.weather.R;
import com.szc.weather.Weather;
import com.szc.weather.WeatherDao;
import com.szc.weather.generator.WeatherQuery;
import com.szc.weather.adapter.WeatherAdapter;
import com.szc.weather.bean.WeatherBean;
import com.szc.weather.constant.Constant;
import com.szc.weather.constant.MyApplication;

import org.greenrobot.greendao.query.QueryBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by szc on 17-2-25.
 */

public class WeatherActivity extends Activity {
    @BindView(R.id.listview)
    ListView listview;

    private WeatherAdapter adapter;
    private DaoMaster.DevOpenHelper helper;
    private SQLiteDatabase db;
    private WeatherDao weatherDao;
    private WeatherBean body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        find(getIntent().getStringExtra("city"));
    }

    private void find(String city) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_WERTHER)
                //支持Gson解析
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherQuery weatherQuery = retrofit.create(WeatherQuery.class);
        Call<WeatherBean> date = weatherQuery.getDate(city);
        date.enqueue(new Callback<WeatherBean>() {



            @Override
            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                body = response.body();
                if(body.getStatus() == 1002){
                    System.out.println("请求失败");
                    Toast.makeText(MyApplication.getContext(),"请输入有效的内容",Toast.LENGTH_SHORT).show();
                    return;
                }

                MyApplication.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        if(adapter == null){
                            adapter = new WeatherAdapter(body,MyApplication.getContext());
                            listview.setAdapter(adapter);
                        }else{
                            adapter.setWeatherBean(body);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

                System.out.println("请求成功");
            }

            @Override
            public void onFailure(Call<WeatherBean> call, Throwable t) {
                Toast.makeText(MyApplication.getContext(),"请检查网络是否开启",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goback(View view) {
        finish();
    }

    public void onSave(View view) {
        // 第一个参数为 context，第二个参数为数据库表名，第三个参数通常为 null
        helper = new DaoMaster.DevOpenHelper(this,"weather",null);
        db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        // 得到 Dao 对象，数据库的 CRUD 操作都是通过此对象来进行
        weatherDao = daoSession.getWeatherDao();
        QueryBuilder<Weather> qb = weatherDao.queryBuilder();
        Long id = qb.list() == null ? 0 : qb.list().get(qb.list().size()-1).getId();
        if(id > 0 && qb.where(
                WeatherDao.Properties.City.eq(body.getData().getCity()),
                WeatherDao.Properties.Date.eq(body.getData().getForecast().get(0).getDate()))
                .list().size() > 0){
            Toast.makeText(this,"数据已经保存了",Toast.LENGTH_SHORT).show();
            return;
        }
        WeatherBean.DataBean.ForecastBean forecastBean = body.getData().getForecast().get(0);
        Weather weather = new Weather(
                id + 1,body.getData().getCity(),
                forecastBean.getDate(),Integer.parseInt(body.getData().getWendu()),
                forecastBean.getHigh(),forecastBean.getLow(),
                body.getData().getGanmao(),forecastBean.getFengxiang()
                );
        weatherDao.insert(weather);
        System.out.println("添加成功");
        Toast.makeText(this,"数据成功",Toast.LENGTH_SHORT).show();
    }




}
