package ftn.team23.controller;


import ftn.team23.dto.LoggedInUserDTO;
import ftn.team23.dto.LoginDTO;
import ftn.team23.service.interfaces.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    ILoginService service;

//    @PostMapping("/login")
//    public ResponseEntity<UserTokenState> createAuthenticationToken(
//            @RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {
//        // Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
//        // AuthenticationException
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                authenticationRequest.getUsername(), authenticationRequest.getPassword()));
//
//        // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
//        // kontekst
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // Kreiraj token za tog korisnika
//        User user = (User) authentication.getPrincipal();
//        String jwt = tokenUtils.generateToken(user.getUsername());
//        int expiresIn = tokenUtils.getExpiredIn();
//
//        // Vrati token kao odgovor na uspesnu autentifikaciju
//        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
//    }

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
