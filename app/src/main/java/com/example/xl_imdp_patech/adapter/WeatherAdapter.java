package com.example.xl_imdp_patech.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xl_imdp_patech.R;
import com.example.xl_imdp_patech.model.main.WeatherModel;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    ArrayList<WeatherModel> modelArrayList;

    public WeatherAdapter(ArrayList<WeatherModel> modelArrayList) {
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_holder, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, int position) {
        holder.maxtemp.setText( modelArrayList.get(position).getTemp().toString());
        holder.mintemp.setText( modelArrayList.get(position).getTemp().toString());
        holder.hour.setText( modelArrayList.get(position).getHour());
        holder.sky.setText( modelArrayList.get(position).getSkyCondition());
        holder.hum.setText( modelArrayList.get(position).getHumidity().toString());
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView maxtemp,hum,hour,sky,mintemp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            maxtemp = itemView.findViewById(R.id.max_temp);
            mintemp = itemView.findViewById(R.id.min_temp);
            hum = itemView.findViewById(R.id.humidity);
            hour = itemView.findViewById(R.id.forecast_hour);
            sky = itemView.findViewById(R.id.forecast_cloud);
        }

        @SuppressLint("SetTextI18n")
        public void setData(String houri, String skyi, Integer humi, Float temp){
            maxtemp.setText(temp.toString());
            mintemp.setText(temp.toString());
            hum.setText(humi.toString());
            hour.setText(houri);
            sky.setText(skyi);
        }
    }
}
