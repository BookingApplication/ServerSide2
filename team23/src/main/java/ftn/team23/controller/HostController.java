package ftn.team23.controller;

import ftn.team23.dto.UserRequest;
import ftn.team23.service.implementations.HostService;
import ftn.team23.service.implementations.UserService;
import ftn.team23.service.interfaces.IUserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(value = "/host", produces = MediaType.APPLICATION_JSON_VALUE)
public class HostController {

    @Autowired
    private IUserService userService;

    @PostMapping(path = "/register", consumes = "text/html")
    public ResponseEntity<UserRequest> signup(@RequestBody UserRequest userRequest, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
        if(userService.IsEmailUniqueAcrossAllTables(userRequest.getEmail())) {
            UserRequest result = userService.signupAsHost(userRequest, getSiteURL(request));
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with that email already exists.");
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping(path = "/verify")
    public void verifyEmail(@Param("code") String code){
        userService.verifyHost(code);
    }

    @PreAuthorize("hasRole('HOST')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        String result = userService.deleteHost(id);
        if (result.equals("Host does not exist.")) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else if (result.equals("Unable to delete account. There are still active reservations.")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
