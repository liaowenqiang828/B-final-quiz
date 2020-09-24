package com.example.demo.exception;

public class TraineeNotExists extends RuntimeException {
    public TraineeNotExists(String message) {
        super(message);
    }
}
