package com.example.xl_imdp_patech.model.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherApiModels {
    @SerializedName("location")
    LocationWeather locationWeather;

    @SerializedName("current")
    Current current;

    @SerializedName("forecast")
    Forecast forecasts;

    public Forecast getForecasts() {
        return forecasts;
    }

    public void setForecasts(Forecast forecasts) {
        this.forecasts = forecasts;
    }

    public LocationWeather getLocation() {
        return locationWeather;
    }

    public void setLocation(LocationWeather location) {
        this.locationWeather = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public class Forecast{
        public ArrayList<Forecastday> getForecastdays() {
            return forecastdays;
        }

        public void setForecastdays(ArrayList<Forecastday> forecastdays) {
            this.forecastdays = forecastdays;
        }

        @SerializedName("forecastday")
        ArrayList<Forecastday> forecastdays;

        public class Forecastday{
            public ArrayList<HourForecast> getHourForecasts() {
                return hourForecasts;
            }

            public void setHourForecasts(ArrayList<HourForecast> hourForecasts) {
                this.hourForecasts = hourForecasts;
            }

            @SerializedName("hour")
            ArrayList<HourForecast> hourForecasts;

            public class HourForecast{
                @SerializedName("time")
                @Expose
                public String time;

                @SerializedName("temp_c")
                @Expose
                public Float tempC;

                @SerializedName("humidity")
                @Expose
                public Integer humHour;

                @SerializedName("condition")
                Condition cond;

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public Float getTempC() {
                    return tempC;
                }

                public void setTempC(Float tempC) {
                    this.tempC = tempC;
                }

                public Integer getHumHour() {
                    return humHour;
                }

                public void setHumHour(Integer humHour) {
                    this.humHour = humHour;
                }

                public Condition getCond() {
                    return cond;
                }

                public void setCond(Condition cond) {
                    this.cond = cond;
                }
            }
        }
    }
}
