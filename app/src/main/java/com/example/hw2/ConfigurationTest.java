package com.example.hw2;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ConfigurationTest  extends AppCompatActivity {
    TextView textView3,textView4,textView5,textView6;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conf);
        Button button = findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 =new Intent(ConfigurationTest.this,MainActivity.class);
                startActivity(intent2);
            }
        });
        textView3=findViewById(R.id.textView3);
        textView4=findViewById(R.id.textView4);
        textView5=findViewById(R.id.textView5);
        textView6=findViewById(R.id.textView6);

        Configuration cfg=getResources().getConfiguration();;
        String screen = cfg.orientation == Configuration.ORIENTATION_LANDSCAPE ?"横向屏幕": "竖向屏幕";
        String mncCode = cfg.mnc + "";
        String naviName = cfg.orientation == Configuration.NAVIGATION_NONAV ? "没有方向控制": cfg.orientation == Configuration.NAVIGATION_WHEEL ? "滚轮控制方向": cfg.orientation == Configuration.NAVIGATION_DPAD ? "方向键控制方向": "轨迹球控制方向";
        textView4.setText(naviName);
        String touchName = cfg.touchscreen == Configuration.TOUCHSCREEN_NOTOUCH ? "无触摸屏": cfg.touchscreen == Configuration.TOUCHSCREEN_STYLUS ? "触摸笔式触摸屏": "接受手指的触摸屏";
        textView3.setText(screen);
        textView6.setText(mncCode);
        textView5.setText(touchName);

    }
}
