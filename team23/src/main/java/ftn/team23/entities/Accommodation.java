package ftn.team23.entities;

import ftn.team23.dto.AccommodationDTO;
import ftn.team23.enums.AccommodationAmenity;
import ftn.team23.enums.Status;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Accommodation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(length = 2000)
    private String description;
    private String location;
    private int minGuests;
    private int maxGuests;
    private String accommodationType;   //Studio, apartment, ...
    private Status status;  //waiting_confirmation, approved, denied
    private boolean isPriceSetPerGuest;
    private boolean isReservationManual;
    private Integer reservationDeadline;   //reservation can be cancelled, this many days before it's start date.

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<AccommodationAmenity> amenities = new HashSet<AccommodationAmenity>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Double> prices = new ArrayList<Double>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "available_intervals", joinColumns = @JoinColumn(name = "accommodation_id"))
    private Set<Interval> availableIntervals = new HashSet<Interval>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "accommodation_id")
    private Set<Image> images = new HashSet<Image>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="host_id")
    private Host host;

    public Accommodation(AccommodationDTO a)
    {
        this.name = a.getName();
        this.description = a.getDescription();
        this.location = a.getLocation();
        this.minGuests = a.getMinNbOfGuests();
        this.maxGuests = a.getMaxNbOfGuests();
        this.accommodationType = a.getAccommodationType();
        this.prices = a.getPrices();
        this.amenities = a.getAmenities();
        this.isPriceSetPerGuest = a.isPriceSetPerGuest();
    }

    public Accommodation(String name, String description, String location, int minGuests, int maxGuests, String accommodationType, List<Double> prices, Host host, Set<String> photos, Set<AccommodationAmenity> amenities) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.minGuests = minGuests;
        this.maxGuests = maxGuests;
        this.accommodationType = accommodationType;
        this.prices = prices;
        this.host = host;
        this.amenities = amenities;
    }

    public void addImage(Image image){
        images.add(image);
        image.setAccommodation(this);
    }

    public void removeImage(Image image){
        images.remove(image);
        image.setAccommodation(null);
    }


    @Override
    public String toString(){
        return "Accommodation{" +
                "id=" + this.id +
                ", name='" + this.name + "}";
    }
}
