package com.example.SeeLife.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.SeeLife.CommonOperations;
import com.example.SeeLife.model.Role;
import com.example.SeeLife.model.User;
import com.example.SeeLife.repository.UserRepo;

@Controller
public class RegistrationController {
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    
    @PostMapping("/registration")
    public String add_user(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String password_confirm,
            Map<String, Object> model
    ) {
        // check if the user already exists.
        if (this.userRepo.findByUsername(username) != null) {
            model.put("username_msg", "The user already exists!");
            System.out.println(model.get("username_msg"));
            return registration();
        }
        
        // check if the username and the password correct.
        model.put("username_msg", CommonOperations.isUsernameValid(username, userRepo));
        model.put("password_msg", CommonOperations.isPasswordValid(password, password_confirm));
        if (model.get("username_msg") != null || model.get("password_msg") != null)
            return registration();
        
        // if everything is fine, save the user into the database.
        User newUser = new User();

        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRoles(Collections.singleton(Role.USER));
        newUser.setActive(true);
        
        this.userRepo.save(newUser);
        
        return "redirect:/";
    }
}
