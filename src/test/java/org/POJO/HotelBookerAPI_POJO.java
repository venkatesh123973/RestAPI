package org.POJO;

import io.restassured.response.Response;
import org.requestspecification.Base;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HotelBookerAPI_POJO extends Base {
    String token;
    int bookingID;

    @Test(priority = 0)
    public void createtoken() {
        Authentication auth = new Authentication();
        auth.setUsername("admin");
        auth.setPassword("password123");

        Response resp = given()
                .spec(req)
                .body(auth)
                .post("/auth");

        resp.then().assertThat().spec(resp_spec_200);

        token = resp.jsonPath().getString("token");

        System.out.println("Token Value " + token);
    }

    @Test(priority = 1)
    public void createbooking() {
        BookingDates dates = new BookingDates();
        dates.setCheckin("2018-01-01");
        dates.setCheckout("2019-01-01");

        BookingPOJO payload = new BookingPOJO();
        payload.setFirstname("Jimone");
        payload.setLastname("Kim");
        payload.setTotalprice(112);
        payload.setDepositpaid(true);
        payload.setAdditionalneeds("Lunch");
        payload.setBookingdates(dates);

        Response resp = given().spec(req)

                .body(payload)
                .post("/booking");

        resp.then().assertThat().spec(resp_spec_200);

        System.out.println(resp.asPrettyString());

        bookingID = resp.jsonPath().getInt("bookingid");

        System.out.println("Booking id " + bookingID);

    }
}
