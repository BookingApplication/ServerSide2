package ftn.team23.entities;

import jakarta.persistence.*;
//import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Entity
public class Host extends UserData implements Serializable {

    @OneToMany(mappedBy = "host", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Accommodation> accommodations;


    public Host() {
    }

    public Host(String email, String password, String name, String surname, String livingAddress, String telephoneNumber) {
        super(email,password, name, surname, livingAddress, telephoneNumber);
    }
}

