package com.example.amazonclonerestapimonodb.models;

import org.springframework.data.annotation.Id;

public class Category {

    @Id
    private String id;
    private String category_name;
    private String category_img;

    public Category() {
    }

    public Category(String id, String category_name, String category_img) {
        this.id = id;
        this.category_name = category_name;
        this.category_img = category_img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_img() {
        return category_img;
    }

    public void setCategory_img(String category_img) {
        this.category_img = category_img;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", category_name='" + category_name + '\'' +
                ", category_img='" + category_img + '\'' +
                '}';
    }
}
