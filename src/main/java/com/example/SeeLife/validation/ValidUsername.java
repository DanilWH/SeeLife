package com.example.SeeLife.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = ConstraintUsernameValidator.class)
@Documented
public @interface ValidUsername {
    String message() default "Invalid username.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
