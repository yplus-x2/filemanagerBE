package com.progetto.filemanager.repository;

import com.progetto.filemanager.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);


}
