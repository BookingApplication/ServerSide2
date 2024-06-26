package ftn.team23.controller;

import ftn.team23.dto.AccountDataDTO;
import ftn.team23.service.implementations.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping(value = "/host")
public class HostController {

    @Autowired
    private HostService hostService;

    @PostMapping(path = "/register")
    public ResponseEntity<AccountDataDTO> register(@RequestBody AccountDataDTO guestData) {
        AccountDataDTO result = hostService.register(guestData);
        if(result == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else
            return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String email) {
        hostService.deleteHostByEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
