package com.poc.mobiletest.core.utils;

public class Helper {
    
    private Helper() {
        throw new IllegalStateException("Helper class");
    }

    public static void delay(double d) {
        try {
            Thread.sleep((long) (1000 * d));
        } catch (InterruptedException e) {
            // Restore interrupted state...
            e.printStackTrace();
        }
    }
}
