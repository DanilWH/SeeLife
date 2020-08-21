package com.example.SeeLife;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.SeeLife.model.Day;
import com.example.SeeLife.model.User;
import com.example.SeeLife.repository.UserRepo;

public interface CommonOperations {
    
    public static final String fieldRequiredMsg = "This field is required!";
    
    public static void checkOwner(Long userId, Long ownerId) {
        if (!userId.equals(ownerId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
    
    public static String fieldIsEmpty(String field) {
        if (field != null && field.trim().isEmpty())
            return fieldRequiredMsg;
        else
            return null;
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
        
        if (fieldIsEmpty(password) != null)
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
}
