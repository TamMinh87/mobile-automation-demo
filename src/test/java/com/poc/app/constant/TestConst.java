package com.poc.app.constant;

public class TestConst {
    public static final String USER_DATA_PATH = "test-data/user_data.json";
    public static final String API_DATA_PATH = "test-data/api_data.json";
    public static final String PROMO_CODE_DATA_PATH = "test-data/promo_code_data.json";
    public static final String YOUR_ODER_COMPLETED_MESSAGE = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";
    public static final String REQUEST_PAYLOAD_PATH = "src/test/resources/test-data/request/%s.json";

    public static final String RESPONSE_SCHEMA_PATH = "src/test/resources/test-data/schema/%s.json";
    private TestConst() {
        throw new IllegalStateException("TestConst class");
    }

    public static final int MAX_TIMESTAMP_GAP = 1500;



}
