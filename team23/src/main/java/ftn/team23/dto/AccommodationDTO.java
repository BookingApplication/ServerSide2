package ftn.team23.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ftn.team23.entities.Accommodation;
import ftn.team23.entities.IntervalAndPrice;
import ftn.team23.enums.AccommodationAmenity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
