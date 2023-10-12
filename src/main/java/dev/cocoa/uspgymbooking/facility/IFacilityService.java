package dev.cocoa.uspgymbooking.facility;

import dev.cocoa.uspgymbooking.facility.facilitytype.FacilityType;

import java.util.List;

public interface IFacilityService {
    List<Facility> getAllFacilities();

    Facility getFacility(Long id);
    Facility addFacility(Facility facility);
}
