package com.example.SeeLife;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;

import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.SeeLife.model.Note;
import com.example.SeeLife.model.User;
import com.example.SeeLife.repository.UserRepo;

public interface CommonOperations {
    
    public static final String fieldRequiredMsg = "This field is required!";
    
    public static void checkOwner(Long current_userId, Long ownerId) {
        if (!current_userId.equals(ownerId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
    
    public static boolean fieldIsEmpty(String field) {
        return field != null && field.trim().isEmpty();
    }
    
    public static boolean isRelevant(LocalDate localDate) {
        return localDate.equals(LocalDate.now());
    }
    
    public static void checkRelevant(LocalDate localDate) {
        if (!isRelevant(localDate))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
    
    public static String isUsernameValid(String username, UserRepo userRepo) {
        /*** 
         * Checks if the username is valid.
         * If it's not then it returns a message what's incorrect.
        ***/
        
        User userFromDB = userRepo.findByUsername(username);

        if (userFromDB != null)
            return "The user already exsists!";
        
        char[] username_chars = username.toCharArray();
        for (int i = 0, len = username_chars.length; i < len; i++) {
            // if the current symbol is in the range of numbers by ascii. 
            boolean numerics = username_chars[i] > 47 && username_chars[i] < 58;
            // if the current symbol is in the range of upper letters by ascii.
            boolean upper_letters = username_chars[i] > 64 && username_chars[i] < 91;
            // if the current symbol is in the range of lower letters by ascii.
            boolean lower_letters = (username_chars[i] > 96 && username_chars[i] < 123);
            // if the current symbol is one of the special symbols.
            boolean special_symbols = username_chars[i] == '.' || username_chars[i] == '+' ||
                                      username_chars[i] == '-' || username_chars[i] == '_' ||
                                      username_chars[i] == '@';
            // is the current symbol valid?
            boolean valid_symbol = numerics || upper_letters || lower_letters || special_symbols; 
            
            // if the current symbol isn't valid then ...
            if (!valid_symbol)
                // ... return the appropriate message.
                return "Enter a valid username. This value may contain only letters,"
                        + "numbers, and @/./+/-/_ characters.";
        }
        
        // return null as a result if the username is valid.
        return null;
    }
    
    public static String isPasswordValid(String password, String password_confirm) {
        /*** 
         * Checks if the password is valid.
         * If it's not then it returns a message what's incorrect.
        ***/
        
        final int passwordMinLen = 8;
        
        if (fieldIsEmpty(password))
            return "This password is entirely of spaces.";
        if (password.equals(password_confirm) == false)
            return "Your passwords didn't match. Please try again.";
        if (password.length() < passwordMinLen || password_confirm.length() < passwordMinLen)
            return "This password is too short. It must contain at least "
            + passwordMinLen + " characters.";
        
        boolean passwordContainsNumericsOnly = true;
        char[] password_chars = password.toCharArray();
        for (int i = 0, len = password_chars.length; i < len; i++) {
            boolean numerics = password_chars[i] > 47 && password_chars[i] < 58;
            
            if (!numerics) {
                passwordContainsNumericsOnly = false;
                break;
            }
        }
        if (passwordContainsNumericsOnly) return "This password is entirely numeric.";
        
        // return null as a result if the password is valid.
        return null;
    }
    
    public static List<String> getImages(List<String> files) {
        List<String> images = new ArrayList<String>();
        
        for (String file : files) {
            File fileObject = new File(file);
            
            String mimetype= new MimetypesFileTypeMap().getContentType(fileObject);
            String type = mimetype.split("/")[0];
            
            if(type.equals("image"))
                images.add(fileObject.getName());
        }
        
        return images;
    }
    
    public static String getFileType(MultipartFile file) {
        File fileObject = new File(file.getOriginalFilename());
        
        String mimetype = new MimetypesFileTypeMap().getContentType(fileObject);
        String type = mimetype.split("/")[0];
        
        return type;
    }
    
    public static void uploadFiles(
            List<MultipartFile> files, String uploadPath, Note note
    ) throws IOException {
        
        // check if the user didn't upload at least one file.
        if (files == null || files.isEmpty())
            return;
        
        // if the user uploaded some files save files into different tables and
        // directories on the server depending on the type of the file.
        for (MultipartFile file : files) {
            List<String> fileDBTable = new ArrayList<String>();
            String directory = "";
            
            if (getFileType(file).equals("image")) {
                directory = uploadPath + "/images";
                fileDBTable = note.getImages(); 
            }
            else if (getFileType(file).equals("video")) {
                directory = uploadPath + "/videos";
                fileDBTable = note.getVideos();
            }
            else if (getFileType(file).equals("audio")) {
                directory = uploadPath + "/audios";
                fileDBTable = note.getAudios();
            }
            else {
                directory = uploadPath + "/otherFiles";
                fileDBTable = note.getOtherFiles();
            }
            
            // make the directory if it doesn't exist.
            File uploadDir = new File(directory);
            
            if (!uploadDir.exists())
                uploadDir.mkdirs();
            
            // create a random uuid for the file.
            String uuidFile = UUID.randomUUID().toString();
            // concatinate the uuid name with the file name.
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            
            // transrer the current file to the directory.
            file.transferTo(new File(directory + "/" + resultFilename));
            
            System.out.println(fileDBTable);
            // save the filename into the database.
            fileDBTable.add(resultFilename);
        }
    }
}
