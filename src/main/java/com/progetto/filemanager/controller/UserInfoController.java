package com.progetto.filemanager.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/userinfo")
@CrossOrigin(origins = "http://localhost:4200" , exposedHeaders = "Access-Control-Allow-Origin")

public class UserInfoController {

}
