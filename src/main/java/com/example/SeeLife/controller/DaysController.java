package com.example.SeeLife.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DaysController {
    
    @GetMapping("/")
    public String greeting() {
        return "days";
    }

}
