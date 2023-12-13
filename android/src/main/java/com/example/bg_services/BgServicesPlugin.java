package com.example.bg_services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Map;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import com.example.bg_services_example.*;

/** BgServicesPlugin */
public class BgServicesPlugin implements FlutterPlugin, MethodCallHandler {
  private MethodChannel channel;
  private Context mContext;
  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "bg_services");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else if(call.method.equals("initializeService")){
      Context context = mContext.getApplicationContext();
      SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref",Context.MODE_PRIVATE);
      Object arguments = call.arguments;
      Long l = 555419204045280687L;
      if (arguments instanceof Map) {
        Map<String, Long> arguments1 = (Map<String, Long>) arguments;
        l=arguments1.get("callbackDispatcher");
      } else {
        System.out.print("wrong instanve");
      }
      SharedPreferences.Editor myEdit = sharedPreferences.edit();
      myEdit.putLong("callbackdisptacher", l);
      myEdit.apply();
    } else if(call.method.equals("runServiceMethod")){
      Intent i = new Intent(mContext, MyService.class);
      mContext.startService(i);
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
  }
}
