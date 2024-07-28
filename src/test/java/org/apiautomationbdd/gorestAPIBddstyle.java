package org.apiautomationbdd;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class gorestAPIBddstyle {

    @Test
    public void getbddstyle(){

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer f353a2e05089756583c09ebb7cd8832df56b9c9aa14c89f019466549589cdbb4");
        headers.put("Accept", "application/json");

        RestAssured.given().baseUri("https://gorest.co.in").headers(headers).when().get("/public/v2/users").then()
                .log().all().statusCode(200).contentType("application/json").body("id",not(empty())).body("name",not(empty()))
                .body("email",not(empty()));
    }
}
