package com.i4o.dms.kubota.validation.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.i4o.dms.kubota.validation.validator.annotation.NoSpecialCharacters;

/**
 * @author suraj.gaur
 */
public class NoSpecialCharactersValidator implements ConstraintValidator<NoSpecialCharacters, String> {
    private static final Pattern SPECIAL_CHARACTERS = Pattern.compile("[^a-zA-Z0-9]");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !SPECIAL_CHARACTERS.matcher(value).find();
    }
}
