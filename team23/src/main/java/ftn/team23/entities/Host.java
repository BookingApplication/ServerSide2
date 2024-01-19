package ftn.team23.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.Set;

@Entity
public class Host extends User{
    @OneToMany(mappedBy = "host", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Accommodation> accommodations;


    public Host() {
    }

    public Host(String email, String password, String name, String surname, String livingAddress, String telephoneNumber) {
        super(email,password, name, surname, livingAddress, telephoneNumber);
    }

    public Set<Accommodation> getAccommodations() {
        return accommodations;
    }

    public void setAccommodations(Set<Accommodation> accommodations) {
        this.accommodations = accommodations;
    }
}

