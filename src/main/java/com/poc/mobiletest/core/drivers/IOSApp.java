package com.poc.mobiletest.core.drivers;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

public class IOSApp implements IMobileDriver{
    
    public AppiumDriver<?> driverType(String hub, DesiredCapabilities caps){
        try {
            return new IOSDriver<>(new URL(hub), caps);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Unable to start iOS driver [" + caps.toString() + "] due to malformed URL.", e);
        }
    }
}
