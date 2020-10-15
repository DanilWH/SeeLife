package com.example.SeeLife.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/day/id/{dayId}")
    public String notes(
            @PathVariable(value="dayId") Long dayId,
            @AuthenticationPrincipal User current_user,
            Model model
    ) {
        // check if the current user is the owner of a certain day.
        Day day = this.dayRepo.findById(dayId).orElseThrow(() -> new NoResultException());
        CommonOperations.checkOwner(current_user.getId(), day.getOwner().getId());
        // find the note that belong to the day.
        List<Note> notes = this.noteRepo.findByDayIdOrderByLocalTime(dayId);
        
        model.addAttribute("day", day);
        model.addAttribute("notes", notes);
        model.addAttribute(
                "isRelevant",
                CommonOperations.isRelevant(day.getLocalDate())
            );
        
        return "notes";
    }
    
    @GetMapping("/new_note")
    public String new_note() {
        return "new_note";
    }
    
    @PostMapping("/new_note")
    public String save_note(
            @AuthenticationPrincipal User current_user,
            @Valid @RequestBody String text,
            @RequestParam("files") List<MultipartFile> files,
            Model model
    ) throws IOException {
        
        
        // create a new day if it doesn't exist yet.
        Day day = this.dayRepo.findByLocalDateAndOwnerId(LocalDate.now(), current_user.getId());
        
        if (day == null) {
            Day new_day = new Day(
                    (text.length() > 20)? text.substring(0, 17) + "..." : text,
                    current_user
                );
            
            day = this.dayRepo.save(new_day);
        }
        
        // create a new note and save it.
        Note new_note = new Note(text, day);
        CommonOperations.uploadFiles(files, this.uploadPath, new_note);
        
        this.noteRepo.save(new_note);
        
        
        return "redirect:/day/id/" + day.getId();
    }
    
    @GetMapping("/edit_note/id/{noteId}")
    public String edit_note(
            @PathVariable(value="noteId") Long noteId,
            @AuthenticationPrincipal User current_user,
            Model model
    ) {
        Note note = this.noteRepo.findById(noteId).orElseThrow(() -> new NoResultException());
        
        CommonOperations.checkOwner(current_user.getId(), note.getDay().getOwner().getId());
        // check if the user wants to edit the note of the day of its creation.
        CommonOperations.checkRelevant(note.getDay().getLocalDate());
        
        model.addAttribute("note", note);
        
        return "edit_note";
    }
    
    @PostMapping("/edit_note/id/{noteId}")
    public String update_note(
            @PathVariable(value="noteId") Long noteId,
            @AuthenticationPrincipal User current_user,
            @RequestParam String text,
            @RequestParam(required=false) Set<String> deletingFiles,
            @RequestParam("files") List<MultipartFile> uploadingFiles,
            Model model
    ) throws IOException {
        Note note = this.noteRepo.findById(noteId).orElseThrow(() -> new NoResultException());
        
        CommonOperations.checkOwner(current_user.getId(), note.getDay().getOwner().getId());
        // check if the user wants to edit the note of the day of its creation.
        CommonOperations.checkRelevant(note.getDay().getLocalDate());
        
        // check if the field isn't empty.
        if (CommonOperations.fieldIsEmpty(text)) {
            model.addAttribute("message", CommonOperations.fieldRequiredMsg);
            return edit_note(noteId, current_user, model);
        }
        
        // if everything is valid we update the text of the note
        note.setText(text);
        // remove the file that were selected.
        CommonOperations.deleteFiles(deletingFiles, this.uploadPath, note);
        // upload new files.
        CommonOperations.uploadFiles(uploadingFiles, this.uploadPath, note);
        // and update it in the database.
        this.noteRepo.save(note);
        
        return "redirect:/day/id/" + note.getDay().getId();
    }
}
