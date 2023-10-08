package dev.cocoa.uspgymbooking.user;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")

public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(Model model){
        model.addAttribute("users",userService.getAllUsers());
        return "users";
    }

    @GetMapping("/showUserUpdateForm/{id}")
    public String updateUser(@PathVariable Long id,Model model){
        User user = userService.getUser(id);
        model.addAttribute("user",user);
        return "update-user";
    }

    @PostMapping("/saveUser")
    public String saveStudent(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/users";
    }
}
