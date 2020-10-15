package com.example.SeeLife.controller;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.SeeLife.CommonOperations;
import com.example.SeeLife.model.Day;
import com.example.SeeLife.model.User;
import com.example.SeeLife.repository.DayRepo;

@Controller
public class DaysController {
    
    @Autowired
    private DayRepo dayRepo;
    
    @Autowired
    private NoteController noteController;
    
    @GetMapping("/")
    public String greeting(
            @AuthenticationPrincipal User current_user,
            Model model
    ) {
        Iterable<Day> days = this.dayRepo.findByOwnerIdOrderByLocalDateDesc(current_user.getId());
        model.addAttribute("days", days);
        
        return "days";
    }
    
    @PostMapping("/edit_day/id/{dayId}")
    public String edit_day(
            @PathVariable(value="dayId") Long dayId,
            @AuthenticationPrincipal User current_user,
            @RequestParam String dayTitle,
            Model model
    ) {
        Day day = this.dayRepo.findById(dayId).orElseThrow(() -> new NoResultException());
        
        // check if the user wants to change the day title at the day of its creation.
        CommonOperations.checkRelevant(day.getLocalDate());
        
        // check if the text field is empty.
        if (CommonOperations.fieldIsEmpty(dayTitle)) {
            model.addAttribute("message", CommonOperations.fieldRequiredMsg);
            return noteController.notes(day.getId(), current_user, model);
        }
        
        
        CommonOperations.checkOwner(current_user.getId(), day.getOwner().getId());
        
        day.setTitle(dayTitle);
        this.dayRepo.save(day);
        
        return "redirect:/day/id/" + day.getId();
    }

}
