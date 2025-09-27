package com.sentinel.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.sentinel.utils.ConfigReader;
import java.util.HashMap;
import java.util.Map;

public class UserApiTests {

    private String token;

    @BeforeClass
    public void setup() {
        // Read base URL and token from config
        RestAssured.baseURI = ConfigReader.getProperty("base.url");
        token = ConfigReader.getProperty("api.token");

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("API token not found in config.properties!");
        }

        // Set default headers for all requests
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void createUserTest() {
        Map<String, Object> userData = new HashMap<>();
        userData.put("name", "John Doe");
        userData.put("email", "johndoe" + System.currentTimeMillis() + "@example.com"); // unique email
        userData.put("gender", "male");
        userData.put("status", "active");

        Response response = RestAssured
                .given()
                .body(userData)
                .post("/users");

        System.out.println(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 201, "User creation failed!");
    }

    @Test(dependsOnMethods = "createUserTest")
    public void getUsersTest() {
        Response response = RestAssured
                .get("/users");

        System.out.println(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200, "Fetching users failed!");
    }
}
