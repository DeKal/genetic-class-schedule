package com.dekal.scheduling.algorithm;

import com.dekal.scheduling.input.Data;
import com.dekal.scheduling.entity.*;
import com.dekal.scheduling.entity.Class;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Schedule {
    private List<Class> classes;
    private Data data;
    private int classNum = 0;
    private int conflictNum = 0;
    private double fitness = -1;
    private boolean isFitnessChanged = true;
    private SelectAlgorithm selector;

    Schedule(Data data, SelectAlgorithm selector) {
        this.data = data;
        this.selector = selector;
    }

    static Schedule create(Data data, SelectAlgorithm selector) {
        return new Schedule(data, selector).init();
    }

    private Schedule init() {
        classes = new ArrayList<>(data.getNumClasses());
        new ArrayList<>(data.getDepartments()).forEach(department -> {
            department.getCourses().forEach(course -> {
                Class newClass = new Class(classNum++, department, course);
                newClass.setInstructor(selector.pickRandom(data.getInstructors()));
                newClass.setMeetingTime(selector.pickRandom(data.getMeetingTimes()));
                newClass.setRoom(selector.pickRandom(data.getRooms()));
                classes.add(newClass);
            });
        });
        return this;
    }

    public int getConflictNum() {
        return conflictNum;
    }

    public double getFitness() {
        if (isFitnessChanged){
            fitness = calcFitness();
            isFitnessChanged = false;
        }
        return fitness;
    }

    private double calcFitness() {
        conflictNum = 0;
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
        return 1/(double)(conflictNum + 1);
    }

    public List<Class> getClasses() {
        isFitnessChanged = true;
        return classes;
    }

    static void assignElite(List<Schedule> targetSchedules, List<Schedule> schedules, int eliteNum) {
        IntStream.range(0, eliteNum).forEach(elite -> {
            targetSchedules.set(elite, schedules.get(elite));
        });
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int classIndex = 0; classIndex < classes.size() - 1; ++classIndex) {
            stringBuilder.append(classes.get(classIndex));
            stringBuilder.append(',');
        }
        stringBuilder.append(classes.get(classes.size() - 1));
        return stringBuilder.toString();
    }

    public Data getData() {
        return data;
    }
}
