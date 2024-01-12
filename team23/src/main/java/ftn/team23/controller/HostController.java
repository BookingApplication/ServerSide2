package ftn.team23.controller;

import ftn.team23.dto.UserRequest;
import ftn.team23.service.implementations.HostService;
import ftn.team23.service.implementations.UserService;
import ftn.team23.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/host", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
public class HostController {

    @Autowired
    private IUserService userService;

    @PostMapping(path = "/register")
    public ResponseEntity<UserRequest> signup(@RequestBody UserRequest userRequest) {
        if(userService.IsEmailUniqueAcrossAllTables(userRequest.getEmail())) {
            UserRequest result = userService.signupAsHost(userRequest);
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
        userService.verifyHost(email, code);
    }

    @PreAuthorize("hasRole('GUEST')")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAccount() {
        boolean succes = userService.deleteHost();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
