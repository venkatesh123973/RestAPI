package org.apiautomationbdd;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class postBDD {
    @Test
    public void createNewUser() {

        RestAssured.baseURI = "https://reqres.in/api";

        Response resp = RestAssured
                .given()
                .log()
                .all()
                .body("{\n"
                        + "    \"name\": \"Mukesh\",\n"
                        + "    \"job\": \"Mentor\"\n"
                        + "}")
                .post("/users");

        Assert.assertEquals(resp.statusCode(), 201);

    }

    @Test
    public void createUser() {

        RestAssured.baseURI = "https://reqres.in/api";

        Response resp = RestAssured
                .given()
                .log()
                .all()
                .body("{\n"
                        + "    \"name\": \"Mukesh\",\n"
                        + "    \"job\": \"Mentor\"\n"
                        + "}")
                .post("/users").then().log().all().extract().response();

        Assert.assertEquals(resp.statusCode(), 201);

    }


}
