package ftn.team23.controller;

import ftn.team23.dto.AccommodationDTO;
import ftn.team23.dto.AccommodationWithImagesDTO;
import ftn.team23.service.interfaces.IAccommodationService;
import ftn.team23.service.interfaces.IUserService;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/administrator")
public class AdministratorController {
    @Autowired
    IUserService userService;

    @GetMapping(path = "/verify/{email}/{code}")
    public void verifyEmail(@PathVariable String email, @PathVariable String code){
        userService.verifyGuest(email, code);
    }
}
