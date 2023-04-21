package com.poc.mobiletest.core.drivers;

import java.security.InvalidParameterException;


public class DriverTypeFactory extends AFactory {

    public IMobileDriver getDriverType(String platform) {
        if (platform == null) {
            return null;
        }
        switch (platform) {
            case "iOS":
                return new IOSApp();
            case "Android":
                return new AndroidApp();
            default:
                throw new InvalidParameterException("The '" + platform + "' platform is unsupported.");
        }
    }

    public IMobileCapability getMobileCapability(String platform) {
        return null;
    }

    @Override
    public IDeviceFarm getDeviceFarm(String deviceFarm) {
        return null;
    }
}
