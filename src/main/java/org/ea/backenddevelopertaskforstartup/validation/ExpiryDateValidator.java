package org.ea.backenddevelopertaskforstartup.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExpiryDateValidator implements ConstraintValidator<ValidExpiryDate, String> {

    private static final String EXPIRY_DATE_REGEX = "^(0[1-9]|1[0-2])/\\d{2}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches(EXPIRY_DATE_REGEX);
    }
}
