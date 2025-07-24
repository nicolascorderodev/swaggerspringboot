package com.gse.firma.firma_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarErroresDeValidacion(MethodArgumentNotValidException ex) {
        List<String> errores = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.add("El campo '" + error.getField() + "' " + error.getDefaultMessage());
        }

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("codigo", 400);
        respuesta.put("mensaje", "Error en la solicitud");
        respuesta.put("detalles", errores);

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> manejarErroresArgumento(IllegalArgumentException ex) {
        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("codigo", 400);
        respuesta.put("mensaje", "Error en la solicitud");
        respuesta.put("detalles", Collections.singletonList(ex.getMessage()));

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }
}
