package ftn.team23.controller;

import ftn.team23.dto.AccountDataDTO;
import ftn.team23.service.implementations.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping(value = "/guest")
public class GuestController {

    @Autowired
    private GuestService guestService;

    //@PostMapping(path = "/register", consumes = "application/json")
    @PostMapping(path = "/register")
    public ResponseEntity<AccountDataDTO> register(@RequestBody AccountDataDTO guestData) {
        AccountDataDTO result = guestService.register(guestData);
        if(result == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else
            return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //for later use, replace email with jwt logic, but this is what works now.
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String email) {
        guestService.deleteGuestByEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
