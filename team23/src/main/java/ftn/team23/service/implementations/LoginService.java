package ftn.team23.service.implementations;

import ftn.team23.dto.LoggedInUserDTO;
import ftn.team23.entities.UserData;
import ftn.team23.service.interfaces.ILoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {
    @Override
    public boolean isValidLogin(String email, String password) {
//        return false;
    return true;
    }

    @Override
    public UserData getUserByEmail(String email) {
//        return null;
        return new UserData("testmail@gmail.com", "passs", true, "pera", "pepeper", "adresa 3", "23489");
    }

    @Override
    public LoggedInUserDTO login(String email, String password) {
        if (isValidLogin(email, password)) {
            UserData userData = getUserByEmail(email);
            return new LoggedInUserDTO(userData.getName(), userData.getSurname(), userData.getEmail());
        }
        return null;
//        UserData userData = new UserData("testmail@gmail.com", "passs", true, "pera", "pepeper", "adresa 3", "23489");
//        return new LoggedInUserDTO(userData.getName(), userData.getSurname(), userData.getEmail());

    }
}