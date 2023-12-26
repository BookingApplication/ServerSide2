package ftn.team23.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
//public class Guest extends User implements Serializable {
public class Guest extends User{
    public Guest() {
    }

    public Guest(String email, String password, String name, String surname, String livingAddress, String telephoneNumber) {
        super(email,password, name, surname, livingAddress, telephoneNumber);
    }

}