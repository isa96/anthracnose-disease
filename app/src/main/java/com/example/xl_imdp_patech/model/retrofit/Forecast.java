package com.example.xl_imdp_patech.model.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Forecast {
//    public Forecastday[] getForecastdays() {
//        return forecastdays;
//    }
//
//    public void setForecastdays(Forecastday[] forecastdays) {
//        this.forecastdays = forecastdays;
//    }

        public ArrayList<Forecastday> getForecastdays() {
        return forecastdays;
    }

    public void setForecastdays(ArrayList<Forecastday> forecastdays) {
        this.forecastdays = forecastdays;
    }

    @SerializedName("forecastday")
    ArrayList<Forecastday> forecastdays;
//    @SerializedName("forecastday")
//    Forecastday [] forecastdays;
}
