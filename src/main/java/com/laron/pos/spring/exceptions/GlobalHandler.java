package com.laron.pos.spring.exceptions;

import com.laron.pos.spring.dtos.utils.AppError;
import com.laron.pos.spring.dtos.utils.ServerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalHandler {




    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ServerResponse<?>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Object FieldError = null;
        List<AppError> errors = ex.getBindingResult().getFieldErrors()
                .stream().map( error-> AppError.builder()
                        .location(error.getField())
                        .message(error.getDefaultMessage())
                        .build()  ).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ServerResponse.builder()
                        .errors(errors)
                        .build()

                );
    }


    @ExceptionHandler({FieldErrorException.class})
    public ResponseEntity<ServerResponse<?>> handleValidationErrors(FieldErrorException ex) {


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ServerResponse.builder()
                        .errors(List.of(AppError.builder()
                                .location(ex.getLocation())
                                .message(ex.getFieldMessage())
                                .build()))
                        .build()

        );
    }


}
