package ftn.team23.entities;

import jakarta.persistence.Entity;

@Entity
//public class Guest extends User implements Serializable {
public class Guest extends User{
    public Guest() {
    }

    public Guest(String email, String password, String name, String surname, String livingAddress, String telephoneNumber) {
        super(email,password, name, surname, livingAddress, telephoneNumber);
    }

}