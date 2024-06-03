package com.progetto.filemanager.service;

import com.progetto.filemanager.entity.BlobEntity;
import com.progetto.filemanager.entity.CategoryEntity;
import com.progetto.filemanager.entity.FileEntity;
import com.progetto.filemanager.entity.UserEntity;
import com.progetto.filemanager.repository.CategoryRepository;
import com.progetto.filemanager.repository.FileRepository;
import com.progetto.filemanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Autowired
    private UserRepository userRepository;


    public List<FileEntity> findAll() {

        return fileRepository.findAll();
    }

    public List<FileEntity> findByUserId(Long id){
        return fileRepository.findByUserEntityId(id);
    }

    public FileEntity uploadFile(MultipartFile file, String categoryName, Long userId) throws IOException {

        FileEntity fileEntity = new FileEntity();
        BlobEntity blobEntity = new BlobEntity();

        CategoryEntity category = categoryRepository.findByName(categoryName)
                .orElseGet(() -> {
                    CategoryEntity newCategory = new CategoryEntity();
                    newCategory.setName(categoryName);
                    return categoryRepository.save(newCategory);
                });

        Optional<UserEntity> userEntity = userRepository.findById(userId);

        fileEntity.setCategory(category);
        blobEntity.setContent(file.getBytes());
        fileEntity.setBlob(blobEntity);
        fileEntity.setUuid(UUID.randomUUID().toString());
        fileEntity.setContentType(file.getContentType());
        fileEntity.setName(file.getOriginalFilename());
        fileEntity.setUserEntity(userEntity.get());

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

    public FileEntity getFileByUuidForView(String uuid) {
        return fileRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("File not found for UUID: " + uuid));
    }

}
