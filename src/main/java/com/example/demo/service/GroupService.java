package com.example.demo.service;

import com.example.demo.constants.ErrorConstants;
import com.example.demo.domain.Group;
import com.example.demo.domain.Trainee;
import com.example.demo.domain.Trainer;
import com.example.demo.exception.CanNotDivideGroups;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GroupService {
    private GroupRepository groupRepository;
    private TraineeRepository traineeRepository;
    private TrainerRepository trainerRepository;

    private final int MIN_NUM_TRAINERS_CAN_BE_DIVIDED = 2;


    @Autowired
    public GroupService(GroupRepository groupRepository,
                        TraineeRepository traineeRepository,
                        TrainerRepository trainerRepository) {
     this.groupRepository = groupRepository;
     this.traineeRepository = traineeRepository;
     this.trainerRepository = trainerRepository;
    }

    public List<Group> getGroups() {
        if (this.groupRepository.findAll().size() > 0) {
            this.groupRepository.deleteAll();
        }
        return divideTrainersAndTraineesRandom();
    }

    public List<Group> divideTrainersAndTraineesRandom() {
        List<Trainee> traineeList = this.traineeRepository.findAll();
        List<Trainer> trainerList = this.trainerRepository.findAll();

        if (isTrainersNumberlessThanTwo(trainerList)) {
            throw new CanNotDivideGroups(ErrorConstants.CAN_NOT_DIVIDED_GROUPS);
        }
        int groupsNum = groupNumCalculator(trainerList);

        return groupsDivider(groupsNum, trainerList, traineeList);
    }

    private List<Group> groupsDivider(int groupNum, List<Trainer> trainerList, List<Trainee> traineeList) {
        int traineeRemainder = traineeRemainderCalculator(traineeList, groupNum);
        int traineeAvg = traineeAvgNumCalculator(traineeList, groupNum);

        Collections.shuffle(traineeList);
        Collections.shuffle(trainerList);

        divider(traineeList, trainerList, traineeRemainder, traineeAvg, groupNum);

//        setGroupsName();
        return groupRepository.findAll();
    }

    private void setGroupsName() {
        List<Group> groupList = this.groupRepository.findAll();
        for (int index = 0; index < groupList.size(); index++) {
            Group group = groupList.get(index);
            group.setName("组");
            this.groupRepository.save(group);
        }
    }

    private List<Trainer> getTrainersListByGroupIndex(List<Trainer> trainerList, int groupIndex) {
        return trainerList.subList(MIN_NUM_TRAINERS_CAN_BE_DIVIDED * groupIndex,
                MIN_NUM_TRAINERS_CAN_BE_DIVIDED * (groupIndex + 1));
    }

    private List<Trainee> getTraineesListByStartIndex(List<Trainee> traineeList, int start, int traineeAvg, int bouns) {
        return traineeList.subList(start, start + traineeAvg + bouns);
    }

    private void divider(List<Trainee> traineeList, List<Trainer> trainerList,
                         int traineeRemainder, int traineeAvg, int groupNum) {
        int indexStart = 0;
        int groupIndex = 0;
        while (groupIndex < groupNum) {
            if (traineeRemainder > 0) {
                List<Trainer> trainers = getTrainersListByGroupIndex(trainerList, groupIndex);
                List<Trainee> trainees = getTraineesListByStartIndex(traineeList, indexStart, traineeAvg, 1);

                Group group = Group.builder()
                        .trainerList(trainers)
                        .traineeList(trainees)
                        .build();
                this.groupRepository.save(group);

                traineeRemainder -= 1;
                groupIndex += 1;
                indexStart = indexStart + traineeAvg + 1;
            } else {
                List<Trainer> trainers = getTrainersListByGroupIndex(trainerList, groupIndex);
                List<Trainee> trainees = getTraineesListByStartIndex(traineeList, indexStart, traineeAvg, 0);

                Group group = Group.builder()
                        .traineeList(trainees)
                        .trainerList(trainers)
                        .build();
                this.groupRepository.save(group);

                groupIndex += 1;
                indexStart = indexStart + traineeAvg;
            }
        }
    }


    private int traineeRemainderCalculator(List<Trainee> traineeList, int groupNum) {
        return traineeList.size() % groupNum;
    }

    private int traineeAvgNumCalculator(List<Trainee> traineeList, int groupNum) {
        return traineeList.size() / groupNum;
    }



    private int groupNumCalculator(List<Trainer> trainerList) {
        int trainersCount = trainerList.size();
        return trainersCount / MIN_NUM_TRAINERS_CAN_BE_DIVIDED;
    }

    private boolean isTrainersNumberlessThanTwo(List<Trainer> trainerList) {
        return trainerList.size() < MIN_NUM_TRAINERS_CAN_BE_DIVIDED;
    }

}
