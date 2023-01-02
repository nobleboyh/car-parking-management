package com.hoang.carmanagement.security.service;

import com.hoang.carmanagement.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceCustomized implements UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public UserDetailsServiceCustomized(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findUserByEmail(username);
    }
}
