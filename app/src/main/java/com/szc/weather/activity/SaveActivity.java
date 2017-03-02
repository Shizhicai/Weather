package com.szc.weather.activity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.szc.weather.DaoMaster;
import com.szc.weather.DaoSession;
import com.szc.weather.R;
import com.szc.weather.Weather;
import com.szc.weather.WeatherDao;
import com.szc.weather.adapter.SaveAdapter;
import com.szc.weather.constant.MyApplication;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaveActivity extends AppCompatActivity {

    @BindView(R.id.lv)
    ListView lv;
    private SaveAdapter adapter;
    private DaoSession daoSession;
    private final static int TYPE_DELETE = 1;
    private final static int TYPE_INIE_DETE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        ButterKnife.bind(this);

        initDao(TYPE_INIE_DETE,0L);
        initListener();
    }

    //根据参数来进行操作
    private void initDao(int i,Long id) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "weather", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        // 得到 Dao 对象，数据库的 CRUD 操作都是通过此对象来进行
        WeatherDao weatherDao = daoSession.getWeatherDao();
        if(i == TYPE_INIE_DETE){
            QueryBuilder<Weather> qb = weatherDao.queryBuilder();
            List<Weather> list = qb.list();
            initDate(list);
        }else if(i == TYPE_DELETE){
            weatherDao.deleteByKey(id);
        }
    }

    //设置长按事件监听
    private void initListener() {
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(SaveActivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("是否删除当前数据")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                initDao(TYPE_DELETE,adapter.getList().get(position).getId());
                                adapter.getList().remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("取消",new DialogInterface.OnClickListener() {//添加取消按钮按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//响应事件
                                // TODO Auto-generated method stub

                            }
                }).show();//在按键响应事件中显示此对话框
                return false;
            }
        });
    }

    //初始化数据
    private void initDate(List<Weather> list) {
        adapter = new SaveAdapter(list, MyApplication.getContext());
        lv.setAdapter(adapter);
    }


}
