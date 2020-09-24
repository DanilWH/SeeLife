package com.example.SeeLife.controller;

import java.util.Collections;
import java.util.Set;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.SeeLife.model.Role;
import com.example.SeeLife.model.User;
import com.example.SeeLife.repository.UserRepo;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    
    @Autowired
    private UserRepo userRepo; 
    
    @GetMapping("/users")
    public String users(Model model) {
        Iterable<User> users = this.userRepo.findAll();
        model.addAttribute("users", users);
        
        return "admin/users";
    }
    
    @GetMapping("/edit_user/{userId}")
    public String edit_user(
            @PathVariable(value="userId") Long userId,
            Model model
    ) {
        User user = this.userRepo.findById(userId).get();
        model.addAttribute("user", user);
        model.addAttribute("role_values", Role.values());
        
        return "admin/edit_user";
    }
    
    @PostMapping("/edit_user/{userId}")
    public String update_user(
            @PathVariable("userId") Long userId,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam(required = false) Set<Role> chosen_roles
    ) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new NoResultException());
        
        // we want to change the user data only if it has been changed,
        // otherwise we don't overwrite the user data.
        boolean isChanged = false;
        if (!user.getUsername().equals(username)) {
            user.setUsername(username);
            isChanged = true;
        }
        if (!user.getPassword().equals(password)) {
            user.setPassword(password);
            isChanged = true;
        }
        if (!user.getRoles().equals(chosen_roles)) {
            user.setRoles(chosen_roles);
            isChanged = true;
        }
        
        // update the repository only if the user has changed something.
        if (isChanged)
            this.userRepo.save(user);
        
        return "redirect:/admin/users";
    }
}