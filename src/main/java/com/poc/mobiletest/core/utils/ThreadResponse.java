package com.poc.mobiletest.core.utils;

import io.restassured.response.Response;

public enum ThreadResponse {
    RESPONSE;
    protected static ThreadLocal<Response> response = new ThreadLocal<>();

    public Response current() {
        return response.get();
    }

    public void set(Response rp) {
        response.set(rp);
    }
}
