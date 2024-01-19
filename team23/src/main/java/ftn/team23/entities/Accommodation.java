package ftn.team23.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ftn.team23.dto.AccommodationDTO;
import ftn.team23.enums.AccommodationAmenity;
import ftn.team23.enums.Status;
import ftn.team23.util.IntervalSerializer;
import ftn.team23.util.IntervalDeserializer;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TableGenerator(name="accommodation_id_generator", table="primary_keys", pkColumnName="key_pk", pkColumnValue="accommodation", valueColumnName="value_pk")
public class Accommodation implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "accommodation_id_generator")
    private Long id;

    @Length(min = 1, max=40, message = "{accommodation.name.length}")
    private String name;
    @Column(length = 2000)
    @Length(min = 1, max=2000, message = "{accommodation.description.length}")
    private String description;
    @Length(min = 1, max=100, message = "{accommodation.location.length}")
    private String location;
    @PositiveOrZero(message = "{accommodation.minGuests.positiveOrZero}")
    private int minGuests;
    @Positive(message = "{accommodation.maxGuests.positive}")
    private int maxGuests;
    @Length(min=1, message = "{accommodation.accommodationType.Empty}")
    private String accommodationType;   //Studio, apartment, ...
    @NotNull(message = "{accommodation.status.notNull}")
    private Status status;  //waiting_confirmation, approved, denied
    @NotNull(message = "{accommodation.isPriceSetPerGuest.notNull}")
    private boolean isPriceSetPerGuest;
    @NotNull(message = "{accommodation.isReservationManual.notNull}")
    private boolean isReservationManual;
    @PositiveOrZero(message = "{accommodation.reservationDeadline.PositiveOrZero}")
    private Integer reservationDeadline;   //reservation can be cancelled, this many days before it's start date.

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<AccommodationAmenity> amenities = new HashSet<AccommodationAmenity>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Double> prices = new ArrayList<Double>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "available_intervals", joinColumns = @JoinColumn(name = "accommodation_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "startDate", column = @Column(name = "start_date")),
            @AttributeOverride(name = "endDate", column = @Column(name = "end_date"))
    })
    @JsonSerialize(using = IntervalSerializer.class)
    @JsonDeserialize(using = IntervalDeserializer.class)
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
