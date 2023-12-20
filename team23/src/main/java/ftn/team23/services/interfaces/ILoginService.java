package ftn.team23.services.interfaces;

import ftn.team23.entities.UserData;

public interface ILoginService {

    boolean isValidLogin(String email, String password);

    UserData getUserByEmail(String email);
}