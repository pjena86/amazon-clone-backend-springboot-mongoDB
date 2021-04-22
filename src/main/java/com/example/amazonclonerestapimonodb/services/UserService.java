package com.example.amazonclonerestapimonodb.services;


import com.example.amazonclonerestapimonodb.models.UserModel;
import com.example.amazonclonerestapimonodb.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService
{

@Autowired
private UserRepository userRepository;

    /************************************************************************************/
    // BcryptPasswordEncoder
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserModel addUser(UserModel user)
    {

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        UserModel createdUser = userRepository.insert(user);

        return createdUser;
    }
/************************************************************************************/
//Get all user service
public List<UserModel> getUser() {


    return userRepository.findAll();
}
    /**********************************************************************************************/
    //Get a User Service
    public Optional<UserModel> getAUser(String id) throws Exception
    {

        Optional<UserModel> user = userRepository.findById(id);
        //if the product reference variable does not contain a value then
        if(!user.isPresent())
        {
            throw new Exception("User with " + id  + " is not found");

        }
        return user; //Optional says it could contain a value or not contain a value
    }
    /**********************************************************************************************/
    //Delete a User service
    /***********************************************************************************************/


    public Optional<UserModel> deleteAUser(String id) throws Exception
    {
        Optional<UserModel> userModel = userRepository.findById(id);

        userRepository.deleteById(id);

        if (!userModel.isPresent())
        {
            throw new Exception("User with " + id  + " is not found");
        }
        return userModel;
    }

    /**********************************************************************************************/
    //Find a User by email
    /***********************************************************************************************/
    public UserModel findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       UserModel userReturned = userRepository.findByUsername(username);

       String userNm = userReturned.getUsername();
       String password = userReturned.getPassword();

       return  new User(userNm, password, new ArrayList<>());
    }


}




