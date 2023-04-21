package com.poc.mobiletest.core.drivers;

import java.security.InvalidParameterException;

public class CapabilityFactory extends AFactory {

    public IMobileCapability getMobileCapability(String platform) {
        if (platform == null) {
            return null;
        }
        switch (platform) {
            case "iOS":
                return new IOSCapability();
            case "Android":
                return new AndroidCapability();
            case "browserStack":
                return new BrowserStackCapability();
            default:
                throw new InvalidParameterException("The '" + platform + "' platform is unsupported.");
        }
    }

    public IMobileDriver getDriverType(String platform) {
        return null;
    }

    @Override
    public IDeviceFarm getDeviceFarm(String deviceFarm) {
        return null;
    }

}
