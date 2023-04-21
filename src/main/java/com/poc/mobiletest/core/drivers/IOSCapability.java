package com.poc.mobiletest.core.drivers;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.poc.mobiletest.core.models.MobileCapability;

import io.appium.java_client.remote.MobileCapabilityType;

public class IOSCapability implements IMobileCapability{
    
    public DesiredCapabilities getCapability(MobileCapability cap) {
        DesiredCapabilities caps = DesiredCapabilities.iphone();
        caps.setCapability("noReset", cap.isNoReset());
        caps.setCapability("fullReset", cap.isFullReset());
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, cap.getPlatform());
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, cap.getPlatformVersion());
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, cap.getDeviceName());
        caps.setCapability("newCommandTimeout", cap.getNewCommandTimeout());
        caps.setCapability("nativeInstrumentsLib", cap.isNativeInstrumentsLib());
        caps.setCapability("launchTimeout", 90000);
        caps.setCapability("autoAcceptAlerts", cap.isAutoAcceptAlerts()); // accept iOS alert
        if (cap.isRealDevice()) {
            caps.setCapability(MobileCapabilityType.UDID, cap.getUdid());
            caps.setCapability("bundleId", cap.getBundleid());
        }
        caps.setCapability(MobileCapabilityType.APP, cap.getApp());
        caps.setCapability("waitForAppScript", cap.getWaitForAppScript());
        return caps;
    }
}
