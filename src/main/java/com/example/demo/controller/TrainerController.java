package com.example.demo.controller;

import com.example.demo.domain.Trainer;
import com.example.demo.service.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainerController {
    private TrainerService trainerService;

    @GetMapping("/trainers")
    @ResponseStatus(HttpStatus.OK)
    public List<Trainer> getTrainers(@RequestParam(required = false) String grouped) {
        return this.trainerService.getTrainers(grouped);
    }
}
