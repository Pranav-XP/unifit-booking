package dev.cocoa.uspgymbooking.viewcontrollers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ViewController {
    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("name","Pranav");
        return "index";
    }
}
