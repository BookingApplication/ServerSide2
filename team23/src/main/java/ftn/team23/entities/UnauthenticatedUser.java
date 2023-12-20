package ftn.team23.entities;

public class UnauthenticatedUser extends UserData{

    public UnauthenticatedUser(){
        super();
    }
    public UnauthenticatedUser(String email, String password, boolean isEmailVerified, String name, String surname, String livingAddress, String telephoneNumber) {
        super(email,password, isEmailVerified, name, surname, livingAddress, telephoneNumber);
    }
}
