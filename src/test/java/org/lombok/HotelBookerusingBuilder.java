package org.lombok;

import io.restassured.response.Response;
import org.requestspecification.Base;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HotelBookerusingBuilder extends Base {

    @Test
    public void getBooking(){

        BookingLombokPOJO booking=BookingLombokPOJO.builder().firstname("Venkat").lastname("Kannan")
                .totalprice(987).depositpaid(true).additionalneeds("Brunch").
                bookingDates(BookingLombokPOJO.BookingDates.builder().checkin("2024-08-03").checkout("2024-08-11").build()).build();

        Response resp = given().spec(req)
                .body(booking)
                .post("/booking");

        resp.then().assertThat().spec(resp_spec_200);
        System.out.println(resp.asPrettyString());
    }
}
