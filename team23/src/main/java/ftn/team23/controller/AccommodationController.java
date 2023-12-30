package ftn.team23.controller;

import ftn.team23.dto.AccommodationDTO;
import ftn.team23.dto.AccountDataDTO;
import ftn.team23.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/accommodation", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccommodationController {

//    @Autowired
//    IAccommodationService service;
//
//    @PostMapping(value = "/create")
//    void CreateAccommodation(@RequestBody AccommodationDTO accommodationDTO){
//        service.createAccommodation(accommodationDTO);
//    }


}
