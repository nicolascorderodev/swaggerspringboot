package com.gse.firma.firma_api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Base64;

public class Base64ValidoValidator implements ConstraintValidator<Base64Valido, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }

        try {
            byte[] decoded = Base64.getDecoder().decode(value);
            String recodificado = Base64.getEncoder().encodeToString(decoded);
            return recodificado.equals(value.replaceAll("\\s", ""));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
