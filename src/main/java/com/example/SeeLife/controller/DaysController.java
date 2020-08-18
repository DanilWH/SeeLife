package com.example.SeeLife.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.SeeLife.model.Day;
import com.example.SeeLife.repository.DayRepo;

@Controller
public class DaysController {
    @Autowired
    private DayRepo dayRepo;
    
    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        Iterable<Day> days = this.dayRepo.findAll();
        model.put("days", days);
        
        return "days";
    }

}
