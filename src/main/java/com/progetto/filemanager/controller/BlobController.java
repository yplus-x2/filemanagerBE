package com.progetto.filemanager.controller;

import com.progetto.filemanager.service.BlobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/blob")
@CrossOrigin(origins="http://localhost:4200")

public class BlobController {

    @Autowired
    private BlobService blobService;

}
