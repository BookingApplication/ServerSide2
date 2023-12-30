package ftn.team23.controller;


import ftn.team23.dto.LoggedInUserDTO;
import ftn.team23.dto.LoginDTO;
import ftn.team23.service.interfaces.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    ILoginService service;


    @PostMapping()
    public ResponseEntity<LoggedInUserDTO> login(@RequestBody LoginDTO loginDTO) {
        LoggedInUserDTO loggedInUserDTO = service.login(loginDTO.getEmail(), loginDTO.getPassword());
//        return new ResponseEntity<> (HttpStatus.OK);
        if(loggedInUserDTO != null)
            return new ResponseEntity<>(loggedInUserDTO, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Void> logout(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
