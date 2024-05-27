package com.progetto.filemanager.repository;

import com.progetto.filemanager.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {

}
