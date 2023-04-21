package com.poc.app.stepdefinitions;

import com.poc.app.pages.HomePagePoc;
import com.poc.app.pages.LoginPagePoc;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class LoginStepDefs extends BaseStepDefs{
    private final LoginPagePoc loginPagePoc;
    private final HomePagePoc homePagePoc;

    public LoginStepDefs() {
        super();
        loginPagePoc = new LoginPagePoc();
        homePagePoc = new HomePagePoc();
    }

    @Given("the user enter username: {string} and password: {string}")
    public void theUserEnterUsernamePassword(String username, String password) {
        loginPagePoc.inputUserNameAndPassword(username, password);
    }

    @And("the user click login button")
    public void theUserClickLoginButton() {
        loginPagePoc.clickOnLoginButton();
    }

    @And("the user should see an error message on login page: {string}")
    public void theUserShouldSeeErrorLoginPage(String message) {
        loginPagePoc.verifyErrorMessageDisplayedWithContent(message);
    }


}
