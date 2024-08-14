package com.example.curly_api.controller;

import com.example.curly_api.dto.CreateUserDto;
import com.example.curly_api.dto.UpdateUserDto;
import com.example.curly_api.entity.User;
import com.example.curly_api.repository.RoleRepository;
import com.example.curly_api.repository.UserRepository;
import com.example.curly_api.service.EspecialidadeService;
import com.example.curly_api.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.ok(userService.createUser(createUserDto));
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String userId) {
        var user = userService.getUserById(userId);
        return ResponseEntity.ok(user.get());
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/users/{userId}")
    public ResponseEntity<Optional<User>> updateUser(@PathVariable("userId") String userId,
                                                     @RequestBody(required = false) UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(userService.updateUser(userId, updateUserDto));
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/users/{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") String userId) {

        userService.deleteUser(userId);
        return ResponseEntity.ok().body("Usuário deletado com sucesso!");
    }

}


