package com.example.demo.exception;

import com.example.demo.constants.ErrorConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TraineeNotExists.class)
    public ResponseEntity<ErrorResult> handler(TraineeNotExists exception) {
        String message = exception.getMessage();
        ErrorResult errorResult = ErrorResult.builder()
                .message(message)
                .detail(new HashMap<String, String>(){{put("exists", ErrorConstants.TRAINEE_NOT_EXISTS);}})
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }

    @ExceptionHandler(TrainerNotExists.class)
    public ResponseEntity<ErrorResult> handler(TrainerNotExists exception) {
        String message = exception.getMessage();
        ErrorResult errorResult = ErrorResult.builder()
                .message(message)
                .detail(new HashMap<String, String>(){{put("exists", ErrorConstants.TRAINER_NOT_EXISTS);}})
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }
}
