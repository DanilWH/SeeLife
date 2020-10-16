package com.example.SeeLife.controller;

import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.SeeLife.CommonOperations;
import com.example.SeeLife.model.Day;
import com.example.SeeLife.model.Note;
import com.example.SeeLife.model.Role;
import com.example.SeeLife.model.User;
import com.example.SeeLife.repository.DayRepo;
import com.example.SeeLife.repository.NoteRepo;
import com.example.SeeLife.repository.UserRepo;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    
    @Value("${upload.path}")
    private String uploadPath;
    
    @Autowired
    private UserRepo userRepo; 
    @Autowired
    private DayRepo dayRepo;
    @Autowired
    private NoteRepo noteRepo;
    
    @GetMapping("/users")
    public String users(
            @RequestParam(required=false, defaultValue="") String user_filter,
            Model model
    ) {
        Iterable<User> users;
        
        if (user_filter != null && !user_filter.isEmpty())
            users = this.userRepo.findByUsernameContaining(user_filter);
        else
            users = this.userRepo.findAll();
            
        model.addAttribute("users", users);
        
        return "admin/users";
    }
    
    @GetMapping("/edit_user/{userId}")
    public String edit_user(
            @PathVariable(value="userId") Long userId,
            Model model
    ) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new NoResultException());
        model.addAttribute("user", user);
        model.addAttribute("role_values", Role.values());
        
        return "admin/edit_user";
    }
    
    @PostMapping("/edit_user/{userId}")
    public String update_user(
            @PathVariable("userId") Long userId,
            @RequestParam String username,
            @RequestParam(required = false) Set<Role> chosen_roles,
            Model model
    ) {
        // check if the username is valid first.
        String username_msg = CommonOperations.isUsernameValid(username, this.userRepo);
        if (username_msg != null) {
            model.addAttribute("username_msg", username_msg);
            return edit_user(userId, model);
        }
        
        User user = this.userRepo.findById(userId).orElseThrow(() -> new NoResultException());
        
        // we want to change the user data only if it has been changed,
        // otherwise we don't overwrite the user data.
        boolean isChanged = false;
        if (!user.getUsername().equals(username)) {
            user.setUsername(username);
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
    
    @GetMapping("/user/{userId}/delete")
    public String delete_user_confirmation(
            @PathVariable("userId") Long userId,
            Model model
    ) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new NoResultException());
        
        model.addAttribute("user", user);
        
        return "admin/delete_user_confirmation";
    }
    
    @PostMapping("/user/{userId}/delete")
    public String delete_user(
            @PathVariable("userId") Long userId
    ) {
        // first of all, we have to delete all the user files from the server.
        // a list of file types that the user can store.
        String[] fileTypes = {"image", "video", "audio", "document"};
        
        // get all the user days.
        Iterable<Day> userDays = this.dayRepo.findByOwnerId(userId);
        
        // go throw all the user days.
        for (Day day : userDays) {
            // get all notes of each day.
            Iterable<Note> dayNotes = this.noteRepo.findByDayId(day.getId());
            
            // got throw all the notes of the day that the cycle iteration is currently on.
            for (Note note : dayNotes) {
                // go throw all file types. 
                for (String fileType : fileTypes) {
                    // get the appropriate storage according to the current type.
                    List<String> filesStorage = note.getFilesByFileType(fileType);
                    
                    // go throw the list of filenames.
                    for (String filename : filesStorage) {
                        // find out the path to the current file.
                        String path = this.uploadPath + "/" + fileType + "s/" + filename;
                        CommonOperations.deleteFileFromServer(path);
                    }
                }
            }
        }
        
        // delete the user from the database.
        this.userRepo.deleteById(userId);
        
        return "redirect:/admin/users";
    }
    
    @GetMapping("/edit_user/{userId}/password")
    public String edit_user_password(
            @PathVariable("userId") Long userId,
            Model model
    ) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new NoResultException());
        
        model.addAttribute("username", user.getUsername());
        model.addAttribute("userId", userId);
        
        return "admin/edit_user_password";
    }
    
    @PostMapping("edit_user/{userId}/password")
    public String update_user_password(
            @PathVariable("userId") Long userId,
            @RequestParam String password,
            @RequestParam String password_confirm,
            Model model
    ) {
        // check if the password is valid first.
        String passwordMsg= CommonOperations.isPasswordValid(password, password_confirm);
        if (passwordMsg != null) {
            model.addAttribute("password_msg", passwordMsg);
            return edit_user_password(userId, model);
        }
        
        User user = this.userRepo.findById(userId).orElseThrow(() -> new NoResultException());
        
        user.setPassword(password);
        this.userRepo.save(user);
        
        return "redirect:/admin/edit_user/" + userId;
    }
}