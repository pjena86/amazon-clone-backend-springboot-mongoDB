package com.example.amazonclonerestapimonodb.models;

import org.springframework.data.annotation.Id;


public class Product {

    @Id
    private String id;
    private String title;
    private String category_name;
    private String image;
    private double price;
    private int countInStock;
    private String brand;
    private double rating;
    private int numReviews;
    private String description;
    private boolean IsBestSeller;

    public Product() {
    }

    public Product(String id, String title, String category_name, String image, double price, int countInStock, String brand, double rating, int numReviews, String description, boolean isBestSeller) {
        this.id = id;
        this.title = title;
        this.category_name = category_name;
        this.image = image;
        this.price = price;
        this.countInStock = countInStock;
        this.brand = brand;
        this.rating = rating;
        this.numReviews = numReviews;
        this.description = description;
        IsBestSeller = isBestSeller;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCountInStock() {
        return countInStock;
    }

    public void setCountInStock(int countInStock) {
        this.countInStock = countInStock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isBestSeller() {
        return IsBestSeller;
    }

    public void setBestSeller(boolean bestSeller) {
        IsBestSeller = bestSeller;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", category_name='" + category_name + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", countInStock=" + countInStock +
                ", brand='" + brand + '\'' +
                ", rating=" + rating +
                ", numReviews=" + numReviews +
                ", description='" + description + '\'' +
                ", IsBestSeller=" + IsBestSeller +
                '}';
    }
}

