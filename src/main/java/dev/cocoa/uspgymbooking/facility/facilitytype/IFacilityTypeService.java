package dev.cocoa.uspgymbooking.facility.facilitytype;

import java.util.List;

public interface IFacilityTypeService {
    List<FacilityType> getAllFacilityTypes();

    FacilityType getFacilityTypeById(Long id);
    void addFacilityType(FacilityType facilityType);

}
