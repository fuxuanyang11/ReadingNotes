package com.example.readingnotes;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.RotateDrawable;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MyAppWidgetProvider extends AppWidgetProvider {

    public static final String CLICK_ACTION = "com.example.readingnotes";

    public MyAppWidgetProvider() {
        super();
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        super.onReceive(context, intent);

//        if (intent.getAction().equals(CLICK_ACTION)) {
//            Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    Bitmap srcBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.measure_spec);
//                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
//                    for (int i = 0; i < 37; i++) {
//                        float degree = (i * 10) % 360;
//                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
//                        remoteViews.setImageViewBitmap(R.id.image, rotateBitmap(context, srcBitmap, degree));
//                        Intent intentClick = new Intent();
//                        intentClick.setAction(CLICK_ACTION);
//                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, 0);
//                        remoteViews.setOnClickPendingIntent(R.id.image, pendingIntent);
//                        appWidgetManager.updateAppWidget(new ComponentName(context, MyAppWidgetProvider.class), remoteViews);
//                        SystemClock.sleep(30);
//                    }
//                }
//            }).start();
//
//        }
    }

    /**
     * 小部件更新就会调用
     * @param context
     * @param appWidgetManager
     * @param appWidgetIds
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);


        for (int appWidgetId : appWidgetIds) {
            onWidgetUpdate(context, appWidgetManager, appWidgetId);

        }
    }

    private void onWidgetUpdate(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        Intent intentClick = new Intent();
        intentClick.setAction(CLICK_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, 0);
        remoteViews.setOnClickPendingIntent(R.id.image, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    private Bitmap rotateBitmap(Context context, Bitmap srcBitmap, float degree) {
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(degree);
        Bitmap bitmap  = Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(), srcBitmap.getHeight(), matrix, true);
        return bitmap;
    }
}
