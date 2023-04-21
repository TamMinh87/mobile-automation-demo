package com.poc.app.pages;

import com.poc.mobiletest.core.pages.PageBase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.assertj.core.api.AutoCloseableSoftAssertions;

import static com.poc.app.constant.TestConst.YOUR_ODER_COMPLETED_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginPagePoc extends PageBase {

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Username\"]")
    @iOSXCUITFindBy(iOSNsPredicate = "value = 'XXX-XXX-XXXX'")
    private MobileElement userNameTxt;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Password\"]")
    @iOSXCUITFindBy(iOSNsPredicate = "value = 'XXX-XXX-XXXX'")
    private MobileElement passwordTxt;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]")
    @iOSXCUITFindBy(iOSNsPredicate = "value = 'XXX-XXX-XXXX'")
    private MobileElement loginBtn;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
    @iOSXCUITFindBy(iOSNsPredicate = "value = 'XXX-XXX-XXXX'")
    private MobileElement errorMessage;


    public void clickOnLoginButton() {
        waitForElementDisplay(loginBtn);
        tap(loginBtn);
    }

    public void inputUserNameAndPassword(String username, String password) {
        waitForElementDisplay(userNameTxt);
        userNameTxt.sendKeys(username);
        passwordTxt.sendKeys(password);
    }

    public void isAtCurrentPage() {
        assertThat(isElementPresent(userNameTxt)).as("userNameTxt is not present.").isTrue();
        assertThat(isElementPresent(passwordTxt)).as("passwordTxt is not present.").isTrue();
        assertThat(isElementPresent(loginBtn)).as("loginBtn is not present.").isTrue();
    }

    public void verifyErrorMessageDisplayedWithContent(String message) {
        assertThat(errorMessage.getText()).isEqualTo(message);
    }
}
