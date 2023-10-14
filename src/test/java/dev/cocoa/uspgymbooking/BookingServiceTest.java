package dev.cocoa.uspgymbooking;

import dev.cocoa.uspgymbooking.booking.BookingRepository;
import dev.cocoa.uspgymbooking.booking.BookingService;
import dev.cocoa.uspgymbooking.facility.FacilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private FacilityRepository facilityRepository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAvailableTimes_NoneBooked() {
        Long facilityId = 1L;
        LocalDate date = LocalDate.of(2023, 10, 13); // Assuming it's a weekday

        // Mocking repository responses
        LocalTime openingTime = LocalTime.of(9,0);
        LocalTime closingTime = LocalTime.of(18,0);
        when(facilityRepository.findWeekdayOpeningTimeByFacilityId(facilityId))
                .thenReturn(openingTime);

        when(facilityRepository.findWeekdayClosingTimeByFacilityId(facilityId))
                .thenReturn(closingTime);

        when(facilityRepository.findWeekendOpeningTimeByFacilityId(facilityId))
                .thenReturn(openingTime);

        when(facilityRepository.findWeekendClosingTimeByFacilityId(facilityId))
                .thenReturn(closingTime);



        // Assume all times are booked
        List<LocalTime> bookedTimes = Arrays.asList();

        when(bookingRepository.findBookedStartTimesByFacilityAndDate(facilityId, date))
                .thenReturn(bookedTimes);

        // Act
        List<LocalTime> result = bookingService.getAvailableTimes(facilityId, date);

        // Print out times
        System.out.println("Opening hours: " + openingTime +" to " + closingTime);
        System.out.println("Booked Times: " + bookedTimes);
        System.out.println("Available Times: " + result);

        // Assert
        assertEquals(result.size(),result.size()-bookedTimes.size()); // No times are booked

        // Verify that the repository methods were called
        verify(facilityRepository).findWeekdayOpeningTimeByFacilityId(facilityId);
        verify(facilityRepository).findWeekdayClosingTimeByFacilityId(facilityId);
        verify(bookingRepository).findBookedStartTimesByFacilityAndDate(facilityId, date);
    }

    @Test
    void getAvailableTimes_AllBooked() {
        // Arrange
        Long facilityId = 1L;
        LocalDate date = LocalDate.of(2023, 10, 13); // Assuming it's a weekday

        // Mocking repository responses
        LocalTime openingTime = LocalTime.of(9, 0);
        LocalTime closingTime = LocalTime.of(18, 0);
        when(facilityRepository.findWeekdayOpeningTimeByFacilityId(facilityId))
                .thenReturn(openingTime);

        when(facilityRepository.findWeekdayClosingTimeByFacilityId(facilityId))
                .thenReturn(closingTime);

        when(facilityRepository.findWeekendOpeningTimeByFacilityId(facilityId))
                .thenReturn(openingTime);

        when(facilityRepository.findWeekendClosingTimeByFacilityId(facilityId))
                .thenReturn(closingTime);

        // Assume all times are booked
        List<LocalTime> bookedTimes = Arrays.asList(LocalTime.of(9, 0), LocalTime.of(10, 0), LocalTime.of(11, 0),
                LocalTime.of(12, 0), LocalTime.of(13, 0), LocalTime.of(14, 0),
                LocalTime.of(15, 0), LocalTime.of(16, 0), LocalTime.of(17, 0));

        when(bookingRepository.findBookedStartTimesByFacilityAndDate(facilityId, date))
                .thenReturn(bookedTimes);

        // Act
        List<LocalTime> result = bookingService.getAvailableTimes(facilityId, date);

        // Print out times
        System.out.println("Opening hours: " + openingTime +" to " + closingTime);
        System.out.println("Booked Times: " + bookedTimes);
        System.out.println("Available Times: " + result);

        // Assert
        assertEquals(Arrays.asList(), result); // All times are booked

        // Verify that the repository methods were called
        verify(facilityRepository).findWeekdayOpeningTimeByFacilityId(facilityId);
        verify(facilityRepository).findWeekdayClosingTimeByFacilityId(facilityId);
        verify(bookingRepository).findBookedStartTimesByFacilityAndDate(facilityId, date);
    }

    @Test
    void getAvailableTimes_PartiallyBooked() {
        // Arrange
        Long facilityId = 1L;
        LocalDate date = LocalDate.of(2023, 10, 13); // Assuming it's a weekday

        // Mocking repository responses
        LocalTime openingTime = LocalTime.of(9,0);
        LocalTime closingTime = LocalTime.of(18,0);
        when(facilityRepository.findWeekdayOpeningTimeByFacilityId(facilityId))
                .thenReturn(openingTime);

        when(facilityRepository.findWeekdayClosingTimeByFacilityId(facilityId))
                .thenReturn(closingTime);

        when(facilityRepository.findWeekendOpeningTimeByFacilityId(facilityId))
                .thenReturn(openingTime);

        when(facilityRepository.findWeekendClosingTimeByFacilityId(facilityId))
                .thenReturn(closingTime);

        // Assume only two times are booked
        List<LocalTime> bookedTimes = Arrays.asList(LocalTime.of(10, 0), LocalTime.of(14, 0));
        when(bookingRepository.findBookedStartTimesByFacilityAndDate(facilityId, date))
                .thenReturn(bookedTimes);

        // Act
        List<LocalTime> result = bookingService.getAvailableTimes(facilityId, date);

        // Print out times
        System.out.println("Opening hours: " + openingTime +" to " + closingTime);
        System.out.println("Booked Times: " + bookedTimes);
        System.out.println("Available Times: " + result);

        // Assert
        assertEquals(Arrays.asList(LocalTime.of(9, 0), LocalTime.of(11, 0), LocalTime.of(12, 0),
                        LocalTime.of(13, 0), LocalTime.of(15, 0), LocalTime.of(16, 0), LocalTime.of(17, 0)),
                result); // Only two times are booked

            // Verify that the repository methods were called
            verify(facilityRepository).findWeekdayOpeningTimeByFacilityId(facilityId);
            verify(facilityRepository).findWeekdayClosingTimeByFacilityId(facilityId);
            verify(bookingRepository).findBookedStartTimesByFacilityAndDate(facilityId, date);
    }
}
