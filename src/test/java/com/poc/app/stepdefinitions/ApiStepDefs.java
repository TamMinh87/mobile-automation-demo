package com.poc.app.stepdefinitions;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.poc.app.models.APIData;
import com.poc.app.utils.Helper;
import com.poc.app.utils.JsonHelpers;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.internal.RequestSpecificationImpl;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.poc.app.constant.TestConst.MAX_TIMESTAMP_GAP;
import static com.poc.app.constant.TestConst.REQUEST_PAYLOAD_PATH;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiStepDefs extends BaseStepDefs {

    public ApiStepDefs() {
        super();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiStepDefs.class);

    @When("the user set API resource to {string}")
    public void setApiResource(String apiName) {
        String uri;
        String path;
        Optional<APIData> apiData = Helper.getAPIInfo(apiName);
        if (apiData.isPresent()) {
            uri = apiData.get().getUri();
            path = apiData.get().getPath();
        } else {
            throw new RuntimeException("Unable to sent API because apiData is not present: " + apiData);
        }
        if (apiName == "login") {
            request().reset();
        }
        testContext().set("baseURI",uri);
        testContext().set("basePath",path);
        request().current().baseUri(uri);
        request().current().basePath(path);
    }

    @When("the user have a request payload {word}")
    public void setRequestPayload(String fileName) throws IOException {
        FileInputStream fis =
                new FileInputStream(String.format(REQUEST_PAYLOAD_PATH, fileName));
        testContext().set("payload", IOUtils.toString(fis));
    }

    @When("the user update request payload with following items")
    public void iUpdateRequestPayloadItemIdWithDynamicValueId(List<Map<String, String>> dataTable) {
        JSONObject jsonObject = JSONObject.parseObject(testContext().get("payload").toString());
        dataTable.forEach((query) -> {
            JSONPath.set(jsonObject, query.get("Path"), query.get("Value"));
        });
        testContext().set("payload", jsonObject.toString());
    }

    @When("the user call POST request")
    public void iCallPostRequest() {
        //Prepare Request body
        String requestBody = testContext().get("payload").toString();
        request().current().body(requestBody);

        //Send GET request
        response().set(request().current().post());
        LOGGER.info("status code:" + response().current().getStatusCode());
        String responseBody = response().current().getBody().asString();
        testContext().set("statusCode", response().current().getStatusCode());
        testContext().set("responseBody", responseBody);
    }

    @Then("the user expect to see response code {int}")
    public void checkResponseCode(int expectStatusCode) {
        int actualStatusCode = (int) testContext().get("statusCode");
        assertThat(actualStatusCode).isEqualTo(expectStatusCode);
    }

    @And("the user expect the response return in correct the schema {word}")
    public void iShouldSeeTheSchemaIsCorrect(String fileName) throws Exception {
        JsonHelpers.validateSchema(fileName, testContext().get("responseBody").toString());
    }

    @Then("the user expect to get an response with correct information")
    public void iExpectResponseReturnWithCorrectInfo(List<Map<String, String>> dataTable) {
        SoftAssert softAssert = new SoftAssert();
        dataTable.forEach((query) -> {
            String path = query.get("Path");
            String value = query.get("Value");
            Object actualValue = JSONPath.read(testContext().get("responseBody").toString(), path);
            String actual = actualValue == null ? null : actualValue.toString();
            if (value.toString().startsWith("contains_")) {
                softAssert.assertTrue(actual.contains(value.toString().replace("contains_", "")), String.format("\n ERROR: Expected value at '%s' is '%s', but actually is '%s'\n", path, value, actual));
            } else if (!path.equals("$.timestamp")) {
                softAssert.assertEquals(actual, value, String.format("\n ERROR: Expected value at '%s' is '%s', but actually is '%s'\n", path, value, actual));
            } else {
                long currentTimestamp = System.currentTimeMillis();
                softAssert.assertTrue(currentTimestamp - Long.parseLong(actual) < MAX_TIMESTAMP_GAP, String.format("The actual Timestamp is over than 1 second than expected Timestamp \n Current: %s, actual: %s"
                        , currentTimestamp, Long.parseLong(actual)));
            }
        });
        softAssert.assertAll();
    }

    @Then("the user get field {string} in response and save to test context with key {string}")
    public void iGetFieldInResponseToTestContextWithKey(String field, String key) {
        String value = iGetFieldInResponse(field);
        LOGGER.info("Got '{}' from key '{}'. And store to test context", value, field);
        testContext().set(key, value);
    }

    private String iGetFieldInResponse(String field) {
        JSONObject responseBody = JSONObject.parseObject(testContext().get("responseBody").toString());
        System.out.println(responseBody);
        LOGGER.info("Got response {}", responseBody.toString());
        return JSONPath.read(responseBody.toJSONString(), field).toString();
    }

    @Then("the user set Token for the request with key {string}")
    public void iGetFieldInResponseToTestContextWithKey(String key) {
        String value = testContext().get(key).toString();
        System.out.println(value);
        String auth = String.format("Bearer %s", value);
        request().current().header("Authorization", auth);
    }

    @When("the user set request header with key {string} and value {string}")
    public void setUpRequestHeader(String key, String value) {
        ((RequestSpecificationImpl) request().current()).removeHeader(key);
        request().current().header(key, value);
    }

    @And("the user update API base path with value {string}")
    public void theUserUpdateAPIBasePathWithValue(String value) {
        String newPath = String.format(testContext().get("basePath").toString(),value);
        request().current().basePath(newPath);
    }

    @When("the user call GET request")
    public void iCallGetRequest() {
        //Send GET request
        response().set(request().current().get());
        LOGGER.info("status code:" + response().current().getStatusCode());
        String responseBody = response().current().getBody().asString();
        System.out.println(responseBody);
        testContext().set("statusCode", response().current().getStatusCode());
        testContext().set("responseBody", responseBody);
    }
}
