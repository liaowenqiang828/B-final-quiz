package com.example.demo.controller;

import com.example.demo.domain.Trainee;
import com.example.demo.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class TraineeController {
    private TraineeService traineeService;

    @Autowired
    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @GetMapping("/trainees")
    @ResponseStatus(HttpStatus.OK)
    public List<Trainee> getTraineesList(
            @RequestParam(required = false)  String grouped) {
        return this.traineeService.getTraineeList(grouped);
    }

    @PostMapping("/trainees")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTrainee(@RequestBody @Valid Trainee trainee) {
        this.traineeService.addTrainee(trainee);
    }

    @DeleteMapping("/trainees/{trainee_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTraineeById(@PathVariable("trainee_id") long trainee_id) {
        this.traineeService.deleteTraineeById(trainee_id);
    }
}

