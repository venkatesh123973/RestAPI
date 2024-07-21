package org.apiautomationbdd;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class captureResponse {

    @Test
    public void verifyFields() {

        RestAssured.baseURI = "https://reqres.in/api";

        Response resp = RestAssured.given().get("/users/2");

        JsonPath json = resp.jsonPath();

        int id = json.getInt("data.id");

        System.out.println("ID captured " + id);

        String email = json.getString("data.email");

        String firstname = json.getString("data.first_name");

        String lastname = json.getString("data.last_name");

        String avatar = json.getString("data.avatar");

        String url = json.getString("support.url");

        String text = json.getString("support.text");

        System.out.println(email);
        System.out.println(firstname);
        System.out.println(lastname);
        System.out.println(avatar);
        System.out.println(url);
        System.out.println(text);

        Assert.assertEquals(email, "janet.weaver@reqres.in");

        Assert.assertTrue(email.endsWith("reqres.in"));


    }

    @Test
    public void verifyFieldsresponse() {

        RestAssured.baseURI = "https://reqres.in/api/";

        Response resp = RestAssured.given().log().all().get("/users/2");

        Assert.assertEquals(resp.jsonPath().getString("data.email"), "janet.weaver@reqres.in");

        Assert.assertTrue(resp.jsonPath().getString("data.email").endsWith("reqres.in"));


    }



}
