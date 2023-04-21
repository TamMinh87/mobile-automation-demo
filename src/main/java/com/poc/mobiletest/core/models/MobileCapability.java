package com.poc.mobiletest.core.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class MobileCapability implements Serializable {
    private static final long serialVersionUID = 1L;
    private String hub;
    private boolean realDevice;
    private String appiumVersion;
    private String platform;
    private String platformVersion;
    private String automationName;
    private String deviceName;
    private boolean noReset;
    private boolean fullReset;
    private String app;
    private int timeout;
    // iOS
    private String bundleid;
    private String udid;
    private boolean nativeInstrumentsLib;
    private String waitForAppScript;
    private int newCommandTimeout;
    private boolean autoAcceptAlerts;
    // Android
    private String appPackage;
    private String appActivity;
    private String appWaitActivity;
    private String appWaitPackage;
    private boolean autoGrantPermissions;
    // BrowserStack or Sauce Labs
    private String deviceFarm;
    private String username;
    private String accesskey;
    private String device;
    private String os_version;
    private String project;
    private String browserStackUser;
    private String browserStackKey;
    private String browserStackAppUrl;
    private String language;
    private String locale;
}
