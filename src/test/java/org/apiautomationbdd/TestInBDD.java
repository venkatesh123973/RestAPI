package org.apiautomationbdd;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

public class TestInBDD {
    @Test
    public void getUsers()
    {
        given()
                .queryParam("page", "2")
                .log()
                .all()
                .baseUri("https://reqres.in/api")
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK");

    }

}
