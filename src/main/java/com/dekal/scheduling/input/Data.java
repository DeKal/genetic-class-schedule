package com.dekal.scheduling.input;

import com.dekal.scheduling.entity.*;
import java.util.List;

public class Data {

    private List<Room> rooms;
    private List<Instructor> instructors;
    private List<Course> courses;
    private List<Department> departments;
    private List<MeetingTime> meetingTimes;
    private int numClasses = 0;

    public Data() {
        Reader reader = new Reader();
        rooms = reader.readRoom();
        meetingTimes = reader.readMeetingTime();
        instructors = reader.readInstructor();
        courses = reader.readCourses(instructors);
        departments = reader.readDepartments(courses);
        departments.forEach(dept -> numClasses+= dept.getCourses().size());
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
