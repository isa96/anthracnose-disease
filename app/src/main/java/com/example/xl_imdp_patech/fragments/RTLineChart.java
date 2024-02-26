package com.example.xl_imdp_patech.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xl_imdp_patech.R;
import com.example.xl_imdp_patech.Utils.XAxisValueFormatter;
import com.example.xl_imdp_patech.model.main.ArduinoDataModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.EntryXComparator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class RTLineChart extends Fragment implements OnChartValueSelectedListener {

    private LineChart lineChart;
    private LineDataSet dataSet, dataSet1;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    int i = 1;
    private final String FIREBASE_URL = "https://patech-xl-imdp-default-rtdb.asia-southeast1.firebasedatabase.app/";
    private ArrayList<Entry> tempData = new ArrayList<>();
    private ArrayList<Entry> humData = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_r_t_line_chart, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance(FIREBASE_URL);
        databaseReference = firebaseDatabase.getReference("data");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tempData = new ArrayList<>();
                humData = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ArduinoDataModel arduinoDataModel = dataSnapshot.getValue(ArduinoDataModel.class);
                    assert arduinoDataModel != null;
                    if(getDate().equals(arduinoDataModel.getDate())){
                        tempData.add(new Entry(arduinoDataModel.getHour(), arduinoDataModel.getTemp()));
                        humData.add(new Entry(arduinoDataModel.getHour(), arduinoDataModel.getHumidity()));
                    }
                }
                dataSet = new LineDataSet(tempData, "Temperature");
                dataSet1 = new LineDataSet(humData, "Humidity");
                if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {
                    dataSet = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
                    dataSet1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
                    dataSet.setValues(tempData);
                    dataSet1.setValues(humData);
                    lineChart.getData().notifyDataChanged();
                    lineChart.notifyDataSetChanged();
                }
                else{
                    dataSet.setFillAlpha(150);
                    dataSet.setLineWidth(1.5f);
                    dataSet.setColor(Color.RED);

                    dataSet1.setFillAlpha(150);
                    dataSet1.setLineWidth(1.5f);
                    dataSet1.setColor(Color.BLUE);

                    ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

                    lineDataSets.add(dataSet);
                    lineDataSets.add(dataSet1);

                    LineData data = new LineData(lineDataSets);
                    lineChart.setData(data);
                    lineChart.invalidate();
                }
            }
            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Log.d("TAG", error.getMessage());
            }
        });

        lineChart = v.findViewById(R.id.line_chart);

        lineChart.setTouchEnabled(true);
        lineChart.setPinchZoom(true);

        lineChart.getAxisRight().setEnabled(false);

        XAxis xAxis = lineChart.getXAxis();
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setAxisMaximum(100f);
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setAxisMinimum(10f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);

//        LimitLine limitLine = new LimitLine(20f, "Suhu Terlalu Rendah");
//        limitLine.setLineColor(Color.BLUE);
//        limitLine.setLineWidth(1f);
//        limitLine.setTextColor(Color.BLACK);
//        limitLine.setTextSize(5f);
        //leftAxis.addLimitLine(limitLine);

        XAxis.XAxisPosition position = XAxis.XAxisPosition.BOTTOM;
        xAxis.setPosition(position);
        xAxis.setAxisMaximum(23);
        xAxis.setAxisMinimum(0);
        xAxis.setLabelCount(12);
        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(0f);
        return v;
    }

    String getDate(){
        String date = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
        if(date.charAt(0) == '0'){
            return date.substring(1,2);
        }
        else{
            return date;
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}