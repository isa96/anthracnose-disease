package com.example.xl_imdp_patech.model.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Current {
    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @SerializedName("condition")
    Condition condition;

    @SerializedName("last_updated")
    @Expose
    public String lastUpdate;

    @SerializedName("temp_c")
    @Expose
    public Float tempCelcius;

    @SerializedName("humidity")
    @Expose
    public Integer humidity;

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Float getTempCelcius() {
        return tempCelcius;
    }

    public void setTempCelcius(Float tempCelcius) {
        this.tempCelcius = tempCelcius;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }
}
