package com.dekal.scheduling.factory;

import com.dekal.scheduling.algorithm.SelectAlgorithm;
import com.dekal.scheduling.entity.Class;
import com.dekal.scheduling.entity.Schedule;
import com.dekal.scheduling.input.Data;

import java.util.ArrayList;
import java.util.List;

public class ScheduleFactory {
    private Data data;
    private SelectAlgorithm selector;
    private int classNum;

    public ScheduleFactory(Data data, SelectAlgorithm selector) {
        this.data = data;
        this.selector = selector;
    }

    public Schedule create() {
        classNum = 0;
        List<Class> classes = new ArrayList<>(data.getNumClasses());
        new ArrayList<>(data.getDepartments()).forEach(department -> {
            department.getCourses().forEach(course -> {
                Class newClass = new Class(classNum++, department, course);
                newClass.setInstructor(selector.pickRandom(data.getInstructors()));
                newClass.setMeetingTime(selector.pickRandom(data.getMeetingTimes()));
                newClass.setRoom(selector.pickRandom(data.getRooms()));
                classes.add(newClass);
            });
        });
        return new Schedule(classes);
    }
}
