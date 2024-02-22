package ftn.team23.service.interfaces;

import ftn.team23.dto.AccommodationDTO;
import ftn.team23.dto.AccommodationWithImagesDTO;
import ftn.team23.dto.SearchedAccommodationDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface IAccommodationService {
    void createAccommodation(AccommodationDTO accommodationDetails, MultipartFile[] multipartFiles, String email);

    void updateAccommodationData(AccommodationDTO updatedAccommodationDetails, MultipartFile[] multipartFiles);

    void deleteAccommodation(Long id);

    Set<AccommodationWithImagesDTO> getAccommodationsWaitingOnApproval();

    Set<AccommodationWithImagesDTO> getAllAvailableAccommodations();

    Boolean approveAccommodation(Long id);

    Boolean denyAccommodation(Long id);

    AccommodationWithImagesDTO getAccommodationDetails(Long id);

    List<SearchedAccommodationDTO> getSearchedAccommodations(String location, Integer numberOfGuests, Long startDate,
                                                             Long endDate, Collection<String> amenities, String accommodationType,
                                                             Double minPrice, Double maxPrice);

    List<SearchedAccommodationDTO> getFilteredAccommodations(Collection<String> amenities, String accommodationType,
                                                             Double minPrice, Double maxPrice);
}
