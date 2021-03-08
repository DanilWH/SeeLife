package com.example.SeeLife.validation;

import com.example.SeeLife.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConstraintUsernameValidator implements ConstraintValidator<ValidUsername, String> {

    @Autowired
    private UserRepo userRepo;

    private final String USERNAME_PATTERN = "[^a-zA-Z\\@\\.\\+\\-\\_\\d]";

    @Override
    public void initialize(ValidUsername constraintAnnotation) {

    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (usernameExists(username)) {
            String message = "The username is already in use.";

            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();

            return false;
        }

        return isUsernameValid(username);
    }

    private boolean isUsernameValid(String username) {
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pattern.matcher(username);

        // if the matcher has found a character that must not be in a username,
        // the matcher return true which means that the username is invalid.
        // But because we check if the username is VALID (not INVALID),
        // we need to convert the boolean value.
        return !matcher.find();
    }

    private boolean usernameExists(String username) {
        return this.userRepo.findByUsername(username) != null;
    }
}
