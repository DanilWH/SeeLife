package com.example.SeeLife.controller;

import java.time.LocalDate;
import java.util.List;

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
    public String show_days(
            @AuthenticationPrincipal User currentUser,
            @RequestParam(required=false, defaultValue="") String startDate,
            @RequestParam(required=false, defaultValue="") String endDate,
            Model model
    ) {
        List<Day> days;
        
        // if both date fields are filled up.
        if ((startDate != null && endDate != null) &&
                (!startDate.isEmpty() && !endDate.isEmpty()))
        {
            days = this.dayRepo.findByLocalDateBetweenAndOwnerId(LocalDate.parse(startDate),
                                                                 LocalDate.parse(endDate),
                                                                 currentUser.getId());
            
            model.addAttribute("startDate", CommonOperations.getFormattedLocalDate(startDate));
            model.addAttribute("endDate", CommonOperations.getFormattedLocalDate(endDate));
        }
        // if the startDate field is filled up only. 
        else if (startDate != null && !startDate.isEmpty()) {
            days = this.dayRepo.findByLocalDateGreaterThanEqualAndOwnerId(LocalDate.parse(startDate),
                                                               currentUser.getId());
            
            model.addAttribute("startDate", CommonOperations.getFormattedLocalDate(startDate));
            model.addAttribute("endDate",
                    CommonOperations.getFormattedLocalDate(LocalDate.now()) + " (today)");
        }
        // if the endDate field is filled up only.
        else if (endDate != null && !endDate.isEmpty()) {
            days = this.dayRepo.findByLocalDateLessThanEqualAndOwnerId(LocalDate.parse(endDate),
                                                                currentUser.getId());
            
            // get the local date of the very first day.
            model.addAttribute("startDate", days.get(0).getFormattedLocalDate() + " (first day)");
            model.addAttribute("endDate", CommonOperations.getFormattedLocalDate(endDate));
        }
        // if none of the fields are filled up.
        else
            days = this.dayRepo.findByOwnerIdOrderByLocalDateDesc(currentUser.getId());
        
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
