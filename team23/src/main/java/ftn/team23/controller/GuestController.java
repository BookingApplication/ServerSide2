package ftn.team23.controller;

import ftn.team23.dto.AccountDataDTO;
import ftn.team23.services.implementations.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @PostMapping("/register")
    public void register(@RequestBody AccountDataDTO guestData) {
        guestService.register(guestData);
    }

    @DeleteMapping("/delete/{email}")
    public void deleteAccount(@PathVariable String email) {
        guestService.deleteGuestByEmail(email);
    }
    //add return response.ok
//    @PutMapping("/update_account")
//    public void updateGuestByEmail(@RequestBody AccountDataDTO updatedGuestData) {
//        guestService.updateGuestAccount(updatedGuestData);
//    }
}