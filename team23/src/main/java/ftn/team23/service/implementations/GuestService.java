package ftn.team23.service.implementations;

import ftn.team23.dto.AccountDataDTO;
import ftn.team23.dto.UserRequest;
import ftn.team23.entities.Guest;
import ftn.team23.entities.User;
import ftn.team23.repositories.IGuestRepository;
import ftn.team23.service.interfaces.IGuestService;
import ftn.team23.service.interfaces.ISendGridService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
//@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GuestService implements IGuestService {

    @Autowired
    IGuestRepository repository;

    @Autowired
    ISendGridService sendGridService;

    @Override
    public AccountDataDTO register(AccountDataDTO guestData) {

        Optional<Guest> found = repository.findByEmail(guestData.getEmail());
        if(found.isPresent()){
            return null;
        } else {
            Guest guestToRegister = new Guest(guestData.getEmail(), guestData.getPassword(), guestData.getName(), guestData.getSurname(), guestData.getLivingAddress(), guestData.getTelephoneNumber());
            try {
                Guest result = repository.save(guestToRegister);
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

    public List<AccountDataDTO> findAllGuests()
    {
        List<Guest> guests = repository.findAll();
        if(guests.isEmpty())
        {
            return null;
        }
        List<AccountDataDTO> allAccounts = new ArrayList<AccountDataDTO>();
        for (Guest g: guests) {
            AccountDataDTO accountData = new AccountDataDTO(g);
            allAccounts.add(accountData);
//            System.out.println(g.getEmail()+ g.getPassword()+g.getName()+ g.getSurname()+ g.getLivingAddress()+ g.getTelephoneNumber());
//            //testemail@example.comtestpasswordJohnDoe123 Main St, City123-456-7890
//            System.out.println(g.toString()); //ftn.team23.entities.Guest@6af12e6c
        }
        return allAccounts;
    }

    @Override
    public void deleteGuestByEmail(String email) {
        Optional<Guest> g = repository.findByEmail(email);
        if(g.isPresent()){
            repository.deleteById(g.get().getId());
        }
    }

    @Override
    public Guest findGuestByEmail(String email) {
        Optional<Guest> g = repository.findByEmail(email);
        if(g.isPresent()){
            return g.get();
        }
        return null;
    }

}
