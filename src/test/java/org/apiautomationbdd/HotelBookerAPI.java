package org.apiautomationbdd;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class HotelBookerAPI {

    String token;

    int bookingID;

    @Test(priority = 0)
    public void createToken() {
        baseURI = "https://restful-booker.herokuapp.com";

        Response resp = given()
                .header("Content-Type", "application/json")
                .body("{\n"
                        + "    \"username\" : \"admin\",\n"
                        + "    \"password\" : \"password123\"\n"
                        + "}")
                .post("/auth");

        Assert.assertEquals(resp.getStatusCode(), 200);

        token = resp.jsonPath().getString("token");

        System.out.println("Token Value " + token);

    }

    @Test(priority = 1)
    public void createBooking() {
        baseURI = "https://restful-booker.herokuapp.com";

        Response resp = given().header("Content-Type", "application/json")

                .body("{\n"
                        + "    \"firstname\" : \"Jim\",\n"
                        + "    \"lastname\" : \"Brown\",\n"
                        + "    \"totalprice\" : 111,\n"
                        + "    \"depositpaid\" : true,\n"
                        + "    \"bookingdates\" : {\n"
                        + "        \"checkin\" : \"2018-01-01\",\n"
                        + "        \"checkout\" : \"2019-01-01\"\n"
                        + "    },\n"
                        + "    \"additionalneeds\" : \"Breakfast\"\n"
                        + "}")
                .post("/booking");

        Assert.assertEquals(resp.getStatusCode(), 200);

        bookingID = resp.jsonPath().getInt("bookingid");

        System.out.println("Booking id " + bookingID);

    }

    @Test(priority = 2)
    public void getBooking() {
        baseURI = "https://restful-booker.herokuapp.com";


        Response resp = given().log().all().header("Content-Type", "application/json").pathParam("bid", bookingID)

                .get("/booking/{bid}");

        Assert.assertEquals(resp.getStatusCode(), 200);
    }


    @Test(priority = 3)
    public void deleteBooking() {
        baseURI = "https://restful-booker.herokuapp.com";

        Response resp = given().log().all().header("Content-Type", "application/json").header("Cookie", "token=" + token).pathParam("bid", bookingID)
                .delete("booking/{bid}");

        Assert.assertEquals(resp.getStatusCode(), 201);

    }
}
