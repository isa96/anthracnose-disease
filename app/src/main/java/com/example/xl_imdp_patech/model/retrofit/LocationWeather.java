package com.example.xl_imdp_patech.model.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationWeather {
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @SerializedName("region")
    @Expose
    public String regionName;

    @SerializedName("country")
    @Expose
    public String countryName;
}
