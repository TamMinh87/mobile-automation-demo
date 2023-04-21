package com.poc.mobiletest.core.drivers;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.poc.mobiletest.core.models.MobileCapability;

import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidCapability implements IMobileCapability{
    
    public DesiredCapabilities getCapability(MobileCapability cap) {
        DesiredCapabilities caps = DesiredCapabilities.android();
        caps.setCapability("noReset", cap.isNoReset());
        caps.setCapability("fullReset", cap.isFullReset());
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, cap.getPlatform());
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, cap.getPlatformVersion());
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, cap.getDeviceName());
        caps.setCapability("appPackage", cap.getAppPackage());
        caps.setCapability("appActivity", cap.getAppActivity());
        caps.setCapability("appWaitActivity", cap.getAppWaitActivity());
        caps.setCapability("appWaitPackage", cap.getAppWaitPackage());
        caps.setCapability(MobileCapabilityType.APP, cap.getApp());
        caps.setCapability("browserName", "");
        caps.setCapability("autoAcceptAlerts", cap.isAutoAcceptAlerts());
        return caps;
    }
}
