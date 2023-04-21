package com.poc.app.stepdefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.poc.mobiletest.core.drivers.POCMobileDriver;
import com.poc.mobiletest.core.models.MobileCapability;
import com.poc.mobiletest.core.utils.Profile;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends BaseStepDefs {
    private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);

    public Hooks() {
        super();
    }

    @Before("@ui")
    public void beforeScenario(Scenario scenario) {
        //Test purpose only - to run test from feature file
        final String envVariable = System.getProperty("properties");
        if (envVariable == null) {
            LOGGER.info("The 'default.properties' is loading...");
            Profile.createInstance().setEnvirontmentVariables("default.properties");
        } else {
            LOGGER.info("The '" + envVariable + "' is loading...");
            Profile.createInstance().setEnvirontmentVariables(envVariable);
        }

        MobileCapability mobileCapability = Profile.createInstance().getEnvirontmentVariables();
        POCMobileDriver.start(mobileCapability);
        LOGGER.info("\n ######### Staring scenario '" + scenario.getName() + "' #########");
    }

    @After("@ui")
    public void afterScenario(Scenario scenario) {
        AppiumDriver<?> driver = POCMobileDriver.getDriverInstance();
        if(driver == null) {
            return;
        }
        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "image");
        POCMobileDriver.stop();
    }

    @After()
    public void afterScenario2(Scenario scenario) {
        LOGGER.info("\n ######### Ending scenario '" + scenario.getName() + "' with status='" + scenario.getStatus() + "'#########");
    }
}
