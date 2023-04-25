package com.example.demospringmvc.annotation.validator;

import com.example.demospringmvc.annotation.AccessibleGenre;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collections;
import java.util.List;

/**
 * @author Cholpon Kurmanalieva
 */

public class AccessibleGenreValidator implements ConstraintValidator<AccessibleGenre, String>{
    private final List<String> notAllowedGenres = Collections.singletonList("horror");

    @Override
    public boolean isValid(String genre, ConstraintValidatorContext constraintValidatorContext) {
        if (notAllowedGenres.contains(genre.toLowerCase()))
            return false;
        return true;
    }
}