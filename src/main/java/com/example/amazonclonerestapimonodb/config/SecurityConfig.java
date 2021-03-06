package com.example.amazonclonerestapimonodb.config;

import com.example.amazonclonerestapimonodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private UserService userService;


    //Allows to configure authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService);

    }

    //This allows us to configure our authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/users/**").permitAll()
                .antMatchers("/products/**").permitAll()
                .antMatchers("/categories/**").permitAll()
                .antMatchers("/auth").permitAll()
                .anyRequest().authenticated();

    }

    //Bcrypt
    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return  new BCryptPasswordEncoder();
    }



    //this calles the authentication manager builder
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
