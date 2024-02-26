package com.example.xl_imdp_patech.fragments;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.xl_imdp_patech.R;
import com.example.xl_imdp_patech.Utils.Converter;
import com.example.xl_imdp_patech.adapter.WeatherAdapter;
import com.example.xl_imdp_patech.model.main.WeatherModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class WeatherFragment extends Fragment{

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    WeatherAdapter weatherAdapter;
    ArrayList<WeatherModel> weatherModel = new ArrayList<>();
    int cDays = 1;
    int x;
    TextView tvCityProvince;
    TextView tvTempDay;
    TextView tvSkyCondition;
    TextView tvCurrentDate;
    TextView tvCurrentTime;
    ImageView imWeatherIcon;
    String API_KEY = "43372368b1f347cba9f115910210109";
    String[] months = {
            "Januari", "Febuari", "Maret", "April", "Mei",
            "Juni", "Juli", "Agustus", "September", "Oktober",
            "November", "Desember"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weather, container, false);

        Bundle bundle = getArguments();
        assert bundle != null;
        String latlong = bundle.getString("latlong");
        String citynames = bundle.getString("city");
        Log.d("LatLong", latlong);

        TextView tvCityProvince = v.findViewById(R.id.cityprovince_text);
        TextView tvTempDay = v.findViewById(R.id.suhu_today);
        TextView tvSkyCondition = v.findViewById(R.id.kondisi_awan);
        TextView tvCurrentDate = v.findViewById(R.id.current_date);
        TextView tvCurrentTime = v.findViewById(R.id.jam_realtime);
        ImageView imWeatherIcon = v.findViewById(R.id.weather_icon);
        ImageView left = v.findViewById(R.id.leftarrow);
        ImageView right = v.findViewById(R.id.rightarrow);

        RecyclerView recyclerView = v.findViewById(R.id.forecast_recyclerview);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        String BASE_URL = "http://api.weatherapi.com/v1/forecast.json?key=" + API_KEY + "&q=" + latlong + "&days=" + cDays + "&aqi=no&alerts=no";
        Log.d("DAYS", String.valueOf(cDays));
        RequestQueue requestQueue = Volley.newRequestQueue(requireActivity().getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, BASE_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                weatherModel.clear();
                try {
                    String location = response.getJSONObject("location").getString("name");
                    String region = response.getJSONObject("location").getString("region");
                    String date = response.getJSONObject("location").getString("localtime");
                    Double temp = response.getJSONObject("current").getDouble("temp_c");
                    String skyCondition = response.getJSONObject("current").getJSONObject("condition").getString("text");
                    Integer code = response.getJSONObject("current").getJSONObject("condition").getInt("code");
                    Integer humidity = response.getJSONObject("current").getInt("humidity");

                    Converter converter = new Converter(code,getContext());
                    converter.codeConverter();
                    imWeatherIcon.setImageDrawable(converter.getImageName());
                    Log.d("TIME", date);
                    String cityregion = location + ", " + citynames + ", " + region + ".";
                    tvCityProvince.setText(cityregion);
                    tvTempDay.setText(temp.toString() + "°");
                    tvSkyCondition.setText(converter.getTextStatus());
                    Converter changeDateFormat = new Converter(date);
                    tvCurrentTime.setText(changeDateFormat.changeDateFormat());

                    JSONObject forecast = response.getJSONObject("forecast");
                    String forecastdaydate = forecast.getJSONArray("forecastday").getJSONObject(cDays-1).getString("date");
                    tvCurrentDate.setText(changeToMonths(forecastdaydate));
                    JSONObject forecastday = forecast.getJSONArray("forecastday").getJSONObject(cDays-1);
                    JSONArray hourForecast = forecastday.getJSONArray("hour");
                    x = hourForecast.length();
                    for (int i = 0; i < hourForecast.length(); i++) {
                        JSONObject hourItem = hourForecast.getJSONObject(i);
                        double temphour = hourItem.getDouble("temp_c");
                        String time = hourItem.getString("time");
                        int codes = hourItem.getJSONObject("condition").getInt("code");
                        Integer hum = hourItem.getInt("humidity");
                        Converter dateFormat = new Converter(time);
                        Converter codeConvert = new Converter(codes);
                        codeConvert.codeConverter();
//                        Log.d("Format", dateFormat.changeToHourFormat().toString());
                        String skyHourCondition = hourItem.getJSONObject("condition").getString("text");
                        WeatherModel model = new WeatherModel(dateFormat.changeDateFormat(), codeConvert.getTextStatus(), hum, (float) temphour);
                        weatherModel.add(model);
                    }
                    recyclerView.setAdapter(new WeatherAdapter(weatherModel));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Cannot Retrieve Data!", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);


        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cDays == 1){
                    return;
                }
                cDays-=1;

                String BASE_URL = "http://api.weatherapi.com/v1/forecast.json?key=" + API_KEY + "&q=" + latlong + "&days=" + cDays + "&aqi=no&alerts=no";
                Log.d("DAYS", String.valueOf(cDays));
                RequestQueue requestQueue = Volley.newRequestQueue(requireActivity().getApplicationContext());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, BASE_URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        weatherModel.clear();
                        try {
                            JSONObject forecast = response.getJSONObject("forecast");
                            String forecastdaydate = forecast.getJSONArray("forecastday").getJSONObject(cDays-1).getString("date");
                            tvCurrentDate.setText(changeToMonths(forecastdaydate));
                            JSONObject forecastday = forecast.getJSONArray("forecastday").getJSONObject(cDays-1);
                            JSONArray hourForecast = forecastday.getJSONArray("hour");
                            x = hourForecast.length();
                            for (int i = 0; i < hourForecast.length(); i++) {
                                JSONObject hourItem = hourForecast.getJSONObject(i);
                                double temphour = hourItem.getDouble("temp_c");
                                String time = hourItem.getString("time");
                                int codes = hourItem.getJSONObject("condition").getInt("code");
                                Integer hum = hourItem.getInt("humidity");
                                Converter dateFormat = new Converter(time);
                                Converter codeConvert = new Converter(codes);
                                codeConvert.codeConverter();
                                Log.d("Format", dateFormat.changeToHourFormat().toString());
                                String skyHourCondition = hourItem.getJSONObject("condition").getString("text");
                                WeatherModel model = new WeatherModel(dateFormat.changeDateFormat(), codeConvert.getTextStatus(), hum, (float) temphour);
                                weatherModel.add(model);
                            }
                            recyclerView.setAdapter(new WeatherAdapter(weatherModel));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Cannot Retrieve Data!", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(request);
            }
        });


        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cDays == 3){
                    return;
                }
                cDays+=1;

                String API_KEY = "43372368b1f347cba9f115910210109";
                String BASE_URL = "http://api.weatherapi.com/v1/forecast.json?key=" + API_KEY + "&q=" + latlong + "&days=" + cDays + "&aqi=no&alerts=no";
                Log.d("DAYS", String.valueOf(cDays));
                RequestQueue requestQueue = Volley.newRequestQueue(requireActivity().getApplicationContext());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, BASE_URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        weatherModel.clear();
                        try {
                            JSONObject forecast = response.getJSONObject("forecast");
                            String forecastdaydate = forecast.getJSONArray("forecastday").getJSONObject(cDays-1).getString("date");
                            tvCurrentDate.setText(changeToMonths(forecastdaydate));
                            JSONObject forecastday = forecast.getJSONArray("forecastday").getJSONObject(cDays-1);
                            JSONArray hourForecast = forecastday.getJSONArray("hour");
                            x = hourForecast.length();
                            for (int i = 0; i < hourForecast.length(); i++) {
                                JSONObject hourItem = hourForecast.getJSONObject(i);
                                double temphour = hourItem.getDouble("temp_c");
                                String time = hourItem.getString("time");
                                int codes = hourItem.getJSONObject("condition").getInt("code");
                                Integer hum = hourItem.getInt("humidity");
                                Converter dateFormat = new Converter(time);
                                Converter codeConvert = new Converter(codes);
                                codeConvert.codeConverter();
                                Log.d("Format", dateFormat.changeToHourFormat().toString());
                                String skyHourCondition = hourItem.getJSONObject("condition").getString("text");
                                WeatherModel model = new WeatherModel(dateFormat.changeDateFormat(), codeConvert.getTextStatus(), hum, (float) temphour);
                                weatherModel.add(model);
                            }
                            recyclerView.setAdapter(new WeatherAdapter(weatherModel));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Cannot Retrieve Data!", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(request);
            }
        });
        return v;
    }

    private String changeToMonths(String dates){
        String changeFormat = dates.trim().substring(5,7);
        int month = Integer.parseInt(changeFormat);
        String result = dates.trim().substring(8, 10) + " " + months[month-1] + " " + dates.trim().substring(0,4);
        return result;
    }
}