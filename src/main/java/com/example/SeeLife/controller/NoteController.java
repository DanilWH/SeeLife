package com.example.SeeLife.controller;

import java.time.LocalDate;
import java.util.List;

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
import com.example.SeeLife.model.Note;
import com.example.SeeLife.model.User;
import com.example.SeeLife.repository.DayRepo;
import com.example.SeeLife.repository.NoteRepo;

@Controller
public class NoteController {
    
    @Autowired
    private DayRepo dayRepo;
    @Autowired
    private NoteRepo noteRepo;

    @GetMapping("/day/id/{dayId}")
    public String notes(
            @PathVariable(value="dayId") Long dayId,
            @AuthenticationPrincipal User current_user,
            Model model
    ) {
        // check if the current user is the owner of a certain day.
        Day day = this.dayRepo.findById(dayId).get();
        CommonOperations.checkOwner(current_user.getId(), day.getOwner().getId());
        // find the note that belong to the day.
        List<Note> notes = this.noteRepo.findNoteByDayId(dayId);
        model.addAttribute("notes", notes);
        
        return "notes";
    }
    
    @GetMapping("/new_note")
    public String new_note() {
        return "new_note";
    }
    
    @PostMapping("/new_note")
    public String save_note(
            @RequestParam String text,
            @AuthenticationPrincipal User current_user
    ) {
        // create a new day if it doesn't exist yet.
        Day day = this.dayRepo.findByLocalDate(LocalDate.now());
        
        if (day == null) {
            Day new_day = new Day(
                    (text.length() > 20)? text.substring(0, 17) + "..." : text,
                    current_user
                );
            day = this.dayRepo.save(new_day);
        }
        
        // create a new note and save it.
        Note new_note = new Note(text, day);
        this.noteRepo.save(new_note);
        
        
        return "redirect:/day/id/" + day.getId();
    }
}
