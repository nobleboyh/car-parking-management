package com.hoang.carmanagement.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
//TODO: Push user data to DB
public class UserDao {

    private final List<UserDetails> APPLICATION_USERS;


    @Autowired
    public UserDao(PasswordEncoder passwordEncoder) {
        this.APPLICATION_USERS = List.of(
                new User(
                        "hoang@gmail.com",
                        passwordEncoder.encode( "pw1"),
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
                ),
                new User(
                        "hoang2@gmail.com",
                        passwordEncoder.encode( "pw2"),
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
                )
        );
    }


    public UserDetails findUserByEmail(String email) {
        return APPLICATION_USERS.stream().filter(u -> u.getUsername().equals(email)).findFirst().orElseThrow(
                () -> new UsernameNotFoundException("No user found")
        );
    }
}