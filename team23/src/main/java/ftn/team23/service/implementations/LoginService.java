package ftn.team23.service.implementations;

import ftn.team23.dto.LoggedInUserDTO;
import ftn.team23.entities.Guest;
import ftn.team23.entities.Host;
import ftn.team23.entities.User;
import ftn.team23.service.interfaces.IGuestService;
import ftn.team23.service.interfaces.IHostService;
import ftn.team23.service.interfaces.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginService implements ILoginService {

    @Autowired
    IGuestService guestService;
    @Autowired
    IHostService hostService;

    //check if this combination of email+pass exists
    @Override
    public boolean isValidLogin(String email, String password) {
        if(guestService.findByEmailAndPassword(email, password))
            return true;
        else
            return false;
    }

    @Override
    public User getUserByEmail(String email) {
        User user;
        Guest g = guestService.findGuestByEmail(email);
        if(g!= null) {
            user = new Guest(g.getEmail(), g.getPassword(), g.getName(), g.getSurname(), g.getLivingAddress(), g.getTelephoneNumber());
            return user;
        }
        Host h = hostService.findHostByEmail(email);
        if(h!=null) {
            user = new Host(h.getEmail(), h.getPassword(), h.getName(), h.getSurname(), h.getLivingAddress(), h.getTelephoneNumber());
            return user;
        }
        else{
            return null;
        }
//        //u table-per-class, tabela userData je prazna...
//        UserData userData = repositroy.findByEmail(email);
    }

    @Override
    public LoggedInUserDTO login(String email, String password) {
        if (isValidLogin(email, password)) {
            User user = getUserByEmail(email);
            return new LoggedInUserDTO(user.getName(), user.getSurname(), user.getEmail());
        }
        return null;
//        UserData userData = new UserData("testmail@gmail.com", "passs", true, "pera", "pepeper", "adresa 3", "23489");
//        return new LoggedInUserDTO(userData.getName(), userData.getSurname(), userData.getEmail());

    }
}