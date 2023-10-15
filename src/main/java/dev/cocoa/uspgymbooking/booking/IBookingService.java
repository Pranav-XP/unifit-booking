package dev.cocoa.uspgymbooking.booking;

import dev.cocoa.uspgymbooking.facility.FacilityStatus;
import dev.cocoa.uspgymbooking.user.User;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IBookingService {
    Booking createBooking(BookingFormDTO booking);

    Booking setMaintenance(BookingFormDTO booking);

    List<Booking> getBookingByStatus(BookingStatus status);
    List<Booking> getBookings();

    Booking getBooking(Long id);

    List<Booking> getBookingsByUser(User user);
    List<LocalTime> getAvailableTimes(Long facilityId, LocalDate date);

    Booking saveBooking(Booking booking);

    void cancelBookings(List<Booking> cancelledBookings);

    Page<Booking> getPaginated(int pageNo, int pageSize);
}
