package ftn.team23.controller;

import ftn.team23.dto.UserRequest;
import ftn.team23.entities.Guest;
import ftn.team23.service.implementations.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@CrossOrigin
@RestController
@RequestMapping(value = "/guest", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
public class GuestController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/getAll")
    public void getAllGuests() {
        userService.findAllGuests();
    }

    @PostMapping(path = "/register")
    public ResponseEntity<UserRequest> signup(@RequestBody UserRequest userRequest) {
        if (userService.IsEmailUniqueAcrossAllTables(userRequest.getEmail())) {
            UserRequest result = userService.signupAsGuest(userRequest);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with that email already exists.");
    }

    @GetMapping(path = "/verify")
    public void verifyEmail(@Param("code") String code) {
        userService.verifyGuest(code);
    }

    //todo: add reservations, delete guest if there are no active reservations
    //      delete host if there are no active reservations for any of the accommodations he owns,
    //      this removes all the accommodations of that owner
    @PreAuthorize("hasRole('GUEST')")
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteAccount() {
        boolean success = userService.deleteGuest();
        return new ResponseEntity<>(success, HttpStatus.OK);
    }
}
