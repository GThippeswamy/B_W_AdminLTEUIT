package com.sentinel.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DataIntegrityTest {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;

        try {
            Response response = RestAssured.get("/posts/1");
            if (response.getStatusCode() != 200) {
                throw new RuntimeException("API server not responding.");
            }
            System.out.println("API server reachable. Status code: " + response.getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot connect to API server.");
        }
    }

    @Test
    public void verifyUserInUI() {
        Response response = RestAssured.get("/users/1");
        System.out.println("verifyUserInUI Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void createUserAPI() {
        String requestBody = """
                {
                    "name": "John Doe",
                    "username": "johndoe",
                    "email": "john@example.com"
                }
                """;

        Response response = RestAssured
                .given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .post("/users");

        System.out.println("createUserAPI Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    @Test
    public void fileUploadTest() {
        System.out.println("File upload test skipped - demo only");
    }
}
