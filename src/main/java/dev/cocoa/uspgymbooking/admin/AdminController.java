package dev.cocoa.uspgymbooking.admin;

import dev.cocoa.uspgymbooking.booking.Booking;
import dev.cocoa.uspgymbooking.booking.BookingService;
import dev.cocoa.uspgymbooking.facility.Facility;
import dev.cocoa.uspgymbooking.facility.FacilityService;
import dev.cocoa.uspgymbooking.facility.facilitytype.FacilityType;
import dev.cocoa.uspgymbooking.facility.facilitytype.FacilityTypeService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
    private final FacilityService facilityService;
    private final FacilityTypeService facilityTypeService;
    private final BookingService bookingService;


    @GetMapping
    public String adminHomePage(Model model){
        List<Booking> bookings = bookingService.getBookings();
        Map<String,Long> chartData = countBookingsByFacility(bookings);
        // Filter out past bookings
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusDays(7);

        List<Booking> sortedBookings  = bookings.stream()
                .filter(booking -> !booking.getBookedDate().isBefore(today))
                .filter(booking -> booking.getBookedDate().isBefore(nextWeek))
                .sorted(Comparator.comparing(Booking::getBookedDate))
                .collect(Collectors.toList());

        model.addAttribute("bookings",sortedBookings);
        model.addAttribute("chartData",chartData);
        return "admin/admin";
    }

    @GetMapping("/schedule")
    public String adminSchedulePage(){
        return "admin/admin-schedule";
    }

    @GetMapping("/facilities")
    public String adminFacilitiesPage(Model model){
        List<FacilityType> facilityTypes = facilityTypeService.getAllFacilityTypes();
        List<Facility> facilities = facilityService.getAllFacilities();
        model.addAttribute("facilities",facilities);
        model.addAttribute("facilityTypes",facilityTypes);
        return "admin/admin-facilities";
    }

    @GetMapping("/facilities/addtype")
    public String addTypeForm(Model model){
        FacilityType newFacilityType = new FacilityType();
        model.addAttribute("facilityType",newFacilityType);
        return "admin/add-facility-type";
    }
    @PostMapping("/facilities/addtype")
    public String addFacilityType(@ModelAttribute FacilityType newFacilityType){
        facilityTypeService.addFacilityType(newFacilityType);
        return "redirect:/admin/facilities";
    }

    @GetMapping("/facilities/add")
    public String addFacilityForm(Model model){
        Facility newFacility = new Facility();
        List<FacilityType> facilityType = facilityTypeService.getAllFacilityTypes();
        model.addAttribute("facility",newFacility);
        model.addAttribute("facilityTypes",facilityType);

        return "admin/add-facility";
    }

    @PostMapping("/facilities/add")
    public String addFacility(@ModelAttribute Facility facility, @RequestParam("facilityTypeId") Long facilityTypeId){
        FacilityType selected = facilityTypeService.getFacilityTypeById(facilityTypeId);
        facility.setFacilityType(selected);
        facilityService.addFacility(facility);
        return "redirect:/admin/facilities";
    }

    @GetMapping("/users")
    public String adminUsersPage(){
        return "admin/admin-users";
    }

    @GetMapping("/booking")
    public String allBookingsPage(Model model){
        return paginatedBookings(1,model);
    }

    @GetMapping("/booking/page/{pageNo}")
    public String paginatedBookings(@PathVariable("pageNo") int pageNo,Model model){
        int pageSize = 10;

        Page<Booking> page = bookingService.getPaginated(pageNo,pageSize);
        List<Booking> bookings = page.getContent();

        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("bookings",bookings);

        return "admin/admin-bookings";

    }

    @GetMapping("/booking/{id}")
    public String updateBookingPage(@PathVariable("id") Long bookingId, Model model) {
        Booking userBooking = bookingService.getBooking(bookingId);
        model.addAttribute("booking",userBooking);

        return "admin/update-booking";
    }

    @PostMapping("/booking/update")
    public String updateBookingStatus(@ModelAttribute("booking") Booking booking) {
        bookingService.saveBooking(booking);

        return "redirect:/admin/booking/"+booking.getId();
    }

    private Map<String,Long> countBookingsByFacility(List<Booking> bookings) {
        return bookings.stream()
                .collect(Collectors.groupingBy(
                        booking -> booking.getFacility().getName(),
                        Collectors.counting()
                ));
    }
}
