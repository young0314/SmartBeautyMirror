package com.example.smartbeautymirror.Mirror;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;


@SpringBootApplication

@RestController
public class RaspberryController {
    private static final Logger logger = LoggerFactory.getLogger(RaspberryController.class);
    @PostMapping(value = "/upload")

    public ResponseEntity<String> uploadImage(@RequestPart(value = "face_image") MultipartFile image) {
        logger.info("1");
        if (image != null && !image.isEmpty()) {
            try {
                logger.info("2");;
                String filePath = "home/ubuntu/app/step2/image/";
                byte[] fileBytes = image.getBytes();
                String fileName = image.getOriginalFilename();
                Path path = Path.of(filePath + fileName);
                logger.info("Saving image to: " + filePath);
                logger.info("3");
                Files.write(path, fileBytes, StandardOpenOption.CREATE);
                logger.info("4");

                return ResponseEntity.ok().body("Image uploaded successfully!");
            } catch (Exception e) {
                logger.error("Error uploading image: " + e.getMessage());
                e.printStackTrace();
                return ResponseEntity.badRequest().body("Error uploading image: " + e.getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("Image not found in the request!");
        }
    }

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(5242880); // 최대 업로드 크기 설정 (5MB)
        return multipartResolver;
    }
}
