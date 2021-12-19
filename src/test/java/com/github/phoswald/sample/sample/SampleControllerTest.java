package com.github.phoswald.sample.sample;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class SampleControllerTest {

    @Test
    public void testEndpoint() {
        given()
                .when().get("/app/pages/sample")
                .then()
                .statusCode(200)
                .body(containsString("Hello, World!"), containsString("<td>user.home</td>"));
    }
}
