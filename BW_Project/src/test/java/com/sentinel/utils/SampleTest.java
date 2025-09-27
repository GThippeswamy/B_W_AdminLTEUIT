package com.sentinel.utils;

public class SampleTest {
    public static void main(String[] args) {
        // Call static method directly, no need to create an object
        String baseUrl = ConfigReader.get("base.url");
        String token = ConfigReader.get("api.token");

        System.out.println("Base URL: " + baseUrl);
        System.out.println("API Token: " + token);
    }
}

