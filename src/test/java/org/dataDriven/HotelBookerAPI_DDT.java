package org.dataDriven;

import io.restassured.response.Response;
import org.POJO.BookingDates;
import org.POJO.BookingPOJO;
import org.requestspecification.Base;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HotelBookerAPI_DDT extends Base {
    int bookingID;

    @Test(dataProvider = "createUserPayload")
    public void createbooking(String fname, String lname, int totalprice, boolean depositpaid, String additionalneeds, String checkin, String checkout) {
        BookingDates dates = new BookingDates();
        dates.setCheckin(checkin);
        dates.setCheckout(checkout);

        BookingPOJO payload = new BookingPOJO();
        payload.setFirstname(fname);
        payload.setLastname(lname);
        payload.setTotalprice(totalprice);
        payload.setDepositpaid(depositpaid);
        payload.setAdditionalneeds(additionalneeds);
        payload.setBookingdates(dates);

        Response resp = given().spec(req)

                .body(payload).log().all()
                .post("/booking");

        resp.then().assertThat().spec(resp_spec_200);

        System.out.println(resp.asPrettyString());

        bookingID = resp.jsonPath().getInt("bookingid");

        System.out.println("Booking id " + bookingID);

    }

    @DataProvider(name = "createUserPayload")
    public Object[][] getDataForAPI() {
        System.out.println("Data Provided ");

        Object[][] arr = new Object[3][7];

        arr[0][0] = "Lokesh";

        arr[0][1] = "Kannappan";

        arr[0][2] = 234;

        arr[0][3] = false;

        arr[0][4] = "2024-09-01";

        arr[0][5] = "2024-10-01";

        arr[0][6] = "Breakfast";


        arr[1][0] = "Haitha";

        arr[1][1] = "Kumari";

        arr[1][2] = 777;

        arr[1][3] = false;

        arr[1][4] = "2025-09-01";

        arr[1][5] = "2025-10-01";

        arr[1][6] = "Lunch";


        arr[2][0] = "Swarupa";

        arr[2][1] = "Deshpande";

        arr[2][2] = 888;

        arr[2][3] = true;

        arr[2][4] = "2025-01-01";

        arr[2][5] = "2025-02-01";

        arr[2][6] = "Dinner";

        return arr;
    }

}
