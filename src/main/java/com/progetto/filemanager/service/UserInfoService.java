package com.progetto.filemanager.service;

import com.progetto.filemanager.entity.*;
import com.progetto.filemanager.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public Optional<UserInfoEntity> getProfileImage(Long userId) {
        return userInfoRepository.findById(userId);
    }

//    public UserInfoEntity uploadFile(MultipartFile file, Long userId) throws IOException {
//
//        UserInfoEntity userInfoEntity = new UserInfoEntity();
//        BlobEntity blobEntity = new BlobEntity();
//
//        Optional<UserEntity> userEntity = userRepository.findById(userId);
//
//        fileEntity.setCategory(category);
//        blobEntity.setContent(file.getBytes());
//        fileEntity.setBlob(blobEntity);
//        fileEntity.setUuid(UUID.randomUUID().toString());
//        fileEntity.setContentType(file.getContentType());
//        fileEntity.setName(file.getOriginalFilename());
//        fileEntity.setUserEntity(userEntity.get());
//
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String formattedDateTime = now.format(formatter);
//
//        fileEntity.setDate(formattedDateTime);
//
//        return fileRepository.save(fileEntity);
//    }

}
