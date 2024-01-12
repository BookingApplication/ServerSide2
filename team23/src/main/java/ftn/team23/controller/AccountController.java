package ftn.team23.controller;

import ftn.team23.dto.UserRequest;
import ftn.team23.entities.Guest;
import ftn.team23.entities.Host;
import ftn.team23.entities.User;
import ftn.team23.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    @Autowired
    IUserService service;

    @GetMapping(value = "/get")
    @PreAuthorize("hasAnyRole('GUEST','HOST','ADMIN')")
    ResponseEntity<UserRequest> getAccountData()
    {
        UserRequest result = service.getAccountData();
        if (result == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value="/update")
    @PreAuthorize("hasAnyRole('GUEST','HOST','ADMIN')")
    ResponseEntity<UserRequest> updateAccount(@RequestBody UserRequest data){
        if(!SecurityContextHolder.getContext().getAuthentication().getName().equals(data.getEmail())
                && !service.IsEmailUniqueAcrossAllTables(data.getEmail())) {
            //ako je email izmenjen, a takav vec postoji u sistemu
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            UserRequest result = service.updateAccount(data);
            if (result == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

}
