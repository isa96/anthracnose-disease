package com.example.xl_imdp_patech.model.retrofit;

import com.google.gson.annotations.SerializedName;

public class Forecastday {
    public HourForecast[] getHourForecasts() {
        return hourForecasts;
    }

    public void setHourForecasts(HourForecast[] hourForecasts) {
        this.hourForecasts = hourForecasts;
    }

    @SerializedName("hour")
    public HourForecast [] hourForecasts;
}
