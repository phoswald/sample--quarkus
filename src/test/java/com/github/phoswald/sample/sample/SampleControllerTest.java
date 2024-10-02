package com.github.phoswald.sample.sample;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;

@QuarkusTest
@TestSecurity(user = "username", roles = {"user"})
public class SampleControllerTest {

    @Test
    public void testEndpoint() {
        given().
        when().
            get("/app/pages/sample").
        then().
            statusCode(200).
            body(containsString("Hello, username!"), containsString("<td>user.home</td>"));
    }
}
