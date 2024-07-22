package com.example.curly_api.controller;

import com.example.curly_api.config.ImageUploadResponse;
import com.example.curly_api.repository.UserRepository;
import com.example.curly_api.service.GoogleDriveService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@AllArgsConstructor
public class GoogleDriveController {

    private GoogleDriveService googleDriveService;
    private UserRepository userRepository;

//    @PostMapping("/uploadImage")
//    public Object uploadImage (@RequestParam("image")MultipartFile file) throws IOException {
//        if(file.isEmpty()){
//            return "File is empty!";
//        }
//        File tempFile = File.createTempFile("temp", null);
//        file.transferTo(tempFile);
//        Res res = googleDriveService.uploadImageToDrive(tempFile);
//        System.out.print(res);
//        return res;
//    }

    @PostMapping("/uploadImage/{userId}")
    public Object uploadImage (@RequestParam("image") MultipartFile file,
                               @PathVariable("userId") String userId) throws IOException {


        if(file.isEmpty()){
            return "File is empty!";
        }
        if(userRepository.findById(userId).isEmpty()){
            return "User is empty!";
        }

        File tempFile = File.createTempFile("temp", null);
        file.transferTo(tempFile);
        ImageUploadResponse res = googleDriveService.uploadImageToDrive(tempFile,userId, file);


        System.out.print(res);
        return res;
    }

}
