package com.progetto.filemanager.repository;

import com.progetto.filemanager.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<FileEntity, Long> {


    Optional<FileEntity> findByUuid(String uuid);
    List<FileEntity> findByNameContainingIgnoreCase(String name);
}
