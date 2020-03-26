package com.dekal.scheduling.entity;

import java.util.List;

public class Department implements HasToFullString{
    private String name;
    private List<Course> courses;

    public Department(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public String toFullString() {
        return "name: " + name + ", courses: " + courses;
    }
}
