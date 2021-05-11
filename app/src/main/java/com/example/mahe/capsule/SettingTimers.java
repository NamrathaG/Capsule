package com.example.mahe.capsule;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import android.util.Log;
import android.view.View;

import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;



import java.util.Calendar;
import java.util.Calendar;

/**
 * Created by mahe on 10/27/2016.
 */
public class SettingTimers {


    Intent[] intents;
    private PendingIntent[] Pintenta;
    AlarmManager[] alarmManager;
    Medicines med;
    String timess;
    String[] timesa;
    Context mContext;


    public SettingTimers(Context mContext) {
        this.mContext = mContext;
    }

    public void medicinehere(Medicines med1) {
        med = med1;
        timess = med1.get_times();
        timesa = timess.split(" ");


        intents = new Intent[timesa.length];
        Pintenta = new PendingIntent[timesa.length];
        alarmManager = new AlarmManager[timesa.length];


        for (int i = 0; i < timesa.length; i++) {

            String[] time = timesa[i].split(":");

            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);

            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, hour);
            c.set(Calendar.MINUTE, minute);

            intents[i] = new Intent(mContext, AlarmReceiver.class);

            intents[i].putExtra("state",med.get_id()+"hello");
            Log.e("insideST",intents[i].getStringExtra("objectis"));
            PendingIntent pi = PendingIntent.getBroadcast(mContext, i, intents[i], 0);
            alarmManager[i] = (AlarmManager) mContext.getSystemService(mContext.ALARM_SERVICE);
            alarmManager[i].setInexactRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);

            Pintenta[i] = pi;


        }


    }
}



