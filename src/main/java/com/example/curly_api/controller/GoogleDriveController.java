package com.example.curly_api.controller;

import com.example.curly_api.config.ImageUploadResponse;
import com.example.curly_api.dto.CreatePostagemDto;
import com.example.curly_api.repository.UserRepository;
import com.example.curly_api.service.GoogleDriveService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@AllArgsConstructor
public class GoogleDriveController {

    private GoogleDriveService googleDriveService;
    private UserRepository userRepository;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/fotoPerfil/{userId}")
    public Object fotoPerfil(@RequestParam("image") MultipartFile file,
                             @PathVariable("userId") String userId) throws IOException {

        if (file.isEmpty()) {
            return "File is empty!";
        }
        if (userRepository.findById(userId).isEmpty()) {
            return "User is empty!";
        }

        File tempFile = File.createTempFile("temp", null);
        file.transferTo(tempFile);
        ImageUploadResponse res = googleDriveService.uploadProfileImageToDrive(tempFile, userId, file);

        System.out.print(res);
        return res;
    }
}
