package com.szc.weather.generator;

import com.szc.weather.bean.WeatherBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by szc on 17-2-25.
 */

public interface WeatherQuery {

    @GET("weather_mini")
    Call<WeatherBean> getDate(@Query("city")String city);
}
