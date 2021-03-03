package com.example.SeeLife.service;

import com.example.SeeLife.model.Role;
import com.example.SeeLife.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SeeLife.repository.UserRepo;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepo.findByUsername(username);
    }

    public void addUser(User user) {
        // if everything is fine, save the user into the database.
        User newUser = new User();

        newUser.setUsername(user.getUsername());
        newUser.setPassword(this.passwordEncoder.encode(user.getPassword()));
        newUser.setRoles(Collections.singleton(Role.USER));
        newUser.setActive(true);

        this.userRepo.save(newUser);
    }

    public User findByUsername(String username) {
        return this.userRepo.findByUsername(username);
    }

}
