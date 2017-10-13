package com.example.readingnotes;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setNotification();
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setNotification() {
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        PendingIntent contentIntent = PendingIntent.getActivity(
//                this, 0, new Intent(this, DemoActivity.class),
//                PendingIntent.FLAG_UPDATE_CURRENT);

//        Notification notification = new Notification.Builder(this)
//                .setSmallIcon( R.mipmap.ic_launcher)
//                .setContentTitle("My notification")
//                .setContentText("Hello World!")
//                .setContentIntent(contentIntent)
//                .build();// getNotification()

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.activity_noti);
        remoteViews.setTextViewText(R.id.demo, "hhhhh");
        remoteViews.setImageViewResource(R.id.img, R.mipmap.ic_launcher);

        PendingIntent contentIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, DemoActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.img, contentIntent);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(getApplicationInfo().icon)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setContent(remoteViews)
                .setContentIntent(contentIntent)
                .build();
        mNotifyMgr.notify(2, notification);

    }


}
