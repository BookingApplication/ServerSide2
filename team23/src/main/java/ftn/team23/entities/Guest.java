package ftn.team23.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Guest extends User{

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "guest_id")
    Set<Reservation> reservations = new HashSet<>();

    public Guest() {
    }

    public Guest(String email, String password, String name, String surname, String livingAddress, String telephoneNumber) {
        super(email,password, name, surname, livingAddress, telephoneNumber);
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

}