package com.example.xl_imdp_patech.api;

import com.example.xl_imdp_patech.model.retrofit.WeatherApiModels;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceCurrent {
    @GET("current.json?")
    Call<WeatherApiModels> getCurrentWeather(
            @Query("key") String apiKey,
            @Query("q") String cityName,
            @Query("days") Integer days,
            @Query("aqi") String aqi,
            @Query("alert") String alert
            );
}
