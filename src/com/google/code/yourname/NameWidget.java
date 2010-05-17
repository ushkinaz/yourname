package com.google.code.yourname;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Dmitry Sidorenko
 * @date May 17, 2010
 */
public class NameWidget extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTime(context, appWidgetManager), 1, 1000);
    }

    private class MyTime extends TimerTask {
        RemoteViews remoteViews;
        AppWidgetManager appWidgetManager;
        ComponentName thisWidget;
        DateFormat format = SimpleDateFormat.getTimeInstance(SimpleDateFormat.MEDIUM,
                Locale.getDefault());

        public MyTime(Context context, AppWidgetManager appWidgetManager) {
            this.appWidgetManager = appWidgetManager;
            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
            thisWidget = new ComponentName(context, NameWidget.class);
        }

        @Override
        public void run() {
            remoteViews.setTextViewText(R.id.widget_textview,
                    "Time = " + format.format(new Date()));
            appWidgetManager.updateAppWidget(thisWidget, remoteViews);
        }
    }
}
