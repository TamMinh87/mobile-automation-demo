package com.poc.mobiletest.core.utils;

import java.util.HashMap;
import java.util.Map;

import static java.lang.ThreadLocal.withInitial;

public enum TestContext {
    CONTEXT;
    private final ThreadLocal<Map<String, Object>> testContexts = withInitial(HashMap::new);

    /**
     * Get string value from test context
     *
     * @param key key of stored test context
     */
    public String getString(String key) {
        if (testContexts.get().get(key) == null) {
            throw new NullPointerException(String.format("key %s is not existed", key));
        }
        return testContexts.get().get(key).toString() ;
    }

    /**
     * Get value from test context
     *
     * @param key key of stored test context
     */
    public <T> T get(String key) {
        return (T) testContexts.get().get(key);
    }

    /**
     * Set value value to test context
     *
     * @param key    key of stored test context
     * @param object object of stored test context
     */
    public <T> void set(String key, T object) {
        testContexts.get().put(key, object);
    }

    /**
     * Check is test context contains the key
     *
     * @param key
     * @return true or false
     */
    public Boolean contains(String key) {
        return testContexts.get().get(key) != null;
    }

    public <T> void removeKey(String key) {
        testContexts.get().remove(key);
    }
}
