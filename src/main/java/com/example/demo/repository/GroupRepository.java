package com.example.demo.repository;

import com.example.demo.domain.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<Group, Long> {
    List<Group> findAll();
}
