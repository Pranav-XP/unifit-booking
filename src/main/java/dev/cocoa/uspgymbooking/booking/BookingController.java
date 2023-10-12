package dev.cocoa.uspgymbooking.booking;

import dev.cocoa.uspgymbooking.facility.Facility;
import dev.cocoa.uspgymbooking.facility.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    private final FacilityService facilityService;
    @GetMapping
    public String bookingSummaryPage(Model model){
        List<Facility> facilities = facilityService.getAllFacilities();
        model.addAttribute("facilities",facilities);
        return "booking";
    }

    @GetMapping("/{id}")
    public String bookingForm(Model model, @PathVariable("id") Long id){
        Facility facility = facilityService.getFacility(id);

        Booking newBooking = new Booking();
        model.addAttribute("booking",newBooking);
        return "add-booking";
    }
}
