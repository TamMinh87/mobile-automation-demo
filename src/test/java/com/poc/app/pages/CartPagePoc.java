package com.poc.app.pages;

import com.poc.mobiletest.core.pages.PageBase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import static com.poc.app.constant.TestConst.YOUR_ODER_COMPLETED_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;

public class CartPagePoc extends PageBase {

    @AndroidFindBy(xpath = "/android.widget.ScrollView[@content-desc=\"test-Cart Content\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name = 'banner']/XCUIElementTypeImage[2]")
    private MobileElement cartContentIcon;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-CHECKOUT\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name = 'banner']/XCUIElementTypeImage[2]")
    private MobileElement checkOutBtn;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"test-First Name\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name = 'banner']/XCUIElementTypeImage[2]")
    private MobileElement firstNameTxt;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Last Name\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name = 'banner']/XCUIElementTypeImage[2]")
    private MobileElement lastNameTxt;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Zip/Postal Code\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name = 'banner']/XCUIElementTypeImage[2]")
    private MobileElement zipCodeTxt;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-CONTINUE\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name = 'banner']/XCUIElementTypeImage[2]")
    private MobileElement continueBtn;

    @AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: OVERVIEW\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name = 'banner']/XCUIElementTypeImage[2]")
    private MobileElement checkoutOverviewSection;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Price\"]/android.widget.TextView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name = 'banner']/XCUIElementTypeImage[2]")
    private MobileElement priceTxt;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-FINISH\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name = 'banner']/XCUIElementTypeImage[2]")
    private MobileElement finishBtn;

    @AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: COMPLETE!\"]/android.view.ViewGroup/android.widget.TextView[2]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name = 'banner']/XCUIElementTypeImage[2]")
    private MobileElement youOrderCompletedMessageTxt;


    private void isAtCurrentPage() {
        assertThat(isElementPresent(cartContentIcon)).as("cartContentIcon is not present.").isTrue();
    }

    public void clickOnCheckOutButton() {
        scrollToElement(checkOutBtn,"Down");
        waitForElementDisplay(checkOutBtn);
        checkOutBtn.click();
    }

    public void inputCheckOutInformation(String firstName, String lastName, String zipCode) {
        waitForElementDisplay(firstNameTxt);
        firstNameTxt.sendKeys(firstName);
        lastNameTxt.sendKeys(lastName);
        zipCodeTxt.sendKeys(zipCode);
    }

    public void clickContinueButton() {
        waitForElementDisplay(continueBtn);
        continueBtn.click();
    }

    public void verifyCheckOutOverviewDisplayed() {
        assertThat(isElementPresent(checkoutOverviewSection)).as("checkoutOverviewSection is not present.").isTrue();
    }

    public void verifyPriceOfProductIsDisplayed(String price) {
        assertThat(priceTxt.getText()).isEqualTo(price);
    }

    public void verifyYourOrderCompleted() {
        assertThat(youOrderCompletedMessageTxt.getText()).isEqualTo(YOUR_ODER_COMPLETED_MESSAGE);
    }

    public void clickFinishButton() {
        scrollToElement(finishBtn,"Down");
        waitForElementDisplay(finishBtn);
        finishBtn.click();
    }

}
