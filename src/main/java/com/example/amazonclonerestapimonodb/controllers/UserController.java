package com.example.amazonclonerestapimonodb.controllers;


import com.example.amazonclonerestapimonodb.models.UserModel;
import com.example.amazonclonerestapimonodb.services.UserService;
import com.example.amazonclonerestapimonodb.services.CustomizedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController
{
    @Autowired
    private UserService service;//service reference variable

    /*****************************************************************************/
    //Create Or Post a User
    /****************************************************************************/
    @PostMapping(value = "/users", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity createUser(@RequestBody UserModel user) {

        //service.addUser(user);
        var customizedResponse = new CustomizedResponse("A new user created successfully" , Collections.singletonList(service.addUser(user)));

        return new ResponseEntity(user, HttpStatus.OK);

    }
    /****************************************************************************/
    //Get all users
    /****************************************************************************/
    @GetMapping("/users")

    //receive object of any type and also return status code
    public ResponseEntity getUsers() {

        var customizedResponse = new CustomizedResponse("A list of users", service.getUser());

        // always send back status code->Header and json
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    /******************************************************************************************/
    //Return a specific user
    /******************************************************************************************/

    @GetMapping("/users/{id}")

    //ResponseEntity receives object of any type and also return status code

    public ResponseEntity getAUser(@PathVariable("id") String id) {

        CustomizedResponse customizedResponse = null;
        try {

            customizedResponse = new CustomizedResponse("User found with id " + id, Collections.singletonList(service.getAUser(id)));

        } catch (Exception e) {

            customizedResponse = new CustomizedResponse(e.getMessage(), null);

            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    /******************************************************************************************/
    //Delete a specific user
    /******************************************************************************************/

    @DeleteMapping("/users/{id}")
    //ResponseEntity receives object of any type and also return status code

    public ResponseEntity deleteACustomer(@PathVariable("id") String id)
    {
        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse("User with  id " + id + " deleted successfully", Collections.singletonList(((service.deleteAUser(id)))));

        } catch (Exception e) {

            customizedResponse = new CustomizedResponse(e.getMessage(), null);

            return new ResponseEntity(customizedResponse, HttpStatus.OK);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
    }






}
