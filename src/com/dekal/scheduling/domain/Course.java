package com.dekal.scheduling.domain;

import java.util.List;

public class Course {
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

    public List<Instructor> getInstructors() {
        return instructors;
    }

    @Override
    public String toString() {
        return name;
    }
}
