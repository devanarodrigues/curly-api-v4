package com.example.curly_api.controller;

import com.example.curly_api.repository.UserRepository;
import com.example.curly_api.service.GoogleDriveService;
import com.example.curly_api.service.PostagemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@AllArgsConstructor
public class PostagemController {

    private GoogleDriveService googleDriveService;
    private UserRepository userRepository;
    private PostagemService postagemService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/postagem/{userId}")
    public ResponseEntity postagem(@RequestParam("image") MultipartFile file,
                                   @PathVariable("userId") String userId,
                                   @RequestParam("descricao") String descricao) throws IOException {

        if (file.isEmpty()) {
            return ResponseEntity.ok("File is empty!");
        }
        if (userRepository.findById(userId).isEmpty()) {
            return ResponseEntity.ok("User is empty!");
        }

        File tempFile = File.createTempFile("temp", null);
        file.transferTo(tempFile);

        return ResponseEntity.ok(googleDriveService.uploadPostagem(tempFile, userId, file, descricao));
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/postagem/{postagemId}")
    public ResponseEntity deletePostagem(@PathVariable("postagemId") String postagemId){
        return ResponseEntity.ok(postagemService.deletePostagem(postagemId));
    }
}
