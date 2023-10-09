package dev.cocoa.uspgymbooking.user;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    private final UserRepository userRepository;

    @GetMapping("/user")
    public ModelAndView userPage(@AuthenticationPrincipal UserDetails authUser){
        ModelAndView page = new ModelAndView("user");
        User user = userRepository.findByEmail(authUser.getUsername());
        page.addObject("user",user);
        return page;
    }


}
