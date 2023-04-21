package com.poc.mobiletest.core.drivers;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AndroidApp implements IMobileDriver{
    public AppiumDriver<?> driverType(String hub, DesiredCapabilities caps){
        try {
            return new AndroidDriver<>(new URL(hub), caps);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Unable to start Android driver [" + caps.toString() + "] due to malformed URL.", e);
        }
    }
}
