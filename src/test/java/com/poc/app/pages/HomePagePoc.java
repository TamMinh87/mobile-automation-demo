package com.poc.app.pages;

import com.poc.mobiletest.core.drivers.POCMobileDriver;
import com.poc.mobiletest.core.pages.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;


import static org.assertj.core.api.Assertions.assertThat;

public class HomePagePoc extends PageBase {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name = 'banner']/XCUIElementTypeImage[2]")
    private MobileElement menuIcon;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name = 'banner']/XCUIElementTypeImage[2]")
    private MobileElement cartIcon;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.TextView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name = 'banner']/XCUIElementTypeImage[2]")
    private MobileElement numberProductInCartIcon;


    public void isAtCurrentPage() {
        assertThat(isElementPresent(menuIcon)).as("menuIcon is not present.").isTrue();
    }

    public void clickOnAProduct(String productName) {
        AppiumDriver<?> driver = POCMobileDriver.getDriverInstance();
        driver.findElement(By.xpath(String.format("//android.widget.TextView[@content-desc=\"test-Item title\" and @text=\"%s\"]/..//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"]",productName))).click();
    }

    public void navigateToCart() {
        waitForElementDisplay(cartIcon);
        cartIcon.click();
    }

    public void verifyNumberProductIsAddedInCart(int number) {
        assertThat(Integer.parseInt(numberProductInCartIcon.getText())).isEqualTo(number);
    }
}
