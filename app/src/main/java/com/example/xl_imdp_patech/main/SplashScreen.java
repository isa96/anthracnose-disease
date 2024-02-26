package com.example.xl_imdp_patech.main;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextPaint;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.xl_imdp_patech.R;

public class SplashScreen extends AppCompatActivity {
    Home home = new Home();
    TextView textView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        textView = findViewById(R.id.patechxlflimdp);
        textView.setText("XLFL & IMDP");
        TextPaint paint = textView.getPaint();
        float width = paint.measureText("XLFL & IMDP");
        Shader shader = new LinearGradient(0,0, width, textView.getTextSize(), new int[]{
                Color.parseColor("#F97C3C"),
                Color.parseColor("#FDB54E"),
                Color.parseColor("#64B678"),
                Color.parseColor("#478AEA"),
                Color.parseColor("#8446CC"),
        }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(shader);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, Login.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}