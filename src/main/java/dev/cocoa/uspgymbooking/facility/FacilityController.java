package dev.cocoa.uspgymbooking.facility;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/facilities")
@RequiredArgsConstructor
public class FacilityController {

    private final FacilityService facilityService;

    @GetMapping("/all")
    public String displayAllFacilities(Model model){
       List<Facility> facilities = facilityService.getAllFacilities();
       model.addAttribute("facilities",facilities);
       return "facilities";
    }

    @GetMapping("/id")
    public String displayFacility(){
        return "facility";
    }

}
