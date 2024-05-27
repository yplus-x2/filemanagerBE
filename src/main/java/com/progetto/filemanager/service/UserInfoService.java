package com.progetto.filemanager.service;

import com.progetto.filemanager.entity.UserInfoEntity;
import com.progetto.filemanager.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

}
