package dev.cocoa.uspgymbooking.user;


import dev.cocoa.uspgymbooking.booking.Booking;
import dev.cocoa.uspgymbooking.booking.BookingRepository;
import dev.cocoa.uspgymbooking.booking.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final BookingService bookingService;

    @GetMapping("/user")
    public ModelAndView userPage(@AuthenticationPrincipal UserDetails authUser){
        ModelAndView page = new ModelAndView("user");
        User user = userRepository.findByEmail(authUser.getUsername());
        List<Booking> bookings = bookingService.getBookingsByUser(user);
        page.addObject("user",user);
        page.addObject("bookings",bookings);
        return page;
    }


}
