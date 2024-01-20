package ftn.team23.service.implementations;

import ftn.team23.dto.UserRequest;
import ftn.team23.entities.Guest;
import ftn.team23.entities.Role;
import ftn.team23.repositories.IGuestRepository;
import ftn.team23.service.interfaces.IGuestService;
import ftn.team23.service.interfaces.RoleService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.*;

@Service
//@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GuestService implements IGuestService {

    @Autowired
    IGuestRepository guestRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleService roleService;



}
