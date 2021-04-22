package com.example.amazonclonerestapimonodb.controllers;


import com.example.amazonclonerestapimonodb.models.Category;
import com.example.amazonclonerestapimonodb.services.CategoryService;
import com.example.amazonclonerestapimonodb.services.CustomizedResponse;
//import com.example.amazonclonerestapimonodb.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService service;//service reference variable

    //Get All Categories
    @GetMapping("/categories")
    public ResponseEntity getCategories() {

        var customizedResponse = new CustomizedResponse("A list of Ctegories", service.getCategories());


        // always send back statuscode->Header and json
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    //Return a specific category
    @GetMapping("/categories/{id}")

    //ResponseEntity receives object of any type and also return status code
    public ResponseEntity getACategory(@PathVariable("id") String id)
    {

        var customizedResponse = new CustomizedResponse("Category found with id " +id , Collections.singletonList(service.getACategory(id)));

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    //Delete a specific Category
    @DeleteMapping("/categories/{id}")
    //ResponseEntity receives object of any type and also return status code
    public ResponseEntity deleteACategory(@PathVariable("id") String id)
    {
        service.deleteACategory(id);


        return new ResponseEntity(HttpStatus.OK);
    }


    //Create Or Post a Category
    @PostMapping(value = "/categories", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addCategory(@RequestBody Category category) {
        service.insertIntoCategory(category);

        return new ResponseEntity(category, HttpStatus.OK);
    }
}
