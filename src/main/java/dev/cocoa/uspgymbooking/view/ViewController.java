package dev.cocoa.uspgymbooking.view;


import dev.cocoa.uspgymbooking.user.User;
import dev.cocoa.uspgymbooking.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ViewController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public String homePage(Model model){
        model.addAttribute("name","Pranav");
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }


    @GetMapping("/user")
    public ModelAndView userPage(@AuthenticationPrincipal UserDetails authUser){
        ModelAndView page = new ModelAndView("user");
        User user = userRepository.findByEmail(authUser.getUsername());
        page.addObject("user",user);
        return page;
    }
}
