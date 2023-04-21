package com.poc.mobiletest.core.drivers;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.poc.mobiletest.core.models.MobileCapability;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import io.appium.java_client.remote.MobileCapabilityType;

public class POCMobileDriver {
    private static final Logger LOGGER = LoggerFactory.getLogger(POCMobileDriver.class);
    private static final String ERROR_MESSAGE = "Unable to start Appium Driver";
    private static Map<Long, AppiumDriver<?>> map = new HashMap<>();
    private static WebDriverWait wait;

    private POCMobileDriver() {
        throw new IllegalStateException("POCMobileDriver class");
    }
    
    public static AppiumDriver<?> getDriverInstance() {
        return map.get(Thread.currentThread().getId());
    }
    
    public static AppiumDriver<?> start(MobileCapability mobileCapability) {
        AppiumDriver<?> driver = null;
        String platform = mobileCapability.getPlatform();
        if (mobileCapability.getDeviceFarm().equals("BrowserStack")) {
            platform = "browserStack";
        }
        IMobileCapability iMobileCapability = ProducerFactory.getFactory("MobileCapability").getMobileCapability(platform);
        DesiredCapabilities cap = iMobileCapability.getCapability(mobileCapability);
        IMobileDriver iMobileDriver = ProducerFactory.getFactory("DriverType").getDriverType(mobileCapability.getPlatform());
        IDeviceFarm iDeviceFarm = ProducerFactory.getFactory("DeviceFarm").getDeviceFarm(mobileCapability.getDeviceFarm());
        try {
            driver = iMobileDriver.driverType(iDeviceFarm.getHub(mobileCapability), cap);
            //Use to log activity of events
            driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new AppiumEventListener());
            wait = new WebDriverWait(driver, mobileCapability.getTimeout());
            map.put(Thread.currentThread().getId(), driver);
            LOGGER.info("[" + mobileCapability.getDeviceFarm() + " mode] The Appium driver is loading... [" + cap.toString() + "]");
            return driver;
        } catch (SessionNotCreatedException e) {
            LOGGER.error(ERROR_MESSAGE + " [" + mobileCapability.toString() + "] due to session not created error.", e);
            throw new SessionNotCreatedException(ERROR_MESSAGE + " [" + mobileCapability.toString() + "] due to session not created error.", e);
        } catch (WebDriverException e) {
            LOGGER.error(ERROR_MESSAGE + " [" + mobileCapability.toString() + "] due to WebDriver error.", e);
            throw new IllegalArgumentException(ERROR_MESSAGE + " [" + mobileCapability.toString() + "] due to WebDriver error.", e);
        }
    }
    
    public static String getDeviceName() {
        AppiumDriver<?> driver = map.get(Thread.currentThread().getId());
        return driver.getCapabilities().getCapability(MobileCapabilityType.DEVICE_NAME).toString();
    }
    
    public static String getPlatformVersion() {
        AppiumDriver<?> driver = map.get(Thread.currentThread().getId());
        return driver.getCapabilities().getCapability(MobileCapabilityType.PLATFORM_VERSION).toString();
    }

    public static String getPlatform() {
        AppiumDriver<?> driver = map.get(Thread.currentThread().getId());
        return driver.getCapabilities().getCapability(MobileCapabilityType.PLATFORM_NAME).toString();
    }

    public static String getBrowserName() {
        AppiumDriver<?> driver = map.get(Thread.currentThread().getId());
        return driver.getCapabilities().getCapability(MobileCapabilityType.BROWSER_NAME).toString();
    }

    public static WebDriverWait getWebDriverWait() {
        return wait;
    }
    
    public static void stop() {
        AppiumDriver<?> driver = map.get(Thread.currentThread().getId());
        if(driver != null){
            String driverInfo = getDeviceName() + " - " + getPlatform() + " " + getPlatformVersion();
            driver.quit();
            LOGGER.info("The Appium driver '" + driverInfo + "' is closed successfully!\n");
        }
    }
}

