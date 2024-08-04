package org.lombok;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class BookingLombokPOJO {

    private String firstname;

    private String lastname;

    private int totalprice;

    private boolean depositpaid;
    private BookingDates bookingDates;
    private String additionalneeds;


    @Data
    @Builder
    @AllArgsConstructor
    public static class BookingDates {
        private String checkin;
        private String checkout;
    }
}
