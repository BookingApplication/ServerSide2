package ftn.team23.controller;

import ftn.team23.dto.UserRequest;
import ftn.team23.entities.Guest;
import ftn.team23.service.implementations.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/guest", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
public class GuestController {

    @Autowired
    private UserService userService;

    @GetMapping(path="/getAll")
    public void getAllGuests()
    {
        userService.findAllGuests();
    }

    @PostMapping(path = "/register")
    public ResponseEntity<UserRequest> signup(@RequestBody UserRequest guestData) {
        if(userService.IsEmailUniqueAcrossAllTables(guestData.getEmail())) {
            UserRequest result = userService.signupAsGuest(guestData);
            if (result == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else
                return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/verify/{email}/{code}")
    public void verifyEmail(@PathVariable String email, @PathVariable String code){
        userService.verifyGuest(email, code);
    }

    //todo: add reservations, delete guest if there are no active reservations
    //      delete host if there are no active reservations for any of the accommodations he owns,
    //      this removes all the accommodations of that owner
    @PreAuthorize("hasRole('GUEST')")
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteAccount() {
       boolean success = userService.deleteGuest();
       return new ResponseEntity<>(success,HttpStatus.OK);
    }

}
