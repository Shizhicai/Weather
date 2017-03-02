package com.szc.weather.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.szc.weather.R;
import com.szc.weather.bean.WeatherBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by szc on 17-2-25.
 */

public class WeatherAdapter extends BaseAdapter {

    private WeatherBean weatherBean;
    private Context context;
    private static final int TYPE_HEAD = 0;
    private static final int TYPE_COMMON = 1;

    public WeatherAdapter(WeatherBean weatherBean,
                          Context context) {
        this.weatherBean = weatherBean;
        this.context = context;
    }

    public void setWeatherBean(WeatherBean weatherBean) {
        this.weatherBean = weatherBean;
    }

    @Override
    public int getCount() {
        return weatherBean.getData().getForecast().size() + 2;
    }

    @Override
    public Object getItem(int position) {
        if (position == 0)
            return weatherBean.getData().getYesterday();
        return weatherBean.getData().getForecast().get(position - 1);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        ViewHolder_Head viewHolder_head = null;
        View view;
        int type = getItemViewType(position);
        if (convertView == null) {
            if(type == TYPE_HEAD){
                viewHolder_head = new ViewHolder_Head();
                viewHolder_head.mRoot.setTag(viewHolder_head);
            }else{
                viewHolder = new ViewHolder();
                viewHolder.mRoot.setTag(viewHolder);
            }
        } else {
            if(type == TYPE_HEAD){
                viewHolder_head = (ViewHolder_Head) convertView.getTag();
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
        }


        if(viewHolder == null){
            System.out.println("为空");
        }
        view = (viewHolder==null?viewHolder_head.mRoot:viewHolder.mRoot);

        if (type == TYPE_HEAD) {
            viewHolder_head.city.setText(weatherBean.getData().getCity());
            viewHolder_head.temperature.setText(weatherBean.getData().getWendu()+"℃");
            viewHolder_head.ganmao.setText(weatherBean.getData().getGanmao());
        } else {
            if(position == 1){
                viewHolder.time.setText(weatherBean.getData().getYesterday().getDate());
                viewHolder.type.setText(weatherBean.getData().getYesterday().getType());
                viewHolder.temperature.setText(weatherBean.getData().getYesterday().getHigh() + " ~ " + weatherBean.getData().getYesterday().getLow());
                viewHolder.wind1.setText(weatherBean.getData().getYesterday().getFx());
                viewHolder.wind2.setText(weatherBean.getData().getYesterday().getFl());
            }else{
                viewHolder.time.setText(weatherBean.getData().getForecast().get(position - 2).getDate());
                viewHolder.type.setText(weatherBean.getData().getForecast().get(position - 2).getType());
                viewHolder.temperature.setText(weatherBean.getData().getForecast().get(position - 2).getHigh() + " ~ " + weatherBean.getData().getForecast().get(position - 2).getLow());
                viewHolder.wind1.setText(weatherBean.getData().getForecast().get(position - 2).getFengxiang());
                viewHolder.wind2.setText(weatherBean.getData().getForecast().get(position - 2).getFengli());
            }
        }
        return view;
    }

    class ViewHolder {

        public View mRoot;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.temperature)
        TextView temperature;
        @BindView(R.id.wind1)
        TextView wind1;
        @BindView(R.id.wind2)
        TextView wind2;
        @BindView(R.id.type)
        TextView type;

        public ViewHolder() {
            mRoot = View.inflate(context, R.layout.item_weather, null);
            ButterKnife.bind(this, mRoot);
        }
    }

    class ViewHolder_Head {

        public View mRoot;
        @BindView(R.id.city)
        TextView city;
        @BindView(R.id.temperature)
        TextView temperature;
        @BindView(R.id.ganmao)
        TextView ganmao;

        public ViewHolder_Head() {
            mRoot = View.inflate(context, R.layout.item_weather_head, null);
            ButterKnife.bind(this, mRoot);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0)
            return TYPE_HEAD;
        return TYPE_COMMON;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}
