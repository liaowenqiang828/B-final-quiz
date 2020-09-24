package com.example.demo.service;

import com.example.demo.constants.ErrorConstants;
import com.example.demo.domain.Trainee;
import com.example.demo.exception.TraineeNotExists;
import com.example.demo.repository.TraineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TraineeService {
    private TraineeRepository traineeRepository;
    @Autowired
    public TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    public List<Trainee> getTraineeList(String grouped) {
        return this.traineeRepository.findAllByGrouped(grouped);
    }

    public void addTrainee(Trainee trainee) {
        this.traineeRepository.save(trainee);
    }


    public void deleteTraineeById(long trainee_id) {
        Trainee trainee = this.traineeRepository.findById(trainee_id);
        isTraineeExists(trainee);
        this.traineeRepository.delete(trainee);
    }

    public void isTraineeExists(Trainee trainee) {
        if (Objects.isNull(trainee)) {
            throw new TraineeNotExists(ErrorConstants.TRAINEE_NOT_EXISTS);
        }
    }
}
