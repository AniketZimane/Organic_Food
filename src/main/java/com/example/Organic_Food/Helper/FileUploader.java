package com.example.Organic_Food.Helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileUploader {

    @Value("${upload.dir}")
    private String uploadDir;

    public boolean uploadFile(MultipartFile file, String fileNameNew) {
        boolean result = false;

        try {
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Files.copy(file.getInputStream(), uploadPath.resolve(fileNameNew));
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
