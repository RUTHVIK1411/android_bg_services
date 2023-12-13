import 'package:flutter_test/flutter_test.dart';
import 'package:bg_services/bg_services.dart';
import 'package:bg_services/bg_services_platform_interface.dart';
import 'package:bg_services/bg_services_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockBgServicesPlatform
    with MockPlatformInterfaceMixin
    implements BgServicesPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final BgServicesPlatform initialPlatform = BgServicesPlatform.instance;

  test('$MethodChannelBgServices is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelBgServices>());
  });

  test('getPlatformVersion', () async {
    BgServices bgServicesPlugin = BgServices();
    MockBgServicesPlatform fakePlatform = MockBgServicesPlatform();
    BgServicesPlatform.instance = fakePlatform;

    expect(await bgServicesPlugin.getPlatformVersion(), '42');
  });
}
