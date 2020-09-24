package com.example.demo.service;

import com.example.demo.constants.ErrorConstants;
import com.example.demo.domain.Trainer;
import com.example.demo.exception.TrainerNotExists;
import com.example.demo.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TrainerService {
    private TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }


    public List<Trainer> getTrainers(String grouped) {
        return this.trainerRepository.findAllByGrouped(grouped);
    }

    public void addTrainer(Trainer trainer) {
        this.trainerRepository.save(trainer);
    }

    public void deleteTrainerById(long trainer_id) {
        Trainer trainer = this.trainerRepository.findById(trainer_id);
        isTrainerExists(trainer);
        this.trainerRepository.delete(trainer);
    }

    public void isTrainerExists(Trainer trainer) {
        if (Objects.isNull(trainer)) {
            throw new TrainerNotExists(ErrorConstants.TRAINER_NOT_EXISTS);
        }
    }


}
