package com.poc.mobiletest.core.drivers;

public abstract class AFactory {

    public abstract IMobileDriver getDriverType(String platform);

    public abstract IMobileCapability getMobileCapability(String platform);
    
    public abstract IDeviceFarm getDeviceFarm(String deviceFarm);
}
