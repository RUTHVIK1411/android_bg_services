import 'dart:ui';

import 'package:flutter/cupertino.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';
import 'bg_services_platform_interface.dart';

class BgServices {

  @visibleForTesting
  static const methodChannel = MethodChannel('bg_services');

  Future<String?> getPlatformVersion() {
    return BgServicesPlatform.instance.getPlatformVersion();
  }

  static Future<void> initialize() async {
    final CallbackHandle? callback = PluginUtilities.getCallbackHandle(callbackDispatcher);
    var raw = callback!.toRawHandle();
    await methodChannel.invokeMethod('initializeService',{'raw': raw});
  }

  static void test(void Function(String s) callback) async {
    var raw = PluginUtilities.getCallbackHandle(callback)?.toRawHandle();
    await methodChannel.invokeMethod('runServiceMethod', {'raw': raw });
  }
}

void callbackDispatcher() {
  const MethodChannel backgroundChannel = MethodChannel('background_channel');
  WidgetsFlutterBinding.ensureInitialized();
  backgroundChannel.setMethodCallHandler((MethodCall call) async {
    final List<dynamic> args = call.arguments;
    final Function? callbackThis = PluginUtilities.getCallbackFromHandle(
        CallbackHandle.fromRawHandle(args[0]));
    assert(callbackThis != null);
    String s = args[1] as String;
    callbackThis!(s);
  });
}
