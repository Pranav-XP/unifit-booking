package dev.cocoa.uspgymbooking.authentication.registration;

import dev.cocoa.uspgymbooking.event.RegistrationCompleteEvent;
import dev.cocoa.uspgymbooking.exception.UserAlreadyExistException;
import dev.cocoa.uspgymbooking.user.IUserService;
import dev.cocoa.uspgymbooking.user.User;
import dev.cocoa.uspgymbooking.user.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final IUserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @GetMapping
    public String showRegistrationForm(Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user",userDTO);
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid UserDTO userDto){
        User registered = null;
        try {
            registered = userService.registerUser(userDto);
        } catch (UserAlreadyExistException e) {
            throw new RuntimeException(e);
        }
        //TODO:Publish Event to send Email
        applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(registered,""));
        return "redirect:/registration?success";
    }
}
