package ftn.team23.dto;

import ftn.team23.entities.Image;

import java.util.Set;

public class AccommodationWithImagesDTO {
    AccommodationDTO accommodationDTO;
    Set<Image> images;

    public AccommodationWithImagesDTO(){

    }

    public AccommodationWithImagesDTO(AccommodationDTO accommodationDTO, Set<Image> images) {
        this.accommodationDTO = accommodationDTO;
        this.images = images;
    }

    public AccommodationDTO getAccommodationDTO() {
        return accommodationDTO;
    }

    public void setAccommodationDTO(AccommodationDTO accommodationDTO) {
        this.accommodationDTO = accommodationDTO;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

}
