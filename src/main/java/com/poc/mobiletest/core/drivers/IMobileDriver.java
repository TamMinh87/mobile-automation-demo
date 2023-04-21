package com.poc.mobiletest.core.drivers;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;

public interface IMobileDriver {
    AppiumDriver<?> driverType(String hub, DesiredCapabilities caps);
}
