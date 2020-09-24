package com.example.demo.service;

import com.example.demo.domain.Trainer;
import com.example.demo.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {
    private TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }


    public List<Trainer> getTrainers(String grouped) {
        return this.trainerRepository.findAllByGrouped(grouped);
    }
}
