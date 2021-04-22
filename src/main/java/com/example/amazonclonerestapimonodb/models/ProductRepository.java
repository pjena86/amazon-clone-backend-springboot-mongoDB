package com.example.amazonclonerestapimonodb.models;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> //Mapping all the CRUD functionality to the Product class/document
{

}
