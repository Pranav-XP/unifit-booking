package dev.cocoa.uspgymbooking.facility;


import dev.cocoa.uspgymbooking.facility.facilitytype.FacilityType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacilityService implements IFacilityService {
    private final FacilityRepository facilityRepository;
    @Override
    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }

    @Override
    public Facility getFacility(Long id) {
            Optional<Facility> facility = facilityRepository.findById(id);
            return facility.get();
    }

    @Override
    public Facility addFacility(Facility facility) {
        facility.setStatus(FacilityStatus.AVAILABLE);
        return facilityRepository.save(facility);
    }

}
