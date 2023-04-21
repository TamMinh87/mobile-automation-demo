package com.poc.mobiletest.core.pages;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

import java.time.Duration;

import com.poc.mobiletest.core.utils.Helper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.poc.mobiletest.core.constant.POCConst;
import com.poc.mobiletest.core.drivers.POCMobileDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class PageBase {
    protected static final Logger LOGGER = LoggerFactory.getLogger(PageBase.class);
    protected AppiumDriver<?> driver;
    private WebDriverWait wait;

    public PageBase() {
        driver = POCMobileDriver.getDriverInstance();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        wait = POCMobileDriver.getWebDriverWait();
    }

    public MobileElement waitForElementVisible(MobileElement element) {
        return (MobileElement) wait.until(ExpectedConditions.visibilityOf(element));
    }

    public MobileElement waitForElementEnable(MobileElement element) {
        return (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean isElementPresent(MobileElement element) {
        try {
            waitForElementVisible(element);
            element.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementEnabled(MobileElement element) {
        try {
            waitForElementEnable(element);
            element.isEnabled();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementPresent2(MobileElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (ElementNotVisibleException e1) {
            return true;
        } catch (NoSuchElementException e2) {
            return false;
        }
    }

    public Boolean waitForElementToBeInVisible(MobileElement element) {
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public MobileElement waitForElementToBeClickable(MobileElement element) {
        return (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public MobileElement scrollToText(String text) {
        try {
            String location;
            if (driver.getPlatformName().equalsIgnoreCase("Android")) {
                // if cannot see element try to scroll 5 times
                int i = 1;
                do {
                    try {
                        LOGGER.info("Starting scroll to text '" + text + "' on ' ...");
                        location = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
                                + text.trim() + "\"))";
                        return (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(location));
                    } catch (Exception e) {
                        LOGGER.warn("Unable to scroll to text '" + text + "' on the " + i + " time(s)");
                        Helper.delay(2);
                        i++;
                    }
                } while (i <= 5);
            } else if (driver.getPlatformName().equalsIgnoreCase("iOS")) {
                location = ".scrollViews()[0].scrollToElementWithPredicate(\"name CONTAINS '" + text + "'\")";
                return (MobileElement) driver.findElement(MobileBy.iOSNsPredicateString(location));
            }
        } catch (Exception e) {
            LOGGER.error("Unable to scroll to text '" + text + "' on '" + driver.getPlatformName() + "'", e);
        }
        return null;
    }

    public void swipeByElements(AndroidElement startElement, AndroidElement endElement) {
        int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
        int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);
        int endX = endElement.getLocation().getX() + (endElement.getSize().getWidth() / 2);
        int endY = endElement.getLocation().getY() + (endElement.getSize().getHeight() / 2);
        new TouchAction<>(driver).press(point(startX, startY)).waitAction(waitOptions(ofMillis(1000))).moveTo(point(endX, endY)).release().perform();
    }

    public TouchAction<?> tapCoordinate(MobileElement element) {
        TouchAction<?> touchAction = new TouchAction<>(driver);
        int startX = element.getLocation().getX();
        int startY = element.getLocation().getY();
        return touchAction.tap(PointOption.point(startX, startY)).perform();
    }

    public void scrollToElement(MobileElement element, String direction) {
        for (int i = 0; i < 20; i++) {
            try {
                if (element.isDisplayed()) {
                    return;
                }
            } catch (NoSuchElementException e) {
                Helper.delay(2);
                scrollUpDown(direction);
            }
        }
    }

    public void scrollToElement(MobileElement element, String direction, int numberSwipe) {
        for (int i = 0; i < numberSwipe; i++) {
            try {
                if (element.isDisplayed()) {
                    return;
                }
            } catch (NoSuchElementException e) {
                Helper.delay(2);
                scrollUpDown(direction);
            }
        }
    }

    public void scrollToElement2(MobileElement element, String direction) {
        boolean isElementVisible = false;
        int maximumTryTimes = 20;
        int currentTryTimes = 0;
        while (!isElementVisible && currentTryTimes <= maximumTryTimes) {
            try {
                if (element.isDisplayed()) {
                    isElementVisible = true;
                }
            } catch (NoSuchElementException e) {
                scrollUpDown(direction);
            }
            currentTryTimes++;
        }
    }

    public TouchAction<?> scrollUpDown(String direction) {
        Dimension dim = driver.manage().window().getSize();
        int x = dim.getWidth() / 2;
        int startY = 0;
        int endY = 0;
        switch (direction) {
            case "Up":
                startY = (int) (dim.getHeight() * 0.2);
                endY = (int) (dim.getHeight() * 0.8);
                break;
            case "Down":
                startY = (int) (dim.getHeight() * 0.8);
                endY = (int) (dim.getHeight() * 0.6);
                break;
            default:
                throw new IllegalArgumentException("Only support Up/Down direction.");
        }
        TouchAction<?> touchAction = new TouchAction<>(driver);
        return touchAction.press(PointOption.point(x, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(300))).moveTo(PointOption.point(x, endY))
                .release().perform();
    }

    public void tap(MobileElement element) {
        int i = 1;
        do {
            try {
                element.click();
                return;
            } catch (Exception e) {
                LOGGER.warn("Unable to tap element '" + i + "' time(s).");
                i++;
                Helper.delay(3);
            }
        } while (i <= POCConst.RETRY_COUNTER);
    }

    public boolean waitForElementDisplay(MobileElement element) {
        int i = 1;
        do {
            try {
                waitForElementVisible(element);
                return element.isDisplayed();
            } catch (WebDriverException e) {
                LOGGER.warn("The element cannot appear at '" + i + "' time(s).");
                i++;
                Helper.delay(3);
            }
        } while (i <= POCConst.RETRY_COUNTER);
        return false;
    }

    public MobileElement waitForElementVisibility(MobileElement element, int seconds) {
        return (MobileElement) new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public boolean waitForElementDisplayInSeconds(MobileElement element, int seconds) {
        try {
            return waitForElementVisibility(element, seconds).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
