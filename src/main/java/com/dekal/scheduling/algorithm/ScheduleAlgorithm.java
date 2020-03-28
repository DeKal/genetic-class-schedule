package com.dekal.scheduling.algorithm;

import com.dekal.scheduling.entity.*;
import com.dekal.scheduling.entity.Class;

import java.util.List;
import java.util.stream.IntStream;

public class ScheduleAlgorithm {
    private int conflictNum = -1;

    private int calcConflicts(Schedule schedule) {
        conflictNum = 0;
        List<Class> classes = schedule.getClasses();
        classes.forEach(curClass -> {
            Room room = curClass.getRoom();
            Course course = curClass.getCourse();

            if (room.getSeats() < course.getMaxStudents()) {
                conflictNum++;
            }
            classes.stream()
                    .filter(filterClass -> classes.indexOf(filterClass) >= classes.indexOf(curClass))
                    .forEach(postClass -> {
                        MeetingTime meetingTime = curClass.getMeetingTime();
                        Instructor instructor = curClass.getInstructor();
                        if (meetingTime == postClass.getMeetingTime()
                                && curClass.getId() != postClass.getId()) {
                            if (room == postClass.getRoom()) {
                                conflictNum++;
                            }
                            if (instructor == postClass.getInstructor()) {
                                conflictNum++;
                            }
                        }
                    });
        });
        return conflictNum;
    }

    double calcFitness(Schedule schedule, boolean isForceFitness) {
        if (!isForceFitness && schedule.getConflictNum() >= 0) {
            return schedule.getFitness();
        }
        int conflictNum = calcConflicts(schedule);
        double fitness = 1/(double)(conflictNum + 1);
        schedule.setConflictNum(conflictNum);
        schedule.setFitness(fitness);
        return fitness;
    }


    void assignElite(List<Schedule> targetSchedules, List<Schedule> schedules, int eliteNum) {
        IntStream.range(0, eliteNum).forEach(elite -> {
            targetSchedules.set(elite, schedules.get(elite));
        });
    }

}
