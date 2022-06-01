package ru.itis.controller.handlers;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.itis.exception.ServiceException;

import java.util.Collections;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationErrorMessage.class)
    public final ResponseEntity<ResponseErrorMessage> onValidationException(ValidationErrorMessage exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseErrorMessage.builder()
                        .errors(Collections.singletonList(new ResponseErrorMessage.Error(exception.getFieldName(), exception.getCode(), exception.getDetailMessage())))
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ResponseErrorMessage> notValidException(MethodArgumentNotValidException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseErrorMessage.builder()
                        .errors(exception.getBindingResult().getFieldErrors().stream()
                                .map(fieldError -> new ResponseErrorMessage.Error(fieldError.getField(), fieldError.getCode(), fieldError.getDefaultMessage()))
                                .collect(Collectors.toList()))
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionMessage> onAllExceptions(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionMessage.builder()
                        .message(exception.getMessage())
                        .exceptionName(exception.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ExceptionMessage> onAccessDeniedException(AccessDeniedException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ExceptionMessage.builder()
                        .message(exception.getMessage())
                        .exceptionName(exception.getClass().getSimpleName())
                        .build());
    }


    @ExceptionHandler(ServiceException.class)
    public final ResponseEntity<ExceptionMessage> onAccountExceptionExceptions(ServiceException serviceException) {
        return ResponseEntity.status(serviceException.getHttpStatus())
                .body(ExceptionMessage.builder()
                        .message(serviceException.getMessage())
                        .exceptionName(serviceException.getClass().getSimpleName())
                        .build());
    }


    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<ExceptionMessage> onAuthenticationExceptions(AuthenticationException authenticationException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ExceptionMessage.builder()
                        .message(authenticationException.getMessage())
                        .exceptionName(authenticationException.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(JwtException.class)
    public final ResponseEntity<ExceptionMessage> onJwtExceptions(JwtException jwtException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ExceptionMessage.builder()
                        .message(jwtException.getMessage())
                        .exceptionName(jwtException.getClass().getSimpleName())
                        .build());
    }
}
