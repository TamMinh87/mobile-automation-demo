package com.poc.app.runners;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.poc.mobiletest.core.utils.Profile;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
                features = "src/test/resources/features/", 
                glue = {"com/poc/app/stepdefinitions"},
                plugin = {
                    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                    "pretty", "html:target/cucumber-reports/report.html",
                     "junit:target/cucumber-reports/cucumber.xml", 
                     "json:target/cucumber-reports/cucumber.json"
                },
                tags = "not @ignore"
)
public class TestRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestRunner.class);

    private TestRunner() {
        throw new IllegalStateException("TestRunner class");
    }
    
    @BeforeClass
    public static void setupClass() throws Exception {
        String env = System.getProperty("env");
        if(env == null)
            env = "SIT";
        if(!env.equalsIgnoreCase("SIT") && !env.equalsIgnoreCase("UAT") && !env.equalsIgnoreCase("PROD"))
            throw new Exception("Environment is not supported");

        final String envVariable = System.getProperty("properties");
        if (envVariable == null) {
            LOGGER.info("The 'default.properties' is loading...");
            Profile.createInstance().setEnvirontmentVariables("default.properties");
        } else {
            LOGGER.info("The '" + envVariable + "' is loading...");
            Profile.createInstance().setEnvirontmentVariables(envVariable);
        }
    }
}