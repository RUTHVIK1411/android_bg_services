import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:bg_services/bg_services_method_channel.dart';

void main() {
  MethodChannelBgServices platform = MethodChannelBgServices();
  const MethodChannel channel = MethodChannel('bg_services');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await platform.getPlatformVersion(), '42');
  });
}
