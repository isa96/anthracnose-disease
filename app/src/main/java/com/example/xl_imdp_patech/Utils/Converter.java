package com.example.xl_imdp_patech.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.xl_imdp_patech.R;

public class Converter {

    String dates;
    Integer code;
    Drawable imageName;
    String textStatus;
    Context context;

    public Converter(String dates) {
        this.dates = dates;
    }

    public Converter(Integer code, Context context){
        this.code = code;
        this.context = context;
    }

    public Converter(Integer code){
        this.code = code;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void codeConverter(){
        if(this.context == null){
            if(this.code == 1000){
                this.textStatus = "CERAH";
            }
            else if(this.code == 1003){
                this.textStatus = "CERAH BERAWAN";
            }
            else if(this.code == 1006){
                this.textStatus = "BERAWAN";
            }
            else if(this.code == 1009){
                this.textStatus = "MENDUNG/HUJAN";
            }
            else if(this.code == 1030){
                this.textStatus = "BERKABUT";
            }
            else if(this.code == 1063){
                this.textStatus = "KEMUNGKINAN GERIMIS";
            }
            else if(this.code == 1243){
                this.textStatus = "HUJAN SEDANG/DERAS";
            }
            else if(this.code == 1276){
                this.textStatus = "HUJAN DISERTAI PETIR";
            }
            else if(this.code == 1195){
                this.textStatus = "HUJAN DERAS";
            }
            else if(this.code == 1192){
                this.textStatus = "HUJAN DERAS";
            }
            else if(this.code == 1189){
                this.textStatus = "HUJAN SEDANG";
            }
            else if(this.code == 1186){
                this.textStatus = "HUJAN SEDANG";
            }
            else if(this.code == 1180){
                this.textStatus = "GERIMIS";
            }
            else if(this.code == 1183){
                this.textStatus = "HUJAN RINGAN";
            }
            else if(this.code == 1240){
                this.textStatus = "HUJAN RINGAN";
            }
            else if(this.code == 1273){
                this.textStatus = "GERIMIS DISERTAI PETIR";
            }
            else if(this.code == 1246){
                this.textStatus = "HUJAN SANGAT DERAS";
            }
        }
        else{
            if(this.code == 1000){
                this.imageName = context.getResources().getDrawable(R.drawable.status_cerah);
                this.textStatus = "CERAH";
            }
            else if(this.code == 1003){
                this.imageName = context.getResources().getDrawable(R.drawable.status_cloudy);
                this.textStatus = "CERAH BERAWAN";
            }
            else if(this.code == 1006){
                this.imageName = context.getResources().getDrawable(R.drawable.status_berawan);
                this.textStatus = "BERAWAN";
            }
            else if(this.code == 1009){
                this.imageName = context.getResources().getDrawable(R.drawable.status_berawan);
                this.textStatus = "MENDUNG/HUJAN";
            }
            else if(this.code == 1030){
                this.imageName = context.getResources().getDrawable(R.drawable.status_berkabut);
                this.textStatus = "BERKABUT";
            }
            else if(this.code == 1063){
                this.imageName = context.getResources().getDrawable(R.drawable.status_gerimis);
                this.textStatus = "KEMUNGKINAN GERIMIS";
            }
            else if(this.code == 1243){
                this.imageName = context.getResources().getDrawable(R.drawable.status_hujan);
                this.textStatus = "HUJAN SEDANG/DERAS";
            }
            else if(this.code == 1276){
                this.imageName = context.getResources().getDrawable(R.drawable.status_petir);
                this.textStatus = "HUJAN DISERTAI PETIR";
            }
            else if(this.code == 1195){
                this.imageName = context.getResources().getDrawable(R.drawable.status_hujan);
                this.textStatus = "HUJAN DERAS";
            }
            else if(this.code == 1192){
                this.imageName = context.getResources().getDrawable(R.drawable.status_hujan);
                this.textStatus = "HUJAN DERAS";
            }
            else if(this.code == 1189){
                this.imageName = context.getResources().getDrawable(R.drawable.status_hujan);
                this.textStatus = "HUJAN SEDANG";
            }
            else if(this.code == 1186){
                this.imageName = context.getResources().getDrawable(R.drawable.status_hujan);
                this.textStatus = "HUJAN SEDANG";
            }
            else if(this.code == 1180){
                this.imageName = context.getResources().getDrawable(R.drawable.status_gerimis);
                this.textStatus = "GERIMIS";
            }
            else if(this.code == 1183){
                this.imageName = context.getResources().getDrawable(R.drawable.status_gerimis);
                this.textStatus = "HUJAN RINGAN";
            }
            else if(this.code == 1240){
                this.imageName = context.getResources().getDrawable(R.drawable.status_gerimis);
                this.textStatus = "HUJAN RINGAN";
            }
            else if(this.code == 1273){
                this.imageName = context.getResources().getDrawable(R.drawable.status_gerimis);
                this.textStatus = "GERIMIS DISERTAI PETIR";
            }
            else if(this.code == 1246){
                this.imageName = context.getResources().getDrawable(R.drawable.status_hujan);
                this.textStatus = "HUJAN SANGAT DERAS";
            }
        }
    }

    public String getTextStatus(){
        return textStatus;
    }
    public Drawable getImageName() {
        return imageName;
    }

    public Float changeToHourFormat(){
        //2021-09-10 20:00
        String changeFormat = this.dates.trim().substring(11,16);
        String afterFormat = changeFormat.substring(0,2) + "." + changeFormat.substring(3,5);
        Float result = Float.parseFloat(afterFormat);
        return result;
    }

    public String changeDateFormat(){
        String example = "2021-09-10 20:00";
        String result = "";
        String changeFormat;
        int format;
        //2021-09-10 20:00
        //2021-09-10 0:12
        if(this.dates.length() < example.length()){
            changeFormat = this.dates.trim().substring(11);
            format = Integer.parseInt(changeFormat.substring(0,1));
        }
        else{
            changeFormat = this.dates.trim().substring(11);
            format = Integer.parseInt(changeFormat.substring(0,2));
        }

        if(format > 12){
            if(format > 21){
                format -= 12;
                result = format + changeFormat.substring(2,5) + " PM";
            }
            else{
                format -=12;
                result = "0" + format + changeFormat.substring(2,5) + " PM";
            }
        }
        else{
            result = changeFormat + " AM";
        }
        return result;
    }
}
