package com.i4o.dms.kubota.validation.validator.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.i4o.dms.kubota.validation.validator.NoSpecialCharactersValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author suraj.gaur
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoSpecialCharactersValidator.class)
public @interface NoSpecialCharacters {
    String message() default "Special characters are not allowed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
