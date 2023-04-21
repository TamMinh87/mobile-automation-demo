package com.poc.app.stepdefinitions;

import com.poc.app.pages.CartPagePoc;
import io.cucumber.java.en.When;

public class CartPageStepDefs extends BaseStepDefs {
    CartPagePoc cartPagePoc;

    public CartPageStepDefs() {
        super();
        cartPagePoc = new CartPagePoc();
    }

    @When("the user checkout the cart with First Name: {string} and Last Name: {string} and Zipcode: {string}")
    public void theUserCheckOutWithInfo(String firstName, String lastName, String zipcode) {

        cartPagePoc.clickOnCheckOutButton();
        cartPagePoc.inputCheckOutInformation(firstName, lastName, zipcode);
        cartPagePoc.clickContinueButton();
        cartPagePoc.verifyCheckOutOverviewDisplayed();
    }

    @When("the user should see the price of product: {string}")
    public void theUserCheckOutWithInfo(String price) {
        cartPagePoc.verifyPriceOfProductIsDisplayed(price);

    }

    @When("the user finish check out successfully")
    public void theUserCheckOutSuccess() {
        cartPagePoc.clickFinishButton();
        cartPagePoc.verifyYourOrderCompleted();
    }
}
