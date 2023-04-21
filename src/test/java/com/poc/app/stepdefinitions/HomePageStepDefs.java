package com.poc.app.stepdefinitions;

import com.poc.app.pages.HomePagePoc;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.List;
import java.util.Map;

public class HomePageStepDefs extends BaseStepDefs {
    HomePagePoc homePagePoc;

    public HomePageStepDefs() {
        super();
        homePagePoc = new HomePagePoc();
    }

    @When("the user add product to cart")
    public void theUserAddAProductIntoCart(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            homePagePoc.clickOnAProduct(columns.get("Product Name"));
        }

    }

    @When("the user should see the cart displayed with {int} product")
    public void theUserSeeNumberProductInCart(int number) {
        homePagePoc.verifyNumberProductIsAddedInCart(number);
    }

    @When("the user navigate to the cart detail")
    public void theUserNavigateToCartDetail() {
        homePagePoc.navigateToCart();
    }

}
