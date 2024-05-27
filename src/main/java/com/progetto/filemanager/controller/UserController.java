package com.progetto.filemanager.controller;

import com.progetto.filemanager.entity.UserEntity;
import com.progetto.filemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins="http://localhost:4200")

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("lastname") String lastname,
            @RequestParam("address") String address,
            @RequestParam("phone") String phone,
            @RequestParam("profilePic") MultipartFile profilePic) {
        if (userService.existsByUsername(username)) {
            return ResponseEntity
                    .badRequest()
                    .body("Errore: l'utente con questo username è già presente.");
        }
        try {
            UserEntity newUser = userService.registerUser(username, password, name, lastname, address, phone, profilePic);
            return ResponseEntity.ok(newUser);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        try {
            UserEntity user = userService.loginUser(username, password);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}