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

@Target({FIELD, METHOD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = ConstraintPasswordValidator.class)
@Documented
public @interface ValidPassword {
    String message() default "Invalid password!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
