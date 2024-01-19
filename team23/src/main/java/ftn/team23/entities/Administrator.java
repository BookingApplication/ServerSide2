package ftn.team23.entities;

import jakarta.persistence.Entity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
public class Administrator extends User{
    public Administrator() {
    }

    public Administrator(String email, String password, String name, String surname, String livingAddress, String telephoneNumber) {
        super(email,password, name, surname, livingAddress, telephoneNumber);
    }
}
