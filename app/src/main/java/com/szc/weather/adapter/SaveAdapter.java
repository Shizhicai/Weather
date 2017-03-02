package com.szc.weather.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.szc.weather.R;
import com.szc.weather.Weather;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by szc on 17-2-26.
 */

public class SaveAdapter extends BaseAdapter {

    private List<Weather> list;
    private Context context;

    public SaveAdapter(List<Weather> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public List<Weather> getList() {
        return list;
    }

    public void setList(List<Weather> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            viewHolder.mRoot.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.city.setText(list.get(position).getCity());
        viewHolder.date.setText(list.get(position).getDate());
        viewHolder.gaowen.setText(list.get(position).getHigh()+" ~ "+ list.get(position).getLow());
        viewHolder.wendu.setText(list.get(position).getTemperature()+"℃");
        viewHolder.ganmao.setText(list.get(position).getGanmao());
        viewHolder.fengxiang.setText(list.get(position).getFengxiang());
        return viewHolder.mRoot;
    }

    class ViewHolder {

        @BindView(R.id.city)
        TextView city;
        @BindView(R.id.wendu)
        TextView wendu;
        @BindView(R.id.gaowen)
        TextView gaowen;
        @BindView(R.id.fengxiang)
        TextView fengxiang;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.ganmao)
        TextView ganmao;
        public View mRoot;

        public ViewHolder() {
            mRoot = View.inflate(context, R.layout.item_save_weather, null);
            ButterKnife.bind(this, mRoot);
        }
    }
}
