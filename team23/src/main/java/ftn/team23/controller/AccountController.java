package ftn.team23.controller;

import ftn.team23.dto.UserRequest;
import ftn.team23.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @PostMapping("/{userId}/profile-picture")
    public ResponseEntity<String> uploadProfilePicture(
            @PathVariable Long userId,
            @RequestParam("image") MultipartFile imageFile) throws IOException {

        service.uploadProfileImage(userId, imageFile);

        return new ResponseEntity<>("Profile picture uploaded successfully", HttpStatus.OK);

    }

    @GetMapping(value= "/{userId}/profile-picture", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]>  getImageWithMediaType(@PathVariable Long userId) throws IOException {

        return new ResponseEntity<>(service.getProfileImage(userId), HttpStatus.OK);
    }


}
