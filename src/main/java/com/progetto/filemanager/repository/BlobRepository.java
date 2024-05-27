package com.progetto.filemanager.repository;

import com.progetto.filemanager.entity.BlobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

public interface BlobRepository extends JpaRepository<BlobEntity, Long> {


}
