package com.progetto.filemanager.controller;

import com.progetto.filemanager.entity.BlobEntity;
import com.progetto.filemanager.entity.FileEntity;
import com.progetto.filemanager.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/file")

public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/all")
    public ResponseEntity<List<FileEntity>> getAllFiles() {
        List<FileEntity> files = fileService.findAll();
        if(files.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(files, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public FileEntity uploadFile(@RequestParam("file") MultipartFile file,
                                 @RequestParam("categoryName") String categoryName) throws IOException {
        return fileService.uploadFile(file, categoryName);
    }

    @GetMapping("/download/{uuid}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String uuid) {
        Optional<FileEntity> fileEntityOptional = fileService.getFileByUuid(uuid);

        if (fileEntityOptional.isPresent()) {
            FileEntity fileEntity = fileEntityOptional.get();
            System.out.println("@@@@ NOME DEL FILE: " + fileEntity.getName());
            BlobEntity blobEntity = fileEntity.getBlob();

            HttpHeaders headers = new HttpHeaders();
            String filename = fileEntity.getName();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
            headers.setContentLength(blobEntity.getContent().length);
            headers.setContentType(MediaType.parseMediaType(fileEntity.getContentType()));

            return new ResponseEntity<>(blobEntity.getContent(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PatchMapping("/updateName/{uuid}")
    public ResponseEntity<FileEntity> updateFileName(@PathVariable String uuid, @RequestParam("newName") String newName) {
        try {
            FileEntity updatedFile = fileService.updateFileName(uuid, newName);
            return ResponseEntity.ok(updatedFile);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchByName")
    public ResponseEntity<List<FileEntity>> searchFilesByName(@RequestParam("name") String name) {
        List<FileEntity> files = fileService.searchFilesByName(name);
        return ResponseEntity.ok(files);
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<?> deleteFile(@PathVariable String uuid) {
        try {
            fileService.deleteFile(uuid);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("File non trovato", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/view/{uuid}")
    public ResponseEntity<byte[]> viewFile(@PathVariable String uuid) {
        FileEntity fileEntity = fileService.getFileByUuidForView(uuid);
        BlobEntity blobEntity = fileEntity.getBlob();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fileEntity.getContentType()));
        headers.setContentLength(blobEntity.getContent().length);

        return new ResponseEntity<>(blobEntity.getContent(), headers, HttpStatus.OK);
    }

}
