package com.example.SeeLife.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.SeeLife.repository.UserRepo;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    
    private UserRepo userRepo; 
    
    @GetMapping("/users")
    public String greeting() {
        
        return "admin_users";
    }
}