package com.example.SeeLife.validation;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class ConstraintPasswordValidator implements ConstraintValidator<ValidPassword, String>  {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                // the length must be between 8 and 20.
                new LengthRule(8, 20),

                // at least on english character.
                new CharacterRule(EnglishCharacterData.LowerCase, 1),

                // at least one digit.
                new CharacterRule(EnglishCharacterData.Digit, 1),

                // rejects passwords that contain a sequence of >= 5 characters alphabetical  (e.g. abcdef)
                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),

                // rejects passwords that contain a sequence of >= 5 characters numerical (e.g. 12345)
                new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false),

                // no whitespace.
                new WhitespaceRule()
        ));

        RuleResult ruleResult = validator.validate(new PasswordData(password));

        if (ruleResult.isValid()) {
            return true;
        }

        List<String> messages = validator.getMessages(ruleResult);
        String messageTemplate = String.join("<br />", messages);

        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
