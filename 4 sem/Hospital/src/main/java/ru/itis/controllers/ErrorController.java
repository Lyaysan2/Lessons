package ru.itis.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.itis.exceptions.ErrorEntity;
import ru.itis.exceptions.ValidationException;


@ControllerAdvice
public class ErrorController {
    @ExceptionHandler
    public ResponseEntity<ErrorEntity> handle(ValidationException exception) {
        return ResponseEntity
                .status(exception.getEntity().getStatus())
                .body(exception.getEntity());
    }
}
