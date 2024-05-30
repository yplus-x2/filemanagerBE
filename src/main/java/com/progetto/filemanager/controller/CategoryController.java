package com.progetto.filemanager.controller;

import com.progetto.filemanager.entity.CategoryEntity;
import com.progetto.filemanager.entity.FileEntity;
import com.progetto.filemanager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.util.Elements;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/category")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/all")
    public ResponseEntity<List<CategoryEntity>> getAllCategories() {
        List<CategoryEntity> categories = categoryService.findAll();
        if(categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/newCategory")
    public ResponseEntity<CategoryEntity> newCategory(@RequestParam String name) throws IOException {
        CategoryEntity categoryEntity = categoryService.newCategory(name);
        return new ResponseEntity<>(categoryEntity, HttpStatus.OK);
    }

    @PostMapping("/modifyCategory")
    public ResponseEntity<?> modifyCategory(@RequestParam String uuid, @RequestParam String name) {
        Object modifiedCategory = categoryService.modifyCategory(name, uuid);
        if (modifiedCategory != null) {
            return new ResponseEntity<>(modifiedCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Categoria non trovata o file non trovato.", HttpStatus.NOT_FOUND);
        }
    }
}

