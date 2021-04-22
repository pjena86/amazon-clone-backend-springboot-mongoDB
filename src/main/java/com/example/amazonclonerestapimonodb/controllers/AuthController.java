package com.example.amazonclonerestapimonodb.controllers;


import com.example.amazonclonerestapimonodb.models.UserModel;
import com.example.amazonclonerestapimonodb.services.CustomizedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AuthController

    {
        @Autowired
        private AuthenticationManager authenticationManager;
        /****************************************************************************/
        //User Login
        /****************************************************************************/

        @PostMapping(value = "/auth", consumes = {
                MediaType.APPLICATION_JSON_VALUE
        })
        public ResponseEntity login (@RequestBody UserModel user){

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            var customizedResponse = new CustomizedResponse("User is logged in successfully", null);

            return new ResponseEntity(customizedResponse, HttpStatus.OK);

        } catch (BadCredentialsException ex) {

            var customizedResponse = new CustomizedResponse("Login Failed! Username and/or Password entered were incorrect", null);

            return new ResponseEntity(customizedResponse, HttpStatus.UNAUTHORIZED);
        }
    }

    /*******************************************************************************************/
    //Register a new user
     /******************************************************************************************/

//     @PostMapping("/register")
//     public ResponseEntity userRegister(@RequestBody UserModel user) {
//         UserModel userExists = userService.findUserByEmail(user.getEmail());
//         if (userExists != null) {
//             throw new BadCredentialsException("User with username: " + user.getEmail() + " already exists");
//         }
//         userService.saveUser(user);
//         Map<Object, Object> model = new HashMap<>();
//         model.put("message", "User registered successfully");
//         return ok(model);
//     }

    }
