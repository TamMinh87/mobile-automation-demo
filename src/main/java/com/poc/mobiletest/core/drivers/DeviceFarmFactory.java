package com.poc.mobiletest.core.drivers;

import java.security.InvalidParameterException;

public class DeviceFarmFactory extends AFactory{

    public IDeviceFarm getDeviceFarm(String deviceFarm) {
        switch (deviceFarm.trim()) {
            case "Local":
                return new LocalHub();
            case "BrowserStack":
                return new BrowserStackHub();
            default:
                throw new InvalidParameterException("The '" + deviceFarm + "' device farm is unsupported.");
        }
    }

    @Override
    public IMobileDriver getDriverType(String platform) {
        return null;
    }

    @Override
    public IMobileCapability getMobileCapability(String platform) {
        return null;
    }

}
