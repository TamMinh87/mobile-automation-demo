package com.poc.app.stepdefinitions;

import com.poc.mobiletest.core.utils.ScenarioContext;
import com.poc.mobiletest.core.utils.TestContext;
import com.poc.mobiletest.core.utils.ThreadRequest;
import com.poc.mobiletest.core.utils.ThreadResponse;

public class BaseStepDefs {
//    protected static ScenarioContext context;

    public BaseStepDefs() {}

    public static TestContext testContext() {
        return TestContext.CONTEXT;
    }

    public static ThreadRequest request() {
        return ThreadRequest.REQUEST;
    }

    public static ThreadResponse response() {
        return ThreadResponse.RESPONSE;
    }
}
