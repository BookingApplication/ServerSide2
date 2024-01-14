package ftn.team23.service.implementations;

import ftn.team23.entities.Image;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

@Service
public class ImageService {

    @Value("${accommodation-pictures-path}")
    private String uploadPath;

    public Set<Image> extractAndSaveImages(MultipartFile[] multipartFiles) throws IOException {
        Set<Image> images = new HashSet<>();
        for (MultipartFile file : multipartFiles) {
            String imgPath = saveImage(file);
            String imgName = file.getName();
            String imgType = file.getContentType();
            byte[] picByte = file.getBytes();
            Image image = new Image(imgPath, imgName, imgType, picByte);
            images.add(image);
        }
        return images;
    }

    public String saveImage(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String imagePath = Paths.get(uploadPath, file.getOriginalFilename()).toString();
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(imagePath))) {
            stream.write(bytes);
        }
        return imagePath;
    }
}
