package org.apiautomationnonBDD;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class getGorestAPI {

    @Test
    public void gorest(){

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer f353a2e05089756583c09ebb7cd8832df56b9c9aa14c89f019466549589cdbb4");
        headers.put("Accept", "application/json");

        RestAssured.baseURI="https://gorest.co.in";
        Response response= RestAssured.given().headers(headers).get("/public/v2/users");
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
        List<String> id =response.jsonPath().getList("id");
        List<String> name =response.jsonPath().getList("name");
        List<String> emails =response.jsonPath().getList("email");
        Assert.assertFalse(id.isEmpty());
        Assert.assertFalse(name.isEmpty());
        Assert.assertFalse(emails.isEmpty());
        System.out.println(response.asPrettyString());

    }
}
