package ftn.team23.controller;

import ftn.team23.dto.LoggedInUserDTO;
import ftn.team23.dto.LoginDTO;
import ftn.team23.entities.UserData;
import ftn.team23.services.interfaces.ILoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    ILoginService service;

    @PostMapping()
    public LoggedInUserDTO login(@RequestBody LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();

        if (service.isValidLogin(email, password)) {
            UserData userData = service.getUserByEmail(email);
            return new LoggedInUserDTO(userData.getName(), userData.getSurname(), loginDTO.getEmail());
        }
        return null;
    }
}
