package com.dekal.scheduling.input;

import com.dekal.scheduling.entity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Data {
    private List<Room> rooms;
    private List<Instructor> instructors;
    private List<Course> courses;
    private List<Department> departments;
    private List<MeetingTime> meetingTimes;
    private int numClasses = 0;

    public Data() {
        init();
    }

    public Data init() {
        Room room1 = new Room("R1", 25);
        Room room2 = new Room("R2", 45);
        Room room3 = new Room("R3", 35);
        rooms = new ArrayList<>(Arrays.asList(room1, room2, room3));

        MeetingTime mt1 = new MeetingTime("MT1", "MFW 09:00 - 10:00");
        MeetingTime mt2 = new MeetingTime("MT2", "MFW 10:00 - 11:00");
        MeetingTime mt3 = new MeetingTime("MT3", "TTH 09:00 - 10:30");
        MeetingTime mt4 = new MeetingTime("MT4", "TTH 10:30 - 12:00");
        meetingTimes = new ArrayList<>(Arrays.asList(mt1, mt2, mt3, mt4));

        Instructor ins1 = new Instructor("T1", "Mr A");
        Instructor ins2 = new Instructor("T2", "Mr B");
        Instructor ins3 = new Instructor("T3", "Mr C");
        Instructor ins4 = new Instructor("T4", "Mr D");
        instructors = new ArrayList<>(Arrays.asList(ins1, ins2, ins3, ins4));

        Course c1 = new Course("325K","C1", 25, new ArrayList<>(Arrays.asList(ins1, ins2)));
        Course c2 = new Course("319K","C2", 35, new ArrayList<>(Arrays.asList(ins1, ins2, ins3)));
        Course c3 = new Course("462K","C3", 25, new ArrayList<>(Arrays.asList(ins1, ins2)));
        Course c4 = new Course("464K","C4", 30, new ArrayList<>(Arrays.asList(ins3, ins4)));
        Course c5 = new Course("360C","C5", 35, new ArrayList<>(Arrays.asList(ins4)));
        Course c6 = new Course("303K","C6", 45, new ArrayList<>(Arrays.asList(ins1, ins3)));
        Course c7 = new Course("303L","C7", 45, new ArrayList<>(Arrays.asList(ins2, ins4)));
        courses = new ArrayList<>(Arrays.asList(c1, c2, c3, c4, c5, c6, c7));

        Department d1 = new Department("MATH", new ArrayList<>(Arrays.asList(c1, c3)));
        Department d2 = new Department("EE", new ArrayList<>(Arrays.asList(c2, c4, c5)));
        Department d3 = new Department("PH", new ArrayList<>(Arrays.asList(c6, c7)));
        departments = new ArrayList<>(Arrays.asList(d1, d2, d3));

        departments.forEach(dept -> numClasses+= dept.getCourses().size());
        return this;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public List<MeetingTime> getMeetingTimes() {
        return meetingTimes;
    }

    public int getNumClasses() {
        return numClasses;
    }
}
