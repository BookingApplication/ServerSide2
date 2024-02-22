package ftn.team23.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ftn.team23.entities.Accommodation;
import ftn.team23.entities.IntervalAndPrice;
import ftn.team23.enums.AccommodationAmenity;

import java.util.List;
import java.util.Set;

public class AccommodationDTO {
    private Long id;
    private String name;
    private String description;
    private String location;
    private Integer minNbOfGuests;
    private Integer maxNbOfGuests;
    private String accommodationType;
    private Set<AccommodationAmenity> amenities;
    private Set<IntervalAndPrice> intervalsAndPrices;
    @JsonProperty("isPriceSetPerGuest")
    private boolean isPriceSetPerGuest;
    @JsonProperty("isReservationManual")
    private boolean isReservationManual;



    public AccommodationDTO(){

    }

    public AccommodationDTO(Accommodation a){
        this(a.getId(), a.getName(), a.getDescription(), a.getLocation(), a.getMinGuests(), a.getMaxGuests(),
                a.getAccommodationType(), a.getAmenities(), a.getAvailableIntervalsAndPrices(),
                a.isPriceSetPerGuest());
    }

    public AccommodationDTO(Long id, String name, String description, String location, Integer minNbOfGuests,
                            Integer maxNbOfGuests, String accommodationType, Set<AccommodationAmenity> amenities,
                            Set<IntervalAndPrice> intervalsAndPrices, boolean isPriceSetPerGuest) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.minNbOfGuests = minNbOfGuests;
        this.maxNbOfGuests = maxNbOfGuests;
        this.accommodationType = accommodationType;
        this.amenities = amenities;
        this.intervalsAndPrices = intervalsAndPrices;
        this.isPriceSetPerGuest = isPriceSetPerGuest;
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

    public Integer getMinNbOfGuests() {
        return minNbOfGuests;
    }

    public void setMinNbOfGuests(Integer minNbOfGuests) {
        this.minNbOfGuests = minNbOfGuests;
    }

    public Integer getMaxNbOfGuests() {
        return maxNbOfGuests;
    }

    public void setMaxNbOfGuests(Integer maxNbOfGuests) {
        this.maxNbOfGuests = maxNbOfGuests;
    }

    public String getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(String accommodationType) {
        this.accommodationType = accommodationType;
    }

    public Set<AccommodationAmenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(Set<AccommodationAmenity> amenities) {
        this.amenities = amenities;
    }

    public boolean isPriceSetPerGuest() {
        return isPriceSetPerGuest;
    }

    public void setPriceSetPerGuest(boolean priceSetPerGuest) {
        isPriceSetPerGuest = priceSetPerGuest;
    }

    public Set<IntervalAndPrice> getIntervalsAndPrices() {
        return intervalsAndPrices;
    }

    public void setIntervalsAndPrices(Set<IntervalAndPrice> intervalsAndPrices) {
        this.intervalsAndPrices = intervalsAndPrices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isReservationManual() {
        return isReservationManual;
    }

    public void setReservationManual(boolean reservationManual) {
        isReservationManual = reservationManual;
    }
}
