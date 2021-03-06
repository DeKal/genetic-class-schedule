package com.dekal.scheduling.entity;

import java.util.List;

public class Course implements HasToFullString{
    private String number = null;
    private String name = null;
    private int maxStudents;
    private List<Instructor> instructors;

    public Course(String number, String name, int maxStudents, List<Instructor> instructors) {
        this.number = number;
        this.name = name;
        this.maxStudents = maxStudents;
        this.instructors = instructors;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    @Override
    public String toString() {
        return name;
    }

    public String toFullString() {
        return "Course #: " + number + ", name: " + name + ", max " +
                "student: " + maxStudents + ", instructors: " + instructors;
    }
}
