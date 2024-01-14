package ftn.team23.service.implementations;

import ftn.team23.dto.AccommodationDTO;
import ftn.team23.dto.AccommodationWithImagesDTO;
import ftn.team23.entities.Accommodation;
import ftn.team23.entities.Host;
import ftn.team23.entities.Image;
import ftn.team23.enums.Status;
import ftn.team23.repositories.IAccommodationRepository;
import ftn.team23.service.interfaces.IAccommodationService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class AccommodationService implements IAccommodationService {

    @Autowired
    IAccommodationRepository repository;

    @Value("${accommodation-pictures-path}")
    String uploadPath;


    @Override
    @Transactional(rollbackFor = {IOException.class, RuntimeException.class})
    public void createAccommodation(AccommodationDTO accommodationDetails, MultipartFile[] multipartFiles) {
        Host host = (Host) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Accommodation> accommodations = repository.findByHost(host.getId());
        if (!accommodations.isEmpty()) {
            for (Accommodation a : accommodations) {
                if (a.getName().equals(accommodationDetails.getName())) {
                    return;
                }
            }
        }

        Accommodation newAccommodation = new Accommodation(accommodationDetails);
        newAccommodation.setHost(host);
        newAccommodation.setStatus(Status.WAITING_CONFIRMATION);

        Set<Image> images = new HashSet<>();
        try {
            images = extractAndSaveImages(multipartFiles);
            for(Image image : images) {
                image.setAccommodation(newAccommodation);
                image.setId((long) (Math.random()*1000000));
            }
            newAccommodation.setImages(images);
            repository.save(newAccommodation);
            repository.flush();
        } catch (IOException e) {
            System.out.println("Storing images to filesystem failed.");
            System.out.println(e.getMessage());
        } catch (RuntimeException ex) {
            System.out.println("Persisting accommodation to db failed.");
            Throwable e = ex;
            Throwable c = null;
            while ((e != null) && !((c = ex.getCause()) instanceof ConstraintViolationException)) {
                e = (RuntimeException) c;
            }
            if ((c != null) && (c instanceof ConstraintViolationException)) {
                ConstraintViolationException c2 = (ConstraintViolationException) c;
                Set<ConstraintViolation<?>> errors = c2.getConstraintViolations();
                StringBuilder sb = new StringBuilder(1000);
                for (ConstraintViolation<?> error : errors) {
                    sb.append(error.getMessage() + "\n");
                }
                System.out.println(sb.toString());
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, sb.toString());
            }
            throw ex;
        }

        for(Image image : images)
            image.setAccommodation(newAccommodation);
        newAccommodation.setImages(images);
        repository.save(newAccommodation);
        repository.flush();

    }


    //todo check if there are any reservations overlaping with one of available intervals
    @Override
    @Transactional(rollbackFor = {IOException.class, RuntimeException.class})
    public void updateAccommodationData(AccommodationDTO updatedAccommodationDetails, MultipartFile[] multipartFiles) {
        Optional<Accommodation> found = repository.findById(updatedAccommodationDetails.getId());
        if(found.isEmpty())
            return;

        Accommodation updatedAccommodation = found.get();
        //ostavi id, host
        updatedAccommodation.setName(updatedAccommodationDetails.getName());
        updatedAccommodation.setDescription(updatedAccommodationDetails.getDescription());
        updatedAccommodation.setLocation(updatedAccommodationDetails.getLocation());
        updatedAccommodation.setMinGuests(updatedAccommodationDetails.getMinNbOfGuests());
        updatedAccommodation.setMaxGuests(updatedAccommodationDetails.getMaxNbOfGuests());
        updatedAccommodation.setAccommodationType(updatedAccommodationDetails.getAccommodationType());
        updatedAccommodation.setAmenities(updatedAccommodationDetails.getAmenities());
        updatedAccommodation.setAvailableIntervals(updatedAccommodationDetails.getIntervals());
        updatedAccommodation.setPrices(updatedAccommodationDetails.getPrices());
        updatedAccommodation.setPriceSetPerGuest(updatedAccommodationDetails.isPriceSetPerGuest());
        updatedAccommodation.setStatus(Status.WAITING_CONFIRMATION);

        try{
            Set<Image> newImages = extractAndSaveImages(multipartFiles);
            Set<Image> oldImages = updatedAccommodation.getImages();

            Set<Path> imagesToDelete = new HashSet<>();
            for(Image image : oldImages)
            {
                if(!newImages.contains(image))
                    imagesToDelete.add(Paths.get(image.getImagePath()));
            }
            for(Path imagePath : imagesToDelete)
            {
                deleteImage(imagePath);
            }

            updatedAccommodation.setImages(newImages);
            repository.save(updatedAccommodation);
            repository.flush();

        } catch (IOException e) {
            System.out.println("Storing images to filesystem failed.");
            System.out.println(e.getMessage());
        } catch (RuntimeException ex) {
            System.out.println("Persisting accommodation to db failed.");
            Throwable e = ex;
            Throwable c = null;
            while ((e != null) && !((c = ex.getCause()) instanceof ConstraintViolationException)) {
                e = (RuntimeException) c;
            }
            if ((c != null) && (c instanceof ConstraintViolationException)) {
                ConstraintViolationException c2 = (ConstraintViolationException) c;
                Set<ConstraintViolation<?>> errors = c2.getConstraintViolations();
                StringBuilder sb = new StringBuilder(1000);
                for (ConstraintViolation<?> error : errors) {
                    sb.append(error.getMessage() + "\n");
                }
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, sb.toString());
            }
            throw ex;
        }
    }


    @Override
    public Set<AccommodationWithImagesDTO> getAllAvailableAccommodations() {

        Set<AccommodationWithImagesDTO> accommodationsWithImagesDTOS = new HashSet<>();
        List<Accommodation> allAccommodations = repository.findAccommodationByStatus(Status.APPROVED);
        if(allAccommodations.isEmpty())
            return null;

        for(Accommodation a : allAccommodations)
        {
            Set<Image> images = getImagesFromFilesystem(a.getImages());
            AccommodationDTO accommodationDTO = new AccommodationDTO(a);
            AccommodationWithImagesDTO accommodationWithImagesDTO = new AccommodationWithImagesDTO(accommodationDTO, images);

            accommodationsWithImagesDTOS.add(accommodationWithImagesDTO);
        }
        return accommodationsWithImagesDTOS;
    }

    @Override
    public Boolean approveAccommodation(Long id)
    {
        repository.changeStatus(id, Status.APPROVED);
        return false;
    }

    @Override
    public Boolean denyAccommodation(Long id) {
        return null;
    }

    @Override
    public AccommodationWithImagesDTO getAccommodationDetails(Long id) {
        Optional<Accommodation> accommodation = repository.findByIdWithImages(id);
        AccommodationWithImagesDTO result = new AccommodationWithImagesDTO();
        AccommodationDTO accommodationDTO = new AccommodationDTO(accommodation.get());
        Set<Image> images = accommodation.get().getImages();
        Set<Image> imagesFromFileSytem = getImagesFromFilesystem(images);
        result.setAccommodationDTO(accommodationDTO);
        result.setImages(imagesFromFileSytem);
        return result;
    }

    @Override
    public void deleteAccommodation(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Set<AccommodationWithImagesDTO> getAccommodationsWaitingOnApproval() {
        repository.findAccommodationByStatus(Status.WAITING_CONFIRMATION);

        return null;
    }


    public Set<Image> extractAndSaveImages(MultipartFile[] multipartFiles) throws IOException {
        Set<Image> images = new HashSet<>();
        for (MultipartFile file: multipartFiles) {

            Image image = saveImage(file);
            images.add(image);
        }

        return images;
    }

    public Image saveImage(MultipartFile file) throws IOException
    {
        byte[] bytes = file.getBytes();
        String name = file.getOriginalFilename();
        String imagePath = Paths.get(uploadPath, name).toString();
        String imageType = file.getContentType();

        Image image = new Image(imagePath, name, imageType);

        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(imagePath)))) {
            stream.write(bytes);
        }

        return image;
    }

    private void deleteImage(Path imagePath) {
        try {
            Files.delete(imagePath);
            System.out.println("File "
                    + imagePath.toAbsolutePath().toString()
                    + " successfully removed");
        } catch (IOException e) {
            System.err.println("Unable to delete "
                    + imagePath.toAbsolutePath().toString()
                    + " due to...");
            e.printStackTrace();
        }
    }

    private Set<Image> getImagesFromFilesystem(Set<Image> images) {
        Set<Image> ret = new HashSet<>();
        for(Image image : images)
        {
            ret.add(getImageFromFilesystem(image));
        }
        return ret;
    }

    private Image getImageFromFilesystem(Image image){
        Path path = Paths.get(image.getImagePath());
        try {
            byte[] imageData = Files.readAllBytes(path);
            Image retVal = new Image(image.getImagePath(), image.getName(), image.getType(), image.getImageBytes());
            retVal.setImageBytes(imageData);
            return retVal;
        } catch (IOException e) {
            System.err.println("Unable to get image data "
                    + path.toAbsolutePath().toString()
                    + " due to...");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



}
