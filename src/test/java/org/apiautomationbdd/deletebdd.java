package org.apiautomationbdd;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class deletebdd {
    @Test
    public void deletSingleUserone()
    {
        RestAssured.baseURI="https://reqres.in/api";

        Response resp=RestAssured.given().log().all().delete("/users/2").then().log().all().extract().response();

        Assert.assertEquals(resp.statusCode(), 204);
    }

}
