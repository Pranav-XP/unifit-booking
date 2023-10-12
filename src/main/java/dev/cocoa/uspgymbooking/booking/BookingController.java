package dev.cocoa.uspgymbooking.booking;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/booking")
public class BookingController {
    @GetMapping
    public String bookingSummaryPage(){
        return "booking";
    }
}
