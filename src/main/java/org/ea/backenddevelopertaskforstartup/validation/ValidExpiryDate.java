package org.ea.backenddevelopertaskforstartup.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExpiryDateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidExpiryDate {
    String message() default "Invalid expiry date format. Use MM/YY.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
