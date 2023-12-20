package ftn.team23.entities;

import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
public class Guest extends UserData implements Serializable {
    public Guest() {
    }

    public Guest(String email, String password, boolean isEmailVerified, String name, String surname, String livingAddress, String telephoneNumber) {
        super(email,password, isEmailVerified, name, surname, livingAddress, telephoneNumber);
    }
}