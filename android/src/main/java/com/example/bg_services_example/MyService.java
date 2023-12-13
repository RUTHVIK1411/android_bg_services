package com.example.bg_services_example;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.widget.Toast;

import java.util.ArrayList;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.plugin.common.MethodChannel;

public class MyService extends Service {
    FlutterEngine flutterEngine;
    public MyService() {
    }
    @Override
    public  int onStartCommand(Intent intent, int flags, int startId) {
        System.out.print("Android Service started");
        String bg_channel_name = "background_channel";
        if(flutterEngine==null){
            System.out.print("flutter got from cache");
            flutterEngine = FlutterEngineCache.getInstance().get("my_engine_id");
        }
        assert flutterEngine != null;
        MethodChannel bg_channel = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(),bg_channel_name);
        Context context = getApplicationContext();
        SharedPreferences sh = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        Long n = sh.getLong("callbackdisptacher",213123123123L);
        final ArrayList<Object> l = new ArrayList<>();
        l.add(n);
        bg_channel.invokeMethod("", l);
        Toast.makeText(getApplicationContext(), "Service started", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        System.out.print("onCreate of service");
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        System.out.print("onBind of service");
        throw new UnsupportedOperationException("Not yet implemented");
    }
}