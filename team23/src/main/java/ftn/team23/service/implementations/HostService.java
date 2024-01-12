package ftn.team23.service.implementations;

import ftn.team23.dto.UserRequest;
import ftn.team23.entities.Guest;
import ftn.team23.entities.Host;
import ftn.team23.entities.Role;
import ftn.team23.repositories.IHostRepository;
import ftn.team23.service.interfaces.IHostService;
import ftn.team23.service.interfaces.ISendGridService;
import ftn.team23.service.interfaces.RoleService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
//@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HostService implements IHostService {
    @Autowired
    IHostRepository hostRepository;
    @Autowired
    ISendGridService sendGridService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleService roleService;

}
