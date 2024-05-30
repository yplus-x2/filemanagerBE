package com.progetto.filemanager.service;

import com.progetto.filemanager.entity.CategoryEntity;
import com.progetto.filemanager.entity.FileEntity;
import com.progetto.filemanager.repository.CategoryRepository;
import com.progetto.filemanager.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FileRepository fileRepository;

    public List<CategoryEntity> findAll() {

        return categoryRepository.findAll();
    }

    public CategoryEntity newCategory(String name) {
        Optional<CategoryEntity> existingEntity = categoryRepository.findByName(name);
        return existingEntity.orElseGet(() -> {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setName(name);
            return categoryRepository.save(categoryEntity);
        });
    }

    public Object modifyCategory(String name, String uuid) {
        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findByName(name);
        Optional<FileEntity> fileEntityOptional = fileRepository.findByUuid(uuid);

        if (categoryEntityOptional.isPresent() && fileEntityOptional.isPresent()) {
            CategoryEntity category = categoryEntityOptional.get();
            FileEntity file = fileEntityOptional.get();
            file.setCategory(category);
            return fileRepository.save(file);
        } else {
            return null;
        }
    }

}
