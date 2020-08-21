package com.example.SeeLife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.SeeLife.model.Day;
import com.example.SeeLife.model.User;
import com.example.SeeLife.repository.DayRepo;

@Controller
public class DaysController {
    @Autowired
    private DayRepo dayRepo;
    
    @GetMapping("/")
    public String greeting(
            @AuthenticationPrincipal User current_user,
            Model model
    ) {
        Iterable<Day> days = this.dayRepo.findByOwnerId(current_user.getId());
        model.addAttribute("days", days);
        
        return "days";
    }

}
