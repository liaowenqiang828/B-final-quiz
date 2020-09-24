package com.example.demo.controller;

import com.example.demo.domain.Group;
import com.example.demo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class GroupController {
    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService){
        this.groupService = groupService;
    }

    @GetMapping("/groups")
    public List<Group> getGroups() {
        return this.groupService.getGroups();
    }

    @PostMapping("/groups/auto-grouping")
    public List<Group> divideTrainersAndTraineesRandom() {
        return this.groupService.divideTrainersAndTraineesRandom();
    }
}
