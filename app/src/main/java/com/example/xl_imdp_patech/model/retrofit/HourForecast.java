package com.example.xl_imdp_patech.model.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HourForecast {
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
