package com.example.demo.controller;

import com.example.demo.domain.Trainer;
import com.example.demo.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class TrainerController {
    private TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping("/trainers")
    @ResponseStatus(HttpStatus.OK)
    public List<Trainer> getTrainers(@RequestParam(required = false) String grouped) {
        return this.trainerService.getTrainers(grouped);
    }

    @PostMapping("/trainers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTrainer(@RequestBody @Valid Trainer trainer) {
        this.trainerService.addTrainer(trainer);
    }

    @DeleteMapping("/trainers/{trainer_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainerById(@PathVariable long trainer_id) {
        this.trainerService.deleteTrainerById(trainer_id);
    }
}
