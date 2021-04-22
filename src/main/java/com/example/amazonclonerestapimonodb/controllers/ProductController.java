package com.example.amazonclonerestapimonodb.controllers;


import com.example.amazonclonerestapimonodb.models.Product;
import com.example.amazonclonerestapimonodb.services.CustomizedResponse;
import com.example.amazonclonerestapimonodb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ProductController {

    @Autowired
    private ProductService service;//service reference variable

    /******************************************************************************************/
    //Get All Products
    @GetMapping("/products")
    //receive object of any type and also return status code
    public ResponseEntity getProducts() {

        var customizedResponse = new CustomizedResponse("A list of products", service.getProducts());

        // always send back statuscode->Header and json
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    /******************************************************************************************/
    //Return a specific product
    @GetMapping("/products/{id}")

    //ResponseEntity receives object of any type and also return status code
    public ResponseEntity getAProduct(@PathVariable("id") String id) {

        CustomizedResponse customizedResponse = null;
        try {

            customizedResponse = new CustomizedResponse("Product found with id " + id, Collections.singletonList(service.getAProduct(id)));

        } catch (Exception e) {

            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    /******************************************************************************************/
    //Delete a specific product
    @DeleteMapping("/products/{id}")

    //ResponseEntity receives object of any type and also return status code
    public ResponseEntity deleteAProduct(@PathVariable("id") String id) {
        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse("Product with  id " + id + " deleted successfully", Collections.singletonList(((service.deleteAProduct(id)))));

        } catch (Exception e) {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.OK);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
    }

    /******************************************************************************************/
    //Create Or Post a Product
    @PostMapping(value = "/products", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addProduct(@RequestBody Product product) {
        service.insertIntoProducts(product);

        return new ResponseEntity(product, HttpStatus.OK);
    }

    /********************************************************************************************/
    //Get All bestseller Products(/products/bestSeller?isBestseller=true
    @GetMapping("/products/IsBestSeller")
    //receive object of any type and also return status code
    public ResponseEntity getBestSellerProducts(@RequestParam(value = "bestSeller") boolean b) //(@RequestParam(value = "limit") String limit )
    {

        var customizedResponse = new CustomizedResponse("A list of bestseller products", service.getBestSellers(b));

        // always send back statuscode->Header and json
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    /********************************************************************************************/
    //Get Products by category(/products/bestSeller?isBestseller=true
    @GetMapping("/products/category_name")
    //receive object of any type and also return status code
    public ResponseEntity getProductsByCategory(@RequestParam(value = "categoryname") String c) //(@RequestParam(value = "limit") String limit )
    {

        var customizedResponse = new CustomizedResponse("A list of bestseller products", service.getProductsWithCatName(c));

        // always send back statuscode->Header and json
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    /********************************************************************************************/
    //Update a product(/products
    @PutMapping(value = "/products/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity UpdateAProduct(@PathVariable("id") String id, @RequestBody Product newProduct) {
        //Singleton
        var customizedResponse = new CustomizedResponse("Product with ID :" + id + "was updated successfully", Collections.singletonList(service.updateProduct(id, newProduct)));

        // always send back statuscode->Header and json
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
}
