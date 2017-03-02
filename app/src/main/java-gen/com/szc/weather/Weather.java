package com.szc.weather;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "WEATHER".
 */
@Entity
public class Weather {

    @Id
    private Long id;
    private String city;
    private String date;
    private Integer temperature;
    private String high;
    private String low;
    private String ganmao;
    private String fengxiang;

    @Generated
    public Weather() {
    }

    public Weather(Long id) {
        this.id = id;
    }

    @Generated
    public Weather(Long id, String city, String date, Integer temperature, String high, String low, String ganmao, String fengxiang) {
        this.id = id;
        this.city = city;
        this.date = date;
        this.temperature = temperature;
        this.high = high;
        this.low = low;
        this.ganmao = ganmao;
        this.fengxiang = fengxiang;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public String getFengxiang() {
        return fengxiang;
    }

    public void setFengxiang(String fengxiang) {
        this.fengxiang = fengxiang;
    }

}
