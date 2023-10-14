package dev.cocoa.uspgymbooking.booking;

import dev.cocoa.uspgymbooking.user.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IBookingService {
    Booking createBooking(BookingFormDTO booking);

    List<Booking> getBookings();

    List<Booking> getBookingsByUser(User user);
    List<LocalTime> getAvailableTimes(Long facilityId, LocalDate date);

}
