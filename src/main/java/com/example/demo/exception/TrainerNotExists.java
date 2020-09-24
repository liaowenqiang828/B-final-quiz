package com.example.demo.exception;

public class TrainerNotExists extends RuntimeException {
    public TrainerNotExists(String message) {
        super(message);
    }
}
