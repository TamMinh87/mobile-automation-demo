package com.poc.mobiletest.core.drivers;

/*************************************** PURPOSE **********************************

 - This class implements the WebDriverEventListener, which is included under events.
 The purpose of implementing this interface is to override all the methods and define certain useful  Log statements 
 which would be displayed/logged as the application under test is being run.

 Do not call any of these methods, instead these methods will be invoked automatically
 as an when the action done (click, findBy etc). 

 */

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.appium.java_client.events.api.general.AppiumWebDriverEventListener;

public class AppiumEventListener implements AppiumWebDriverEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppiumEventListener.class);

    public void beforeNavigateTo(String url, WebDriver driver) {
        LOGGER.info("Before navigating to: '" + url + "'");
    }

    public void afterNavigateTo(String url, WebDriver driver) {
        LOGGER.info("Navigated to: '" + url + "'");
    }

    public void beforeChangeValueOf(WebElement element, WebDriver driver) {
        LOGGER.info("Value of the: '" + element.toString() + "' before any changes made");
    }

    public void afterChangeValueOf(WebElement element, WebDriver driver) {
        LOGGER.info("Element value changed to: '" + element.toString() + "'");
    }

    public void beforeClickOn(WebElement element, WebDriver driver) {
        LOGGER.info("Trying to click on: '" + element.toString() + "'");
    }

    public void afterClickOn(WebElement element, WebDriver driver) {
        LOGGER.info("Clicked on: '" + element.toString() + "'");
    }

    public void beforeNavigateBack(WebDriver driver) {
        LOGGER.info("Navigating back to previous page");
    }

    public void afterNavigateBack(WebDriver driver) {
        LOGGER.info("Navigated back to previous page");
    }

    public void beforeNavigateForward(WebDriver driver) {
        LOGGER.info("Navigating forward to next page");
    }

    public void afterNavigateForward(WebDriver driver) {
        LOGGER.info("Navigated forward to next page");
    }

    public void onException(Throwable error, WebDriver driver) {
        LOGGER.error("Exception occured: " + error);
    }

    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        LOGGER.info("Trying to find Element By: '" + by.toString() + "'");
    }

    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        LOGGER.info("Found Element By: '" + by.toString() + "'");
    }

    /*
     * non overridden methods of WebListener class
     */
    public void beforeScript(String script, WebDriver driver) {
        // TODO Auto-generated method stub
    }

    public void afterScript(String script, WebDriver driver) {
        // TODO Auto-generated method stub
    }

    public void beforeAlertAccept(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public void afterAlertAccept(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public void afterAlertDismiss(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public void beforeAlertDismiss(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public void beforeNavigateRefresh(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public void afterNavigateRefresh(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        // TODO Auto-generated method stub

    }

    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        // TODO Auto-generated method stub

    }

    public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
        // TODO Auto-generated method stub

    }

    public void afterGetText(WebElement arg0, WebDriver arg1, String arg2) {
        // TODO Auto-generated method stub

    }

    public void afterSwitchToWindow(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub

    }

    public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
        // TODO Auto-generated method stub

    }

    public void beforeGetText(WebElement arg0, WebDriver arg1) {
        // TODO Auto-generated method stub

    }

    public void beforeSwitchToWindow(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub

    }

}