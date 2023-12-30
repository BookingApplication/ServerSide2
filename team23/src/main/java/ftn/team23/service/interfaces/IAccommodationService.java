package ftn.team23.service.interfaces;

import ftn.team23.dto.AccommodationDTO;

public interface IAccommodationService {
    void createAccommodation(AccommodationDTO accommodationDetails);
    void deleteAccommodation(Long id);
    void updateAccommodationData(AccommodationDTO updatedAccommodationDetails);
}
