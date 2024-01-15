package ftn.team23.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Set;

@Entity
//@SQLDelete(sql
//        = "UPDATE host "
//        + "SET deleted = true "
//        + "WHERE id = ?")
//@Where(clause = "deleted = false")
public class Host extends User{


    @OneToMany(mappedBy = "host", fetch = FetchType.LAZY, cascade = CascadeType.ALL) //jer se svi smestaji brisu kad se obrise vlasnik koji ih je kreirao
    private Set<Accommodation> accommodations;


    public Host() {
    }

    public Host(String email, String password, String name, String surname, String livingAddress, String telephoneNumber) {
        super(email,password, name, surname, livingAddress, telephoneNumber);
    }
}

