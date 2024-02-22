package ftn.team23.controller;

import ftn.team23.dto.AccommodationDTO;
import ftn.team23.dto.AccommodationWithImagesDTO;
import ftn.team23.dto.SearchedAccommodationDTO;
import ftn.team23.service.interfaces.IAccommodationService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping(value = "/accommodation")
public class AccommodationController {

    @Autowired
    IAccommodationService service;

    @PreAuthorize("hasRole('HOST')")
    @PostMapping(value = "/create/{email}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void CreateAccommodation(@RequestPart("accommodation") AccommodationDTO accommodationDTO,
                                    @RequestPart("imageFile") MultipartFile[] multipartFiles,
                                    @PathVariable String email) {

        service.createAccommodation(accommodationDTO, multipartFiles, email);
    }

    @PreAuthorize("hasRole('HOST')")
    @PostMapping(value = "/update", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void UpdateAccommodation(@RequestPart("accommodation") AccommodationDTO accommodationDTO,
                                    @RequestPart("imageFile") MultipartFile[] multipartFiles) {
        service.updateAccommodationData(accommodationDTO, multipartFiles);
    }


    @GetMapping(value = "/getDetails/{id}")
    public AccommodationWithImagesDTO GetAccommodationDetails(@PathVariable Long id) {
        AccommodationWithImagesDTO result = service.getAccommodationDetails(id);
        return result;
    }


    @GetMapping(value = "/getAllAvailable")
    public ResponseEntity<Set<AccommodationWithImagesDTO>> GetAllAvailableAccommodations() {

        Set<AccommodationWithImagesDTO> accommodations = service.getAllAvailableAccommodations();
        if (accommodations != null)
            return new ResponseEntity<>(accommodations, HttpStatus.OK);
        else
            return new ResponseEntity<>(accommodations, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/get-accommodations")
    public ResponseEntity<Set<AccommodationWithImagesDTO>> getAccommodationsWaitingOnApproval() {
        Set<AccommodationWithImagesDTO> accommodations = service.getAccommodationsWaitingOnApproval();

        return new ResponseEntity<>(accommodations, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/approve-accommodation")
    public ResponseEntity<Boolean> approveAccommodation(@RequestBody Long id) {
        if (service.approveAccommodation(id)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/deny-accommodation")
    public ResponseEntity<Boolean> denyAccommodation(@RequestBody Long id) {
        if (service.denyAccommodation(id)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }


    @GetMapping(value = "/get-by-search-criteria")
    public ResponseEntity<List<SearchedAccommodationDTO>> getAccommodationsBySearchCriteria(
            @RequestParam(value = "location", required = false) Optional<String> location,
            @RequestParam(value = "numberOfGuests", required = false) Optional<Integer> numberOfGuests,
            @RequestParam(value = "startDate", required = false) Optional<Long> startDate,
            @RequestParam(value = "endDate", required = false) Optional<Long> endDate,
            @RequestParam(value = "amenities", required = false) Optional<Collection<String>> amenities,
            @RequestParam(value = "accommodationType", required = false) Optional<String> accommodationType,
            @RequestParam(value = "minPrice", required = false) Optional<Double> minPrice,
            @RequestParam(value = "maxPrice", required = false) Optional<Double> maxPrice) {

        List<SearchedAccommodationDTO> searchedObjects = service.getSearchedAccommodations(location.orElse(" "),
                numberOfGuests.orElse(null),
                startDate.orElse(null),
                endDate.orElse(null),
                amenities.orElse(null),
                accommodationType.orElse(null),
                minPrice.orElse(null),
                maxPrice.orElse(null));

        return new ResponseEntity<>(searchedObjects, HttpStatus.OK);
        //todo return images
    }


    @GetMapping(value = "/get-filtered")
    public ResponseEntity<List<SearchedAccommodationDTO>> getAccommodationsByFilterCriteria(
            @RequestParam(value = "amenities", required = false) Optional<Collection<String>> amenities,
            @RequestParam(value = "accommodationType", required = false) Optional<String> accommodationType,
            @RequestParam(value = "minPrice", required = false) Optional<Double> minPrice,
            @RequestParam(value = "maxPrice", required = false) Optional<Double> maxPrice) {

        List<SearchedAccommodationDTO> filteredObjects = service.getFilteredAccommodations(amenities.orElse(null),
                accommodationType.orElse(null),
                minPrice.orElse(null),
                maxPrice.orElse(null));

        return new ResponseEntity<>(filteredObjects, HttpStatus.OK);
    }


}
