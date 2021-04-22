package com.example.amazonclonerestapimonodb.services;


import com.example.amazonclonerestapimonodb.models.Product;
import com.example.amazonclonerestapimonodb.models.ProductRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Product> getProducts() {


        return repository.findAll();
    }
// Create a product service
    public void insertIntoProducts(Product product) {
        repository.insert(product);
    }
/**********************************************************************************************/
    //Get a Product Service
    public Optional<Product> getAProduct(String id) throws Exception
    {

        Optional<Product> product = repository.findById(id);
        //if the product reference variable does not contain a value then
        if(!product.isPresent())
        {
            throw new Exception("Product with " + id  + " is not found");

        }
        return product; //Optional says it could contain a value or not contain a value
    }
/***********************************************************************************************/
    //Delete a Product service

    public Optional<Product> deleteAProduct(String id) throws Exception
    {
        Optional<Product> product = repository.findById(id);

        repository.deleteById(id);

        if (!product.isPresent())
        {
            throw new Exception("Product with " + id  + " is not found");
        }
        return product;
    }
//BestSeller
/***************************************************************************/
public List<Product> getBestSellers(boolean b)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("IsBestSeller").is(b));
        List<Product> bestsellerProds = mongoTemplate.find(query,Product.class);

        return bestsellerProds;
    }
    //Products By Category
    /***************************************************************************/
    public List<Product> getProductsWithCatName(String c)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("category_name").is(c));
        List<Product> prodsByCat = mongoTemplate.find(query,Product.class);

        return prodsByCat;
    }

    //Update Product
    /***************************************************************************/
     public Product updateProduct(String id, Product newProductDetails)

     {
     // Get the product based on the id

         Optional<Product> product = repository.findById(id);

     //Code to validate the id

         product.get().setCountInStock(newProductDetails.getCountInStock());//wrap Optional product to regular product object
         product.get().setPrice(newProductDetails.getPrice());
         product.get().setImage(newProductDetails.getImage());
         product.get().setRating(newProductDetails.getRating());
         product.get().setTitle(newProductDetails.getTitle());
         product.get().setBestSeller(newProductDetails.isBestSeller());
         product.get().setBrand(newProductDetails.getBrand());
         product.get().setCategory_name(newProductDetails.getCategory_name());
         product.get().setDescription(newProductDetails.getDescription());
         product.get().setNumReviews(newProductDetails.getNumReviews());

         Product updatedaProduct = repository.save(product.get());

         return updatedaProduct;

     // Update the existing product with new data

     }

}
