import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'bg_services_method_channel.dart';

abstract class BgServicesPlatform extends PlatformInterface {
  /// Constructs a BgServicesPlatform.
  BgServicesPlatform() : super(token: _token);

  static final Object _token = Object();

  static BgServicesPlatform _instance = MethodChannelBgServices();

  /// The default instance of [BgServicesPlatform] to use.
  ///
  /// Defaults to [MethodChannelBgServices].
  static BgServicesPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [BgServicesPlatform] when
  /// they register themselves.
  static set instance(BgServicesPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
