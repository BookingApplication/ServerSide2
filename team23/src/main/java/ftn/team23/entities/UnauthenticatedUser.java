package ftn.team23.entities;

public class UnauthenticatedUser extends User {

    public UnauthenticatedUser(){
        super();
    }

    public UnauthenticatedUser(String email, String password, String name, String surname, String livingAddress, String telephoneNumber) {
        super(email,password, name, surname, livingAddress, telephoneNumber);
    }
}
