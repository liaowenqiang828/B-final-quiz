package com.example.demo.repository;

import com.example.demo.domain.Trainer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrainerRepository extends CrudRepository<Trainer, Long> {
    List<Trainer> findAllByGrouped(String grouped);
    Trainer findById(long id);
}
