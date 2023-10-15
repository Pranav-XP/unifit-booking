package dev.cocoa.uspgymbooking.booking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingNotification {
    private Long bookingId;
    private String customerName;
    private String facilityName;
}
