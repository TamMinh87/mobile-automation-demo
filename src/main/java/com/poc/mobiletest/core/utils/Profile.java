package com.poc.mobiletest.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.poc.mobiletest.core.models.MobileCapability;

public class Profile {
    private static MobileCapability cap = new MobileCapability();

    // create an object of SingleObject
    private static Profile instance = new Profile();

    // make the constructor private so that this class cannot be instantiated
    private Profile() {
    }

    // Get the only object available
    public static Profile createInstance() {
        return instance;
    }

    public void setEnvirontmentVariables(String fileName) {
        InputStream conf = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        InputStream commonConf;

        if (conf == null) {
            throw new IllegalArgumentException("The file '" + fileName + "' not found in 'resources' folder.");
        }
        Properties appConfig = new Properties();
        try {
            appConfig.load(conf);
            cap.setRealDevice(Boolean.parseBoolean(appConfig.getProperty("realDevice")));
            cap.setDeviceFarm(appConfig.getProperty("deviceFarm"));
            cap.setUsername(appConfig.getProperty("devicefarm.username"));
            cap.setAccesskey(appConfig.getProperty("devicefarm.acccesskey"));
            cap.setAppiumVersion(appConfig.getProperty("appium.version"));
            cap.setAutomationName(appConfig.getProperty("appium.automationName"));
            cap.setDevice(appConfig.getProperty("device"));
            cap.setOs_version(appConfig.getProperty("os_version"));
            String platform = appConfig.getProperty("appium.platformName");
            switch (platform) {
                case "iOS":
                    commonConf = Thread.currentThread().getContextClassLoader().getResourceAsStream("common_ios_cap.properties");
                    appConfig.load(commonConf);
                    cap.setHub(appConfig.getProperty("appium.hub"));
                    cap.setTimeout(Integer.parseInt(appConfig.getProperty("appium.timeout")));
                    cap.setLanguage(appConfig.getProperty("appium.language"));
                    cap.setLocale(appConfig.getProperty("appium.locale"));
                    cap.setBrowserStackUser(appConfig.getProperty("browserstack.user"));
                    cap.setBrowserStackKey(appConfig.getProperty("browserstack.key"));
                    cap.setBrowserStackAppUrl(appConfig.getProperty("browserstack_app_url"));
                    cap.setNewCommandTimeout(Integer.parseInt(appConfig.getProperty("appium.newCommandTimeout")));
                    cap.setAutoAcceptAlerts(Boolean.parseBoolean(appConfig.getProperty("appium.autoAcceptAlerts")));
                    cap.setBundleid(appConfig.getProperty("appium.bundleid"));
                    cap.setUdid(appConfig.getProperty("appium.udid"));
                    cap.setNativeInstrumentsLib(Boolean.parseBoolean(appConfig.getProperty("appium.nativeInstrumentsLib")));
                    cap.setWaitForAppScript(appConfig.getProperty("appium.waitForAppScript"));
                    break;
                case "Android":
                    commonConf = Thread.currentThread().getContextClassLoader().getResourceAsStream("common_android_cap.properties");
                    appConfig.load(commonConf);
                    cap.setHub(appConfig.getProperty("appium.hub"));
                    cap.setTimeout(Integer.parseInt(appConfig.getProperty("appium.timeout")));
                    cap.setLanguage(appConfig.getProperty("appium.language"));
                    cap.setLocale(appConfig.getProperty("appium.locale"));
//                    cap.setBrowserStackUser(appConfig.getProperty("browserstack.user"));
//                    cap.setBrowserStackKey(appConfig.getProperty("browserstack.key"));
//                    cap.setBrowserStackAppUrl(appConfig.getProperty("browserstack_app_url"));
                    cap.setNewCommandTimeout(Integer.parseInt(appConfig.getProperty("appium.newCommandTimeout")));
                    cap.setAutoAcceptAlerts(Boolean.parseBoolean(appConfig.getProperty("appium.autoAcceptAlerts")));
                    cap.setAppPackage(appConfig.getProperty("appium.appPackage"));
                    cap.setAppActivity(appConfig.getProperty("appium.appActivity"));
                    cap.setAppWaitActivity(appConfig.getProperty("appium.appWaitActivity"));
                    cap.setAppWaitPackage(appConfig.getProperty("appium.appWaitPackage"));
                    cap.setAutoGrantPermissions(Boolean.parseBoolean(appConfig.getProperty("appium.autoGrantPermissions")));
                    break;
                default:
                    throw new InvalidParameterException("The '" + platform + "' is unsupported.");
            }
            cap.setPlatform(platform);
            cap.setPlatformVersion(appConfig.getProperty("appium.platformVersion"));
            cap.setDeviceName(appConfig.getProperty("appium.deviceName"));
            cap.setApp(appConfig.getProperty("appium.app"));
            if (StringUtils.isEmpty(appConfig.getProperty("appium.fullReset"))) {
                cap.setNoReset(true);
                cap.setFullReset(false);
            } else {
                if (Boolean.parseBoolean(appConfig.getProperty("appium.fullReset"))) {
                    cap.setNoReset(false);
                    cap.setFullReset(true);
                } else {
                    cap.setNoReset(true);
                    cap.setFullReset(false);
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to read the file '" + fileName + "' in 'resources' folder.", e);
        } finally {
            try {
                conf.close();
            } catch (IOException ignore) {
            }
        }
    }

    public MobileCapability getEnvirontmentVariables() {
        return cap;
    }
}
