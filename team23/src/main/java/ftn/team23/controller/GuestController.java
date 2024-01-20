package ftn.team23.controller;

import ftn.team23.dto.UserRequest;
import ftn.team23.entities.Guest;
import ftn.team23.service.implementations.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.UnsupportedEncodingException;

//import javax.mail.MessagingException;
//import java.io.UnsupportedEncodingException;

@CrossOrigin
@RestController
@RequestMapping(value = "/guest", produces = MediaType.APPLICATION_JSON_VALUE)
public class GuestController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/getAll")
    public void getAllGuests() {
        userService.findAllGuests();
    }

    @PostMapping(path = "/register")
    public ResponseEntity<UserRequest> signup(@RequestBody UserRequest userRequest, HttpServletRequest request)  throws UnsupportedEncodingException, MessagingException {
        if (userService.IsEmailUniqueAcrossAllTables(userRequest.getEmail())) {
            UserRequest result = userService.signupAsGuest(userRequest, getSiteURL(request));
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with that email already exists.");
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping(path = "/verify")
    public void verifyEmail(@Param("code") String code) {
        userService.verifyGuest(code);
    }

    @PreAuthorize("hasRole('GUEST')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        String result = userService.deleteGuest(id);
        if(result.equals("Guest does not exist."))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if(result.equals("Unable to delete guest. There are still active reservations."))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
