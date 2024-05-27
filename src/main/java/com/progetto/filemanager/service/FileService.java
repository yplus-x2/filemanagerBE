package com.progetto.filemanager.service;

import com.progetto.filemanager.entity.BlobEntity;
import com.progetto.filemanager.entity.CategoryEntity;
import com.progetto.filemanager.entity.FileEntity;
import com.progetto.filemanager.repository.CategoryRepository;
import com.progetto.filemanager.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public List<FileEntity> findAll() {
        return fileRepository.findAll();
    }

    public FileEntity uploadFile(MultipartFile file, String categoryName) throws IOException {

        FileEntity fileEntity = new FileEntity();
        BlobEntity blobEntity = new BlobEntity();

        CategoryEntity category = categoryRepository.findByName(categoryName)
                .orElseGet(() -> {
                    CategoryEntity newCategory = new CategoryEntity();
                    newCategory.setName(categoryName);
                    return categoryRepository.save(newCategory);
                });

        fileEntity.setCategory(category);
        blobEntity.setContent(file.getBytes());
        fileEntity.setBlob(blobEntity);
        fileEntity.setUuid(UUID.randomUUID().toString());
        fileEntity.setName(file.getOriginalFilename());

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        fileEntity.setDate(formattedDateTime);

        return fileRepository.save(fileEntity);
    }

    public Optional<FileEntity> getFileByUuid(String uuid) {
        return fileRepository.findByUuid(uuid);
    }

    public FileEntity updateFileName(String uuid, String newName) {
        Optional<FileEntity> fileEntityOptional = fileRepository.findByUuid(uuid);
        if (fileEntityOptional.isPresent()) {
            FileEntity fileEntity = fileEntityOptional.get();
            fileEntity.setName(newName);
            return fileRepository.save(fileEntity);
        } else {
            throw new RuntimeException("File not found for UUID: " + uuid);
        }
    }

    public List<FileEntity> searchFilesByName(String name) {
        return fileRepository.findByNameContainingIgnoreCase(name);
    }

    public void deleteFile(String uuid) {
        Optional<FileEntity> fileEntityOptional = fileRepository.findByUuid(uuid);
        fileEntityOptional.ifPresent(fileRepository::delete);
    }


}
