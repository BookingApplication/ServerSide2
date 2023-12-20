package ftn.team23.service.interfaces;

import ftn.team23.dto.LoggedInUserDTO;
import ftn.team23.entities.UserData;

public interface ILoginService {

    boolean isValidLogin(String email, String password);

    UserData getUserByEmail(String email);

    LoggedInUserDTO login(String email, String password);
}
