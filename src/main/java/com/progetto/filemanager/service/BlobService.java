package com.progetto.filemanager.service;

import com.progetto.filemanager.entity.BlobEntity;
import com.progetto.filemanager.repository.BlobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class BlobService {

    @Autowired
    private BlobRepository blobRepository;

    public BlobEntity storeBlob(byte[] content) {
        BlobEntity blobEntity = new BlobEntity();
        blobEntity.setContent(content);
        return blobRepository.save(blobEntity);
    }

    public BlobEntity getBlobById(Long id) {
        return blobRepository.findById(id).orElse(null);
    }
}
