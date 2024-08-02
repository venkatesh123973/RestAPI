package org.serializationanddeserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.POJO.Authentication;
import org.POJO.BookingDates;
import org.POJO.BookingPOJO;
import org.requestspecification.Base;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Deserialization extends Base {

    @Test(priority = 0)
    public void createtoken() throws JsonProcessingException {
        Authentication auth = new Authentication();
        auth.setUsername("admin");
        auth.setPassword("password123");

        Response resp = given()
                .spec(req)
                .body(auth)
                .post("/auth");

        resp.then().assertThat().spec(resp_spec_200);

        ObjectMapper mapper = new ObjectMapper();
        tokenPOJO response=mapper.readValue(resp.getBody().asString(),tokenPOJO.class);
        System.out.println(response.getToken());
    }

    @Test
    public void createbooking() throws JsonProcessingException {
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

        ObjectMapper mapper = new ObjectMapper();
        BookingidPOJO response=mapper.readValue(resp.getBody().asString(),BookingidPOJO.class);
        System.out.println(response.getBookingid());
        System.out.println(response.getBooking().getFirstname());
        System.out.println(response.getBooking().getLastname());
        System.out.println(response.getBooking().getTotalprice());
        System.out.println(response.getBooking().getBookingdates().getCheckin());
    }

}
