package com.progetto.filemanager.controller;

import com.progetto.filemanager.dto.UserDto;
import com.progetto.filemanager.entity.UserEntity;
import com.progetto.filemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
            @RequestParam("password") String password) {

        if (userService.existsByUsername(username)) {
            return ResponseEntity.badRequest().body("Errore: l'utente con questo username è già presente.");
        }
        try {
            UserEntity newUser = userService.register(username, password);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestParam("username") String username, @RequestParam("password") String password) {
//        try {
//            UserEntity user = userService.loginUser(username, password);
//            return ResponseEntity.ok(user);
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
    @PostMapping(value = "/login" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginUser(@RequestBody UserDto userDto) {
        try {
            UserEntity user = userService.loginUser(userDto.getUsername(), userDto.getPassword());
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}