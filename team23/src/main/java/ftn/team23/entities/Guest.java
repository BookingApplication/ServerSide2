package ftn.team23.entities;

import javax.persistence.Entity;

@Entity
public class Guest extends UserData{

    public Guest() {
    }

    public Guest(String email, String password, boolean isEmailVerified, String name, String surname, String livingAddress, String telephoneNumber) {
        super(email,password, isEmailVerified, name, surname, livingAddress, telephoneNumber);
    }

}