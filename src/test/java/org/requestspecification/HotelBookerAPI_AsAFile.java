package org.requestspecification;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class HotelBookerAPI_AsAFile extends Base {

    String token;

    int bookingID;

    @Test(priority = 0)
    public void createToken() {

        Response resp = given()
                .spec(req)
                .body(new File("./src/test/resources/JSONFiles/auth.json"))
                .post("/auth");

        resp.then().assertThat().spec(resp_spec_200);

        token = resp.jsonPath().getString("token");

        System.out.println("Token Value " + token);

    }

    @Test(priority = 1)
    public void createBooking() {

        Response resp = given().spec(req)

                .body(new File("./src/test/resources/JSONFiles/user.json"))
                .post("/booking");

        resp.then().assertThat().spec(resp_spec_200);

        System.out.println(resp.asPrettyString());

        bookingID = resp.jsonPath().getInt("bookingid");

        System.out.println("Booking id " + bookingID);

    }

    @Test(priority = 2)
    public void getBooking() {

        Response resp = given().spec(req).pathParam("bid", bookingID)

                .get("/booking/{bid}");

        resp.then().assertThat().spec(resp_spec_200);

    }


    @Test(priority = 3)
    public void deleteBooking() {


        Response resp = given()
                .spec(req)
                .header("Cookie", "token=" + token)
                .pathParam("bid", bookingID)
                .delete("booking/{bid}").then().log().all().extract().response();

        resp.then().assertThat().spec(resp_spec_201);


    }

}