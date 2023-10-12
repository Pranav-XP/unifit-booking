package dev.cocoa.uspgymbooking.facility;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/facilities")
public class FacilityController {
    @GetMapping("/all")
    public String displayAllFacilities(){
        return "facilities";
    }

    @GetMapping("/id")
    public String displayFacility(){
        return "facility";
    }

}
