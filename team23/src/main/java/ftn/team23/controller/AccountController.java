package ftn.team23.controller;

import ftn.team23.dto.AccountDataDTO;
import ftn.team23.service.implementations.AccountService;
import ftn.team23.service.interfaces.IAccountService;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    IAccountService service;

    @GetMapping(value = "/{email}")
    ResponseEntity<AccountDataDTO> getAccountData(@PathVariable String email)
    {
       AccountDataDTO result = service.getAccountData(email);
       if (result == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<AccountDataDTO> updateAccountData(AccountDataDTO data){
        AccountDataDTO result = service.updateAccountData(data);
        if (result == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
