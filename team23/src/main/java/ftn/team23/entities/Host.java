package ftn.team23.entities;

import javax.persistence.Entity;

@Entity
public class Host extends UserData {

    public Host() {
    }

    public Host(String email, String password, boolean isEmailVerified, String name, String surname, String livingAddress, String telephoneNumber) {
        super(email,password, isEmailVerified, name, surname, livingAddress, telephoneNumber);
    }
}
