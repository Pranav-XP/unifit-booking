package dev.cocoa.uspgymbooking.facility.facilitytype;

import dev.cocoa.uspgymbooking.facility.Facility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacilityTypeService implements IFacilityTypeService {

    private final FacilityTypeRepository facilityTypeRepository;
    @Override
    public List<FacilityType> getAllFacilityTypes() {
        return facilityTypeRepository.findAll();
    }

    @Override
    public FacilityType getFacilityTypeById(Long id) {
        Optional<FacilityType> facilityType = facilityTypeRepository.findById(id);
        return facilityType.get();
    }

    @Override
    public void addFacilityType(FacilityType facilityType) {
        facilityTypeRepository.save(facilityType);
    }
}
