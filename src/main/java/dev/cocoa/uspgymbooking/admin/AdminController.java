package dev.cocoa.uspgymbooking.admin;

import dev.cocoa.uspgymbooking.facility.Facility;
import dev.cocoa.uspgymbooking.facility.FacilityService;
import dev.cocoa.uspgymbooking.facility.facilitytype.FacilityType;
import dev.cocoa.uspgymbooking.facility.facilitytype.FacilityTypeService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
    private final FacilityService facilityService;
    private final FacilityTypeService facilityTypeService;


    @GetMapping
    public String adminHomePage(){
        return "admin/admin";
    }

    @GetMapping("/schedule")
    public String adminSchedulePage(){
        return "admin/admin-schedule";
    }

    @GetMapping("/facilities")
    public String adminFacilitiesPage(Model model){
        List<FacilityType> facilityTypes = facilityTypeService.getAllFacilityTypes();
        List<Facility> facilities = facilityService.getAllFacilities();
        model.addAttribute("facilities",facilities);
        model.addAttribute("facilityTypes",facilityTypes);
        return "admin/admin-facilities";
    }

    @GetMapping("/facilities/addtype")
    public String addTypeForm(Model model){
        FacilityType newFacilityType = new FacilityType();
        model.addAttribute("facilityType",newFacilityType);
        return "admin/add-facility-type";
    }
    @PostMapping("/facilities/addtype")
    public String addFacilityType(@ModelAttribute FacilityType newFacilityType){
        facilityTypeService.addFacilityType(newFacilityType);
        return "redirect:/admin/facilities";
    }

    @GetMapping("/facilities/add")
    public String addFacilityForm(Model model){
        Facility newFacility = new Facility();
        List<FacilityType> facilityType = facilityTypeService.getAllFacilityTypes();
        model.addAttribute("facility",newFacility);
        model.addAttribute("facilityTypes",facilityType);

        return "admin/add-facility";
    }

    @PostMapping("/facilities/add")
    public String addFacility(@ModelAttribute Facility facility, @RequestParam("facilityTypeId") Long facilityTypeId){
        FacilityType selected = facilityTypeService.getFacilityTypeById(facilityTypeId);
        facility.setFacilityType(selected);
        facilityService.addFacility(facility);
        return "redirect:/admin/facilities";
    }

    @GetMapping("/users")
    public String adminUsersPage(){
        return "admin/admin-users";
    }
}
