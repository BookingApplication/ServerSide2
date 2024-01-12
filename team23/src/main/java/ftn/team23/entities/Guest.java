package ftn.team23.entities;

import jakarta.persistence.Entity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
//@SQLDelete(sql
//        = "UPDATE guest "
//        + "SET deleted = true "
//        + "WHERE id = ?")
//@Where(clause = "deleted = false")
public class Guest extends User{
    public Guest() {
    }

    public Guest(String email, String password, String name, String surname, String livingAddress, String telephoneNumber) {
        super(email,password, name, surname, livingAddress, telephoneNumber);
    }

}