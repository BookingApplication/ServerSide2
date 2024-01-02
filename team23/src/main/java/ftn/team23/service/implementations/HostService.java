package ftn.team23.service.implementations;

import ftn.team23.dto.AccountDataDTO;
import ftn.team23.entities.Guest;
import ftn.team23.entities.Host;
import ftn.team23.entities.Role;
import ftn.team23.repositories.IGuestRepository;
import ftn.team23.repositories.IHostRepository;
import ftn.team23.service.interfaces.IHostService;
import ftn.team23.service.interfaces.ISendGridService;
import ftn.team23.service.interfaces.RoleService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
//@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HostService implements IHostService {
    @Autowired
    IHostRepository repository;

    @Autowired
    ISendGridService sendGridService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleService roleService;

    @Override
    public AccountDataDTO signup(AccountDataDTO hostData) {

        Optional<Host> found = repository.findByEmail(hostData.getEmail());
        if(found.isPresent()){
            return null;
        } else {
            String encryptedPassword =  passwordEncoder.encode(hostData.getPassword());
            Host hostToRegister = new Host(hostData.getEmail(), encryptedPassword, hostData.getName(), hostData.getSurname(), hostData.getLivingAddress(), hostData.getTelephoneNumber());
            List<Role> roles = roleService.findByName("ROLE_HOST");
            hostToRegister.setRoles(roles);
            try {
                Host result = repository.save(hostToRegister);
                repository.flush();
                AccountDataDTO a = new AccountDataDTO(result);
                return a;
            } catch (RuntimeException ex) {
                Throwable e = ex;
                Throwable c = null;
                while ((e != null) && !((c = ex.getCause()) instanceof ConstraintViolationException)) {
                    e = (RuntimeException) c;
                }
                if ((c != null) && (c instanceof ConstraintViolationException)) {
                    ConstraintViolationException c2 = (ConstraintViolationException) c;
                    Set<ConstraintViolation<?>> errors = c2.getConstraintViolations();
                    StringBuilder sb = new StringBuilder(1000);
                    for (ConstraintViolation<?> error : errors) {
                        sb.append(error.getMessage() + "\n");
                    }
                    throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, sb.toString());
                }
                throw ex;
            }
        }
    }

    public List<AccountDataDTO> findAllHosts()
    {
        List<Host> hosts = repository.findAll();
        if(hosts.isEmpty())
        {
            return null;
        }
        List<AccountDataDTO> allAccounts = new ArrayList<AccountDataDTO>();
        for (Host h: hosts) {
            AccountDataDTO accountData = new AccountDataDTO(h);
            allAccounts.add(accountData);
        }
        return allAccounts;
    }

    @Override
    public void deleteHostByEmail(String email) {
        Optional<Host> h = repository.findByEmail(email);
        if(h.isPresent()){
            repository.deleteById(h.get().getId());
        }
    }

    @Override
    public Host findHostByEmail(String email) {
        Optional<Host> h = repository.findByEmail(email);
        if(h.isPresent()){
            return h.get();
        }
        return null;
    }

    @Override
    public Host findById(Long userId) {
        Optional<Host> found = repository.findById(userId);
        if(found.isPresent()) {
            return found.get();
        }
        return null;
    }

    @Override
    public List<Host> findAll() {
        List<Host> hosts = repository.findAll();
        if(hosts.isEmpty()) {
            return null;
        }
        return hosts;
    }

    @Override
    public Host findByUsername(String username) {
        Optional<Host> found = repository.findByUsername(username);
        if(found.isPresent()){
            return found.get();
        }
        return null;
    }
}
