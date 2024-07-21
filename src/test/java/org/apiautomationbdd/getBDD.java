package org.apiautomationbdd;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class getBDD {
    @Test
    public void getUser() {

        RestAssured.baseURI = "https://reqres.in/api";

        Response resp = RestAssured.given().get("/users/2");

        Assert.assertEquals(resp.statusCode(), 200);

        Assert.assertTrue(resp.statusLine().contains("OK"));

        Assert.assertTrue(resp.getContentType().contains("json"));

    }

}
