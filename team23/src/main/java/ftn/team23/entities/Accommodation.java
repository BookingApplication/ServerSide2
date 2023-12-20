package ftn.team23.entities;

import ftn.team23.enums.AccommodationAmenity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Accommodation {
    private static final int DAYS_IN_A_YEAR = 365;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String location;    // {"latitude": 123.45, "longitude": 67.89}
    private int minGuests;
    private int maxGuests;
    private String accommodationType;   //Studio, apartment, ...

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<AccommodationAmenity> amenities;
    @ElementCollection
    private List<Double> prices; //prices per day

    @ManyToOne
    @JoinColumn(name = "host_id")
    private Host host;

    @ElementCollection
    @CollectionTable(name = "accommodation_photos", joinColumns = @JoinColumn(name = "accommodation_id"))
    @Column(name = "photo_url")
    private Set<String> photos;

    public Accommodation() {
        this.prices = new ArrayList<>(DAYS_IN_A_YEAR);
    }

    public Accommodation(Long id, String name, String description, String location, int minGuests, int maxGuests, String accommodationType, List<Double> prices, Host host, Set<String> photos, Set<AccommodationAmenity> amenities){
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.minGuests = minGuests;
        this.maxGuests = maxGuests;
        this.accommodationType = accommodationType;
        this.prices = prices;
        this.host = host;
        this.photos = photos;
        this.amenities = amenities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<String> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<String> photos) {
        this.photos = photos;
    }

    public int getMinGuests() {
        return minGuests;
    }

    public void setMinGuests(int minGuests) {
        this.minGuests = minGuests;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    public String getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(String accommodationType) {
        this.accommodationType = accommodationType;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }
    public Set<AccommodationAmenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(Set<AccommodationAmenity> amenities) {
        this.amenities = amenities;
    }

    public List<Double> getPrices() {
        return prices;
    }

    public void setPrices(List<Double> prices) {
        this.prices = prices;
    }
}