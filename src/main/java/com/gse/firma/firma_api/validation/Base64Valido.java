package com.gse.firma.firma_api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = Base64ValidoValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Base64Valido {
    String message() default "No es una cadena base64 válida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
