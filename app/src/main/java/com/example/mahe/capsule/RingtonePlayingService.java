package com.example.mahe.capsule;

/**
 * Created by mahe on 10/27/2016.
 */

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;

public class RingtonePlayingService extends Service {

    private boolean isRunning;
    private Context context;
    MediaPlayer mMediaPlayer;
    DBHandler dbh;
    private int startId;

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("MyActivity", "In the Richard service");
        return null;
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String state = intent.getExtras().getString("state");

        final NotificationManager mNM = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        Intent intent1 = new Intent(this.getApplicationContext(), Taken.class);

        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_SINGLE_TOP);
                //Intent.FLAG_ACTIVITY_NEW_TASK);
                // Intent.FLAG_ACTIVITY_SINGLE_TOP);
                //Intent.FLAG_ACTIVITY_NEW_TASK);
        //
        Log.e("insideRPS with state",state);
        intent1.putExtra("state",state);

        int id1=Integer.parseInt(state);
        int  randomNum = 1 + (int)(Math.random() * 100);
        Random random = new Random();
        int randomID = random.nextInt(9999 - 1000) + 1000;
        PendingIntent pIntent = PendingIntent.getActivity(this, randomNum, intent1, PendingIntent.FLAG_ONE_SHOT);
        //Bitmap notificationLargeIconBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.logo);


        dbh = new DBHandler(this, null, null, 1);
        String medname=dbh.getname(id1);

        Notification mNotify = new Notification.Builder(this).setContentIntent(pIntent)
                .setContentTitle("Take your medicine " +medname+ "!")
                .setContentText("Click me!")
                .setSmallIcon(R.drawable.logo).build();
                // .setLargeIcon(notificationLargeIconBitmap)

        mNotify.flags = Notification.FLAG_AUTO_CANCEL;
        mNM.notify(randomID, mNotify);
        mMediaPlayer = MediaPlayer.create(this, R.raw.pill_bottle);
        mMediaPlayer.start();
       // mMediaPlayer.stop();
        return START_NOT_STICKY;
    }


    public void onDestroy() {
        Log.e("JSLog", "on destroy called");
        super.onDestroy();

        this.isRunning = false;
    }


}