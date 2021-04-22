package com.example.amazonclonerestapimonodb.services;


import com.example.amazonclonerestapimonodb.models.Category;
import com.example.amazonclonerestapimonodb.models.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {


    @Autowired
    private CategoryRepository repository;

    public List<Category> getCategories() {


        return repository.findAll();
    }

    //Get a Category Service
    public Optional<Category> getACategory(String id) {

        return repository.findById(id); //Optional says it could contain a value or not contain a value
    }

    // Create a Category service
    public void insertIntoCategory(Category category) {

        repository.insert(category);
    }

   //Delete a Category service
    public void deleteACategory(String id) {

        repository.deleteById(id);
    }
}
