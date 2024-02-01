package ftn.team23.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ftn.team23.entities.Accommodation;
import ftn.team23.entities.Image;
import ftn.team23.entities.IntervalAndPrice;
import ftn.team23.enums.AccommodationAmenity;
import java.util.HashSet;
import java.util.Set;

public class SearchedAccommodationDTO {

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
    private Set<Image> images;



    public SearchedAccommodationDTO(Object[] objects){
        this.id = (Long) objects[0];
        this.name = (String) objects[1];
        this.description = (String) objects[2];
        this.location = (String) objects[3];
        this.minNbOfGuests = (Integer) objects[4];
        this.maxNbOfGuests = (Integer) objects[5];
        this.accommodationType = (String) objects[6];
        this.amenities = getAmenitiesFromString((String[]) objects[7]);
        this.intervalsAndPrices = getIntervalsAndPricesFromString((String[]) objects[8]);
        this.isPriceSetPerGuest = (boolean) objects[9];
        this.isReservationManual = (boolean) objects[10];
        this.images = getImagesFromString((String[]) objects[11]);
        //add comments and rating, both here and in repository

    }

    public Set<AccommodationAmenity> getAmenitiesFromString(String[] amenitiesAsString)
    {
        Set<AccommodationAmenity> amenities = new HashSet<>();
        for(String amenity : amenitiesAsString)
        {
            //"{FREE_WIFI,BUSINESS_CENTER,ROOM_SERVICE,AIR_CONDITIONING}"
            amenities.add(AccommodationAmenity.valueOf(amenity));
        }
        return  amenities;
    }

    public Set<IntervalAndPrice> getIntervalsAndPricesFromString(String[] intervalsAndPricesAsString)
    {
        Set<IntervalAndPrice> intervalsAndPrices = new HashSet<>();
        for(String intervalAndPriceString : intervalsAndPricesAsString)
        {
            if(intervalAndPriceString.equals(""))
                continue;

            //"{""(1644403200000,1645008000000,55)"",""(1645094400000,1645699200000,55)"",""(1645795200000,1646400000000,55)""}"
            String[] splitted = intervalAndPriceString.split(",");
            IntervalAndPrice intervalAndPrice = new IntervalAndPrice();
            intervalAndPrice.setStartDate(Long.valueOf(splitted[0]));
            intervalAndPrice.setEndDate(Long.valueOf(splitted[1]));
            intervalAndPrice.setPrice(Double.valueOf(splitted[2]));

            intervalsAndPrices.add(intervalAndPrice);
        }
        return  intervalsAndPrices;
    }

    public Set<Image> getImagesFromString(String[] imagesAsString)
    {
        Set<Image> images = new HashSet<>();
        //"{""team23/src/main/resources/static/images/accommodations/img1.jpg,img1.jpg,image/jpeg,1""}"...
        for(String imageString : imagesAsString)
        {
            String[] splitted = imageString.split(",");
            Image image = new Image();
            image.setImagePath(splitted[0]);
            image.setName(splitted[1]);
            image.setType(splitted[2]);
            image.setId(Long.valueOf(splitted[3]));

            images.add(image);
        }
        return images;
    }

    public SearchedAccommodationDTO(Accommodation a){
        this(a.getId(), a.getName(), a.getDescription(), a.getLocation(), a.getMinGuests(), a.getMaxGuests(),
                a.getAccommodationType(), a.getAmenities(), a.getAvailableIntervalsAndPrices(),
                a.isPriceSetPerGuest(), a.getImages());
    }

    public SearchedAccommodationDTO(Long id, String name, String description, String location, Integer minNbOfGuests,
                                    Integer maxNbOfGuests, String accommodationType, Set<AccommodationAmenity> amenities,
                                    Set<IntervalAndPrice> intervalsAndPrices, boolean isPriceSetPerGuest, Set<Image> images) {
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
        this.images = images;
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

    public Set<IntervalAndPrice> getIntervalsAndPrices() {
        return intervalsAndPrices;
    }

    public void setIntervalsAndPrices(Set<IntervalAndPrice> intervalsAndPrices) {
        this.intervalsAndPrices = intervalsAndPrices;
    }

    public boolean isPriceSetPerGuest() {
        return isPriceSetPerGuest;
    }

    public void setPriceSetPerGuest(boolean priceSetPerGuest) {
        isPriceSetPerGuest = priceSetPerGuest;
    }

    public boolean isReservationManual() {
        return isReservationManual;
    }

    public void setReservationManual(boolean reservationManual) {
        isReservationManual = reservationManual;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }


}
