package ftn.team23.service.implementations;

import ftn.team23.dto.AccountDataDTO;
import ftn.team23.entities.Guest;
import ftn.team23.entities.Host;
import ftn.team23.repositories.IHostRepository;
import ftn.team23.service.interfaces.IHostService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
//@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HostService implements IHostService {
    @Autowired
    IHostRepository repository;

    @Override
    public AccountDataDTO register(AccountDataDTO hostData) {
//        Host h = repository.findByEmail(guestData.getEmail());
        Host h = repository.findHostByEmail(hostData.getEmail());
        if(h != null) {
            return null;
        }
        else {
            try {
                Host host = repository.save(h);
                repository.flush();
                AccountDataDTO a = new AccountDataDTO(host) ;
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

    @Override
    public void deleteHostByEmail(String email) {

    }

    @Override
    public Host findHostByEmail(String email) {
        return null;
    }

    @Override
    public boolean findByEmailAndPassword(String email, String password) {
        return false;
    }

//    @Override
//    public void deleteHostByEmail(String email) {
//        Host g = repository.findByEmail(email);
//        if(g!=null){
//            repository.deleteById(g.getId());
//        }
//    }
//
//    @Override
//    public Host findHostByEmail(String email) {
//        return repository.findByEmail(email);
//    }
//
//    @Override
//    public boolean findByEmailAndPassword(String email, String password) {
//        Host g = repository.findByEmail(email);
//        if(g!=null)
//            return g.getPassword().equals( password);
//        else return false;
//    }
}
