package ftn.team23.controller;

import ftn.team23.dto.AccommodationDTO;
import ftn.team23.dto.AccommodationWithImagesDTO;
import ftn.team23.entities.Reservation;
import ftn.team23.service.interfaces.IAccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RestController
@RequestMapping(value = "/accommodation")
public class AccommodationController {

    @Autowired
    IAccommodationService service;

    @PreAuthorize("hasRole('HOST')")
    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void CreateAccommodation(@RequestPart("accommodation") AccommodationDTO accommodationDTO,
                                    @RequestPart("imageFile") MultipartFile[] multipartFiles){

        service.createAccommodation(accommodationDTO, multipartFiles);
    }

    @PreAuthorize("hasRole('HOST')")
    @PostMapping(value="/update", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void UpdateAccommodation(@RequestPart("accommodation") AccommodationDTO accommodationDTO,
                                    @RequestPart("imageFile") MultipartFile[] multipartFiles){
        service.updateAccommodationData(accommodationDTO, multipartFiles);
    }


    @GetMapping(value="/getDetails/{id}")
    public AccommodationWithImagesDTO GetAccommodationDetails(@PathVariable Long id){
        AccommodationWithImagesDTO result = service.getAccommodationDetails(id);
        return result;
    }


    @GetMapping(value = "/getAllAvailable")
    public ResponseEntity<Set<AccommodationWithImagesDTO>> GetAllAvailableAccommodations(){

        Set<AccommodationWithImagesDTO> accommodations = service.getAllAvailableAccommodations();
        if(accommodations!= null)
            return new ResponseEntity<>(accommodations, HttpStatus.OK);
        else
            return new ResponseEntity<>(accommodations, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/get-accommodations")
    public ResponseEntity<Set<AccommodationWithImagesDTO>> getAccommodationsWaitingOnApproval(){
        Set<AccommodationWithImagesDTO> accommodations = service.getAccommodationsWaitingOnApproval();

        return new ResponseEntity<>(accommodations, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value="/approve-accommodation")
    public ResponseEntity<Boolean> approveAccommodation(@RequestBody Long id){
        if (service.approveAccommodation(id))
        {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/deny-accommodation")
    public ResponseEntity<Boolean> denyAccommodation(@RequestBody Long id){
        if(service.denyAccommodation(id))
        {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }



}
