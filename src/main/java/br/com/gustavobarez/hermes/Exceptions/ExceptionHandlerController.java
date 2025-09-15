package br.com.gustavobarez.hermes.Exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {

        List<ErrorMessageDTO> errors = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(err -> {
            String message = String.format("param: %s (type: body) is required", err.getField());
            ErrorMessageDTO error = new ErrorMessageDTO("400", message);
            errors.add(error);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMissingParam(MissingServletRequestParameterException e) {
        List<ErrorMessageDTO> errors = new ArrayList<>();
        String message = String.format("param: %s (type: queryParameter) is required", e.getParameterName());
        errors.add(new ErrorMessageDTO("400", message));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleEntityNotFoundException(EntityNotFoundException e) {
        List<ErrorMessageDTO> errors = new ArrayList<>();
        errors.add(new ErrorMessageDTO("404", e.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleIllegalArgumentException(IllegalArgumentException e) {
        List<ErrorMessageDTO> errors = new ArrayList<>();
        errors.add(new ErrorMessageDTO("400", e.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        List<ErrorMessageDTO> errors = new ArrayList<>();
        String message = String.format("Invalid value '%s' for parameter '%s'. Expected type: %s",
                e.getValue(), e.getName(), e.getRequiredType().getSimpleName());
        errors.add(new ErrorMessageDTO("400", message));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleGenericException(Exception e) {
        e.printStackTrace();
        List<ErrorMessageDTO> errors = new ArrayList<>();
        errors.add(new ErrorMessageDTO("500", "Internal server error"));
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}