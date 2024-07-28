package org.requestspecification;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
import io.restassured.response.Response;
public class HotelBookerAPI extends Base {

    String token;

    int bookingID;

    @Test(priority = 0)
    public void createToken() {

        Response resp = given()
                .spec(req)
                .body("{\n"
                        + "    \"username\" : \"admin\",\n"
                        + "    \"password\" : \"password123\"\n"
                        + "}")
                .post("/auth");

        resp.then().assertThat().spec(resp_spec_200);

        token = resp.jsonPath().getString("token");

        System.out.println("Token Value " + token);

    }

    @Test(priority = 1)
    public void createBooking() {


        Response resp = given().spec(req)

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