package com.dekal.scheduling.input;

import com.dekal.scheduling.entity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Data {

    private static final String ROOM_FILE = "rooms.csv";
    private static final String MT_FILE = "meeting-times.csv";
    private static final String INSTRUCTOR_FILE = "instructors.csv";
    private static final String COURSE_FILE = "courses.csv";
    private static final String DEPT_FILE = "departments.csv";

    private List<Room> rooms;
    private List<Instructor> instructors;
    private List<Course> courses;
    private List<Department> departments;
    private List<MeetingTime> meetingTimes;
    private int numClasses = 0;
    private Reader reader = new Reader();

    public Data() {
        init();
    }

    public Data init() {
        rooms = readRoom();
        meetingTimes = readMeetingTime();
        instructors = readInstructor();
        courses = readCourses(instructors);
        departments = readDepartments(courses);
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

    private List<Room> readRoom() {
        List<Room> rooms = new ArrayList<>();
        List<List<String>> records = reader.read(ROOM_FILE);
        records.forEach(rows -> {
            String number = rows.get(0);
            int seats = Integer.parseInt(rows.get(1));
            rooms.add(new Room(number, seats));
        });
        return rooms;
    }

    private List<MeetingTime> readMeetingTime() {
        List<MeetingTime> meetingTimes = new ArrayList<>();
        List<List<String>> records = reader.read(MT_FILE);
        records.forEach(rows -> {
            String id = rows.get(0);
            String time = rows.get(1);
            meetingTimes.add(new MeetingTime(id, time));
        });

        return meetingTimes;
    }

    private List<Instructor> readInstructor() {
        List<Instructor> instructors = new ArrayList<>();
        List<List<String>> records = reader.read(INSTRUCTOR_FILE);
        records.forEach(rows -> {
            String id = rows.get(0);
            String name = rows.get(1);
            instructors.add(new Instructor(id, name));
        });

        return instructors;
    }

    private List<Course> readCourses(List<Instructor> instructors) {
        List<Course> courses = new ArrayList<>();
        List<List<String>> records = reader.read(COURSE_FILE);
        records.forEach(rows -> {
            String number = rows.get(0);
            String name = rows.get(1);
            int maxStudents = Integer.parseInt(rows.get(2));

            List<Instructor> courseInstructors = new ArrayList<>();
            int pos = 3;
            while (pos < rows.size()) {
                int instructorPos = Integer.parseInt(rows.get(pos));
                courseInstructors.add(instructors.get(instructorPos - 1));
                pos++;
            }
            courses.add(new Course(number, name, maxStudents, courseInstructors));
        });

        return courses;
    }


    private List<Department> readDepartments(List<Course> courses) {
        List<Department> department = new ArrayList<>();
        List<List<String>> records = reader.read(DEPT_FILE);
        records.forEach(rows -> {
            String name = rows.get(0);
            List<Course> departmentCourses = new ArrayList<>();
            int pos = 1;
            while (pos < rows.size()) {
                int coursePos = Integer.parseInt(rows.get(pos));
                departmentCourses.add(courses.get(coursePos - 1));
                pos++;
            }
            department.add(new Department(name, departmentCourses));
        });
        return department;
    }
}
