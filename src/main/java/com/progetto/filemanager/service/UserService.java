package com.progetto.filemanager.service;

import com.progetto.filemanager.entity.UserEntity;
import com.progetto.filemanager.entity.UserInfoEntity;
import com.progetto.filemanager.repository.UserRepository;
import com.progetto.filemanager.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserInfoRepository userInfoRepository) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
    }

    public UserEntity registerUser(String username, String password, String name, String lastname, String address, String phone, MultipartFile profilePic) throws IOException {
        UserEntity userEntity = new UserEntity();
        UserInfoEntity userInfoEntity = new UserInfoEntity();

        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setUuid(UUID.randomUUID().toString());

        userInfoEntity.setName(name);
        userInfoEntity.setLastname(lastname);
        userInfoEntity.setAddress(address);
        userInfoEntity.setPhone(phone);
        userInfoEntity.setProfilepic(profilePic.getBytes());

        userInfoRepository.save(userInfoEntity);
        userEntity.setId_userinfo(userInfoEntity);

        return userRepository.save(userEntity);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public UserEntity loginUser(String username, String password) {
        UserEntity userEntity = userRepository.findByUsernameAndPassword(username, password);
        if (userEntity != null) {
            return userEntity;
        } else {
            throw new RuntimeException("Username o password errati");
        }
    }

}
