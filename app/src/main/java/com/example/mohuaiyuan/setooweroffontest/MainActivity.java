package com.example.mohuaiyuan.setooweroffontest;

import android.app.smdt.SmdtManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText powerOff;
    private EditText powerOn;
    private Button powerOffOn;

    private Context context;
    private SmdtManager smdtManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;

        initUI();
        initData();
        initListener();

    }

    private void initData() {

        smdtManager =SmdtManager.create(context);

        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);
        int second=calendar.get(Calendar.SECOND);
        Log.d(TAG, "hour: "+hour);
        Log.d(TAG, "minute: "+minute);
        Log.d(TAG, "second: "+second);

        int offMinute=0;
        if (second>30){
            offMinute=minute+2;
        }else {
            offMinute=minute+1;
        }

        int onMinute=0;
        onMinute=offMinute+2;

        powerOff.setText(hour+":"+offMinute);
        powerOn.setText(hour+":"+onMinute);


    }

    private void initListener() {
        powerOffOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String offTime=powerOff.getText().toString();
                String onTime=powerOn.getText().toString();
                Log.d(TAG, "offTime: "+offTime);
                Log.d(TAG, "onTime: "+onTime);

                smdtManager.smdtSetTimingSwitchMachine(offTime,onTime,"1");

            }
        });
    }

    private void initUI() {
        powerOff=findViewById(R.id.powerOffEditText);
        powerOn=findViewById(R.id.powerOnEditText);
        powerOffOn=findViewById(R.id.powerOnOffBtn);
    }
}
