package com.dekal.scheduling.algo;

import com.dekal.scheduling.input.Data;
import com.dekal.scheduling.entity.*;
import com.dekal.scheduling.entity.Class;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private List<Class> classes;
    private Data data;
    private int classNum = 0;
    private int conflictNum = 0;
    private double fitness = -1;
    private boolean isFitnessChanged = true;

    public Schedule(Data data) {
        this.data = data;
        classes = new ArrayList<>(data.getNumClasses());
    }

    private <T> T getRandomItem(List<T> list) {
        int randomListItem = (int) (list.size() * Math.random());
        return list.get(randomListItem);
    }

    public Schedule init() {
        new ArrayList<>(data.getDepartments()).forEach(department -> {
            department.getCourses().forEach(course -> {
                Class newClass = new Class(classNum++, department, course);
                newClass.setInstructor(getRandomItem(data.getInstructors()));
                newClass.setMeetingTime(getRandomItem(data.getMeetingTimes()));
                newClass.setRoom(getRandomItem(data.getRooms()));
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
