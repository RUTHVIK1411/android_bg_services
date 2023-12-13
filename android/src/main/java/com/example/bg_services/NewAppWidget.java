package com.example.bg_services;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.app.PendingIntent;
import android.content.Intent;

import com.example.bg_services_example.MyService;

import java.text.DateFormat;
import java.util.Date;
public class NewAppWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        // Intent for the refresh button
        Intent refreshIntent = new Intent(context, NewAppWidget.class);
        refreshIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        refreshIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, new int[]{appWidgetId});
        PendingIntent refreshPendingIntent = PendingIntent.getBroadcast(context, 0, refreshIntent, PendingIntent.FLAG_IMMUTABLE);

        // Intent for the service
        Intent serviceIntent = new Intent(context, MyService.class);
        serviceIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        PendingIntent servicePendingIntent = PendingIntent.getService(context, 0, serviceIntent, PendingIntent.FLAG_IMMUTABLE);

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setOnClickPendingIntent(R.id.refresh_button, refreshPendingIntent);
        views.setOnClickPendingIntent(R.id.refresh_button, servicePendingIntent);


        String currentTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date());
        String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(new Date());
        views.setTextViewText(R.id.text_time, currentTime);
        views.setTextViewText(R.id.text_date, currentDate);


        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(intent.getAction())) {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int[] appWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
            if (appWidgetIds != null) {
                for (int appWidgetId : appWidgetIds) {
                    updateAppWidget(context, appWidgetManager, appWidgetId);
                }
            }
        }
    }
}