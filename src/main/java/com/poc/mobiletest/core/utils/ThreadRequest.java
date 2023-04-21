package com.poc.mobiletest.core.utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public enum ThreadRequest {
    REQUEST;
    protected ThreadLocal<RequestSpecification> request;

    ThreadRequest() {
        request = new ThreadLocal<>();
    }

    public RequestSpecification current() {
        if (request.get() == null) {
            request.set(RestAssured.given());
        }
        return request.get();
    }

    public void reset() {
        request.set(RestAssured.given());
    }

    public void set(RequestSpecification rq) {
        request.set(rq);
    }
}
