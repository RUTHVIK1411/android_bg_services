package com.example.bg_services_example;


import androidx.annotation.NonNull;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;


public class MainActivity extends FlutterActivity {
    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        System.out.println("configure method flutter engine in mainactivity of plugin method");
        FlutterEngineCache.getInstance().put("my_engine_id", flutterEngine);
    }
}
