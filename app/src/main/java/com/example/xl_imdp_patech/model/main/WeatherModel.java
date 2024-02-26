package com.example.xl_imdp_patech.model.main;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherModel{
    private String hour;
    private String skyCondition;
    private int humidity;
    private float temp;

    public WeatherModel(String hour, String skyCondition, int humidity, float temp) {
        this.hour = hour;
        this.skyCondition = skyCondition;
        this.humidity = humidity;
        this.temp = temp;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getSkyCondition() {
        return skyCondition;
    }

    public void setSkyCondition(String skyCondition) {
        this.skyCondition = skyCondition;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Float getTemp() {
        return temp;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }

}
