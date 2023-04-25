package com.example.demospringmvc.annotation;

import com.example.demospringmvc.annotation.validator.AccessibleGenreValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Cholpon Kurmanalieva
 */

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccessibleGenreValidator.class)
public @interface AccessibleGenre {
    String message() default "This genre is not allowed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}