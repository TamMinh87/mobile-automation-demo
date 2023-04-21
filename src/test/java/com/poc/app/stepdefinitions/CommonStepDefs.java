package com.poc.app.stepdefinitions;

import com.poc.app.models.UserInfo;
import com.poc.app.utils.Helper;
import com.poc.app.pages.HomePagePoc;
import com.poc.app.pages.LoginPagePoc;
import io.cucumber.java.en.Given;

import java.util.Optional;

public class CommonStepDefs extends BaseStepDefs {
    private final LoginPagePoc loginPagePoc;
    private final HomePagePoc homePagePoc;

    public CommonStepDefs() {
        super();
        loginPagePoc = new LoginPagePoc();
        homePagePoc = new HomePagePoc();
    }

    @Given("the user is on login page")
    public void theUserIsOnLoginPage() {
        loginPagePoc.isAtCurrentPage();
    }

    @Given("the user is on home page")
    public void theUserIsOnHomePage() {
        homePagePoc.isAtCurrentPage();
    }

    @Given("the user {string} logged in successfully")
    public void theUserLoggedInSuccessfully(String username) {
        Optional<UserInfo> user = Helper.getUserPoc(username);
        if (user.isPresent()) {
            testContext().set("user", user.get());
        } else {
            testContext().set("user", Optional.empty());
        }
        UserInfo userInfo = testContext().get("user");
        loginPagePoc.isAtCurrentPage();
        loginPagePoc.inputUserNameAndPassword(userInfo.getUsername(), userInfo.getPassword());
        loginPagePoc.clickOnLoginButton();
        homePagePoc.isAtCurrentPage();
    }
}
