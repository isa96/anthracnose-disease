package com.example.xl_imdp_patech.Utils;

import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

public class XAxisValueFormatter extends ValueFormatter {

    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        String changeFormat = "";
        String example = "23.55";
        //23.5 23
        //1.5
        if(value > 12){
            if(value > 21){
                value -= 12;
                changeFormat = String.valueOf(value);
                Log.d("VV", changeFormat);
                if(changeFormat.length() < example.length()){
                    if(changeFormat.length() == 2){
                        changeFormat = String.valueOf(value) + ".00";
                    }
                    else{
                        changeFormat = String.valueOf(value) + "0";
                    }

                }
                else{
                }
                //23.50
                changeFormat = changeFormat.substring(0,2) + ":" + changeFormat.substring(3, 5) + " PM";
            }
            else{
                //18, 1.55, 11.5, 11.55, 13 - 12 = 1
                value -=12;
                changeFormat = String.valueOf(value);
                if(changeFormat.length() < example.length()){
                    if(changeFormat.length() == 1){
                        changeFormat = "0" + String.valueOf(value) + ".00";
                    }
                    else if(changeFormat.length() == 2){
                        changeFormat = "0" + String.valueOf(value) + "0";
                    }
                    else{
                        changeFormat = "0" + String.valueOf(value);
                    }
                }
                else{
                }
                //23.50
                changeFormat = changeFormat.substring(0,2) + ":" + changeFormat.substring(3) + " PM";
            }
        }
        else{
            //0,1.5,2.55,3
            changeFormat = String.valueOf(value);
            if(changeFormat.length() < example.length()){
                if(changeFormat.length() == 1){
                    //0
                    changeFormat = "0" + String.valueOf(value) + ".00";
                    //00.00
                }
                else if(changeFormat.length() == 2){
                    //1.5
                    changeFormat = "0" + String.valueOf(value) + "0";
                    //01.50
                }
                else{
                    //2.55
                    changeFormat = "0" + String.valueOf(value);
                    //02.55
                }
            }
            else{
            }
            //23.50
            changeFormat = changeFormat.substring(0,2) + ":" + changeFormat.substring(3) + " AM";
        }
        return changeFormat;
    }
}
