package ftn.team23.dto;

import ftn.team23.entities.Image;

import java.util.Set;

public class AccommodationWithImagesDTO {
    AccommodationDTO accommodation;
    Set<Image> images;

    public AccommodationWithImagesDTO(){

    }

    public AccommodationWithImagesDTO(AccommodationDTO accommodation, Set<Image> images) {
        this.accommodation = accommodation;
        this.images = images;
    }

    public AccommodationDTO getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(AccommodationDTO accommodation) {
        this.accommodation = accommodation;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

}
