package ftn.team23.controller;


import ftn.team23.dto.LoggedInUserDTO;
import ftn.team23.dto.LoginDTO;
import ftn.team23.entities.UserData;
import ftn.team23.service.interfaces.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    ILoginService service;

    @PostMapping()
    public LoggedInUserDTO login(@RequestBody LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();

        return service.login(email, password);
//        if (service.isValidLogin(email, password)) {
//            UserData userData = service.getUserByEmail(email);
//            return new LoggedInUserDTO(userData.getName(), userData.getSurname(), loginDTO.getEmail());
//        }
//        return null;
//        return new LoggedInUserDTO("pera", "peric", "testmail@gmail.com");
    }
}
