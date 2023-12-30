package ftn.team23.controller;

import ftn.team23.dto.AccountDataDTO;
import ftn.team23.dto.UserRequest;
import ftn.team23.entities.User;
import ftn.team23.exception.ResourceConflictException;
import ftn.team23.service.implementations.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin
@RestController
@RequestMapping(value = "/guest", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
public class GuestController {

    @Autowired
    private GuestService guestService;

    @GetMapping(path="/getAll")
    public void getAllGuests()
    {
        guestService.findAllGuests();
    }

    @PostMapping(path = "/register")
    public ResponseEntity<AccountDataDTO> register(@RequestBody AccountDataDTO guestData) {
        AccountDataDTO result = guestService.register(guestData);
        if(result == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else
            return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

//
//    // Endpoint za registraciju novog korisnika
//    @PostMapping("/signup")
//    public ResponseEntity<User> addUser(@RequestBody UserRequest userRequest, UriComponentsBuilder ucBuilder) {
//
//        User existUser = this.userService.findByUsername(userRequest.getEmail());
//
//        if (existUser != null) {
//            throw new ResourceConflictException(userRequest.getId(), "Username already exists");
//        }
//
//        User user = this.userService.save(userRequest);
//
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
//    }


    //for later use, replace email with jwt logic
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String email) {
        System.out.println("email received: " + email);
        guestService.deleteGuestByEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
