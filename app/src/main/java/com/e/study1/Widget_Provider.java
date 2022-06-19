package com.e.study1;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Widget_Provider extends AppWidgetProvider {
    private static final String MY_ACTION = "android.action.MY_ACTION";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);

            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            views.setOnClickPendingIntent(R.id.button, pendingIntent);

            Intent intent2 = new Intent(context,Widget_Provider.class);
            intent2.setAction(MY_ACTION);
            PendingIntent pendingIntent2 = PendingIntent.getBroadcast(context,0,intent2,0);
            views.setOnClickPendingIntent(R.id.button2,pendingIntent2);

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            String time = preferences.getString("time","0000-00-00 00:00:00");
            views.setTextViewText(R.id.time_tv,time);

            appWidgetManager.updateAppWidget(appWidgetId,views);
        }

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
        ComponentName thisAppWidget = new ComponentName(context.getPackageName(),Widget_Provider.class.getName());

        final String action = intent.getAction();
        System.out.println(action);
        if (action.equals(MY_ACTION)) {
            String time = getTime();
            Response.Listener<String> listener = response -> {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if(success){
                        Toast.makeText(context,"시간 저장 완료",Toast.LENGTH_SHORT).show();

                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            };
            TimeRequest request = new TimeRequest(time,"none",listener);
            RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(request);
            views.setTextViewText(R.id.time_tv,time);
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("time",time);
            editor.commit();
            appWidgetManager.updateAppWidget(thisAppWidget,views);
        }
    }

    public String getTime(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = mFormat.format(date);
        return time;
    }
}
