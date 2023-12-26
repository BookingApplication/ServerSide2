package ftn.team23.service.implementations;

import ftn.team23.dto.AccountDataDTO;
import ftn.team23.entities.Guest;
import ftn.team23.repositories.IGuestRepository;
import ftn.team23.service.interfaces.IGuestService;
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

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
//@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GuestService implements IGuestService {

    @Autowired
    IGuestRepository repository;

    @Override
    public AccountDataDTO register(AccountDataDTO guestData) {
//        Guest found = repository.findByEmail(guestData.getEmail());
//        Guest found = repository.findGuestByEmail(guestData.getEmail());
        Optional<Guest> found = repository.findByEmail(guestData.getEmail());
        if(found.isPresent()){
            return null;
        } else {
            try {
                Guest guestToRegister = new Guest(guestData.getEmail(), guestData.getPassword(), guestData.getName(), guestData.getSurname(), guestData.getLivingAddress(), guestData.getTelephoneNumber());
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

    public void findAllGuests()
    {
        List<Guest> guests = repository.findAll();
        if(guests != null)
        {
            System.out.println("Success on horizon.");

        }
        System.out.println("Here.");
        System.out.println(guests);
        System.out.println(guests.size());
        for (Guest g: guests) {
            System.out.println(g.getEmail()+ g.getPassword()+g.getName()+ g.getSurname()+ g.getLivingAddress()+ g.getTelephoneNumber());
            //testemail@example.comtestpasswordJohnDoe123 Main St, City123-456-7890
            System.out.println(g.getId());
            System.out.println(g.toString()); //ftn.team23.entities.Guest@6af12e6c
//            System.out.println(g.accommodations)

        }
    }


    @Override
    public void deleteGuestByEmail(String email) {
//        Guest g = repository.findByEmail(email);
//        Guest g = repository.findGuestByEmail(email);
        Optional<Guest> g = repository.findByEmail(email);
        System.out.println("found guest issss: " + g);

        if(g.isPresent()){
            repository.deleteById(g.get().getId());
            System.out.println("here in delete section");
        }
    }

    @Override
    public Guest findGuestByEmail(String email) {
        return null;
    }

    @Override
    public boolean findByEmailAndPassword(String email, String password) {
        return false;
    }

//    @Override
//    public Guest findGuestByEmail(String email) {
//        return repository.findByEmail(email);
//    }

//    @Override
//    public boolean findByEmailAndPassword(String email, String password) {
//        Guest g = repository.findByEmail(email);
//        if(g!=null)
//            return g.getPassword().equals( password);
//        else return false;
//    }
}
