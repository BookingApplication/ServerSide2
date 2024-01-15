package ftn.team23.service.interfaces;

import ftn.team23.dto.AccommodationDTO;
import ftn.team23.dto.AccommodationWithImagesDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface IAccommodationService {
    void createAccommodation(AccommodationDTO accommodationDetails, MultipartFile[] multipartFiles);

    void updateAccommodationData(AccommodationDTO updatedAccommodationDetails, MultipartFile[] multipartFiles);

    void deleteAccommodation(Long id);

    Set<AccommodationWithImagesDTO> getAccommodationsWaitingOnApproval();

    Set<AccommodationWithImagesDTO> getAllAvailableAccommodations();

    Boolean approveAccommodation(Long id);

    Boolean denyAccommodation(Long id);

    AccommodationWithImagesDTO getAccommodationDetails(Long id);
}
