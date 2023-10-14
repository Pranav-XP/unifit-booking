package dev.cocoa.uspgymbooking.booking;

import dev.cocoa.uspgymbooking.facility.Facility;
import dev.cocoa.uspgymbooking.facility.FacilityRepository;
import dev.cocoa.uspgymbooking.facility.FacilityService;
import dev.cocoa.uspgymbooking.user.User;
import dev.cocoa.uspgymbooking.user.UserRepository;
import dev.cocoa.uspgymbooking.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {
    private final BookingRepository bookingRepository;
    private final FacilityRepository facilityRepository;
    private final FacilityService facilityService;
    private final UserService userService;

    @Override
    public Booking createBooking(BookingFormDTO form) {
        Booking booking = new Booking();
        User user = userService.getUser(form.getUserId());
        booking.setUser(user);
        Facility facility = facilityService.getFacility(form.getFacilityId());
        booking.setFacility(facility);

        booking.setBookedDate(form.getBookedDate());
        booking.setStart(form.getStart());
        booking.setEnd(form.getEnd());
        booking.setStatus(BookingStatus.PENDING);

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> getBookingsByUser(User user) {
        return bookingRepository.findAllByUser(user);
    }

    @Override
    public List<LocalTime> getAvailableTimes(Long facilityId, LocalDate date) {
        LocalTime openingTime;
        LocalTime closingTime;

        // Determine if it's a weekday or weekend
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println("CHECKING AVAILABILITY FOR: " + dayOfWeek);
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            openingTime = facilityRepository.findWeekendOpeningTimeByFacilityId(facilityId);
            closingTime = facilityRepository.findWeekendClosingTimeByFacilityId(facilityId);
        } else {
            openingTime = facilityRepository.findWeekdayOpeningTimeByFacilityId(facilityId);
            closingTime = facilityRepository.findWeekdayClosingTimeByFacilityId(facilityId);
        }
        List<LocalTime> bookedTimes = bookingRepository.findBookedStartTimesByFacilityAndDate(facilityId, date);

        // Logic to calculate available times
        return calculateAvailableTimes(openingTime,closingTime, bookedTimes);
    }

    private List<LocalTime> calculateAvailableTimes(LocalTime openingTime,LocalTime facilityClosingTime, List<LocalTime> bookedTimes) {
        // Extract opening and closing times
        LocalTime currentTime = openingTime;
        LocalTime closingTime = facilityClosingTime;
        List<LocalTime> availableTimes = new ArrayList<>();


        while(currentTime.isBefore(closingTime)){
            availableTimes.add(currentTime);
            currentTime = currentTime.plusHours(1);
        }

        availableTimes.removeAll(bookedTimes);

        return availableTimes;

    }

}
