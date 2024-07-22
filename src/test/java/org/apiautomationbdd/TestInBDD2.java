package org.apiautomationbdd;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestInBDD2 {

    @Test
    public void getUsers() {
        given()
                .queryParam("page", "2")
                .log()
                .all()
                .baseUri("https://reqres.in/api")
                .when()
                .get("/users")
                .then()
                .body("data.email", everyItem(containsString("@reqres.in")))
                .body("data.id", hasItems(10));
    }

    @Test
    public void getSingleUsers() {
        given()
                .log()
                .all()
                .baseUri("https://reqres.in/api")
                .when()
                .get("/users/2")
                .then()
                .body("data.id", equalTo(2))
                .body("data.email", startsWith("janet"))
                .body("data.email", endsWith("reqres.in"))
                .body("data.email", containsString("weaver"))
                .body("support.text", notNullValue());
    }


}

