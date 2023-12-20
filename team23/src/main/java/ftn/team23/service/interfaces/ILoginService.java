package ftn.team23.service.interfaces;

import ftn.team23.dto.LoggedInUserDTO;
import ftn.team23.entities.User;

public interface ILoginService {

    boolean isValidLogin(String email, String password);

    User getUserByEmail(String email);

    LoggedInUserDTO login(String email, String password);
}
