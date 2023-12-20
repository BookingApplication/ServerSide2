package ftn.team23.controller;

import ftn.team23.entities.Guest;
//import ftn.team23.services.interfaces.IGuestRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guest/register")
public class GuestRegistrationController {

    @Autowired
    //IGuestRegistrationService service;

    @PostMapping
    public void register(@RequestBody Guest g) {
        //service.register(g);
    }
}
