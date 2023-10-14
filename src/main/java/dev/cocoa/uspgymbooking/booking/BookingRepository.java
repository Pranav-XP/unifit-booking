package dev.cocoa.uspgymbooking.booking;

import dev.cocoa.uspgymbooking.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findAllByUser(User user);

    List<Booking> findAllByBookedDate(LocalDate date);
    @Query("SELECT b.start FROM Booking b WHERE b.bookedDate = :date")
    List<LocalTime> findBookedTimesByDate(@Param("date") LocalDate date);

    @Query("SELECT b.start FROM Booking b WHERE b.facility.id = :facilityId AND b.bookedDate = :date")
    List<LocalTime> findBookedStartTimesByFacilityAndDate(@Param("facilityId") Long facilityId, @Param("date") LocalDate date);

}
