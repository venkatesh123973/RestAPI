package org.lombok;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.POJO.BookingDates;
import org.POJO.BookingPOJO;
import org.requestspecification.Base;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class getToken extends Base {
    String token;
    int bookingID;

    @Test
    public void createtoken(){
        AuthenticationPOJO payload = new AuthenticationPOJO();
        payload.setUsername("admin");
        payload.setPassword("password123");
       Response resp= RestAssured.given().spec(req).body(payload)
                .post("/auth");

        resp.then().assertThat().spec(resp_spec_200);

        token = resp.jsonPath().getString("token");

        System.out.println("Token Value " + token);
    }

    @Test(priority = 1)
    public void createbooking() {
        BookingLombokPOJO.BookingDates dates = new BookingLombokPOJO.BookingDates("2024-08-03", "2024-08-11");
        BookingLombokPOJO payload = new BookingLombokPOJO("Jimone","Kim",888,true,dates,"dinner");
        Response resp = given().spec(req)
                .body(payload)
                .post("/booking");

        resp.then().assertThat().spec(resp_spec_200);

        System.out.println(resp.asPrettyString());

        bookingID = resp.jsonPath().getInt("bookingid");

        System.out.println("Booking id " + bookingID);

    }

}
