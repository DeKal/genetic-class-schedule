package com.dekal.scheduling.input;

import com.dekal.scheduling.entity.*;

import java.util.ArrayList;
import java.util.List;

class Reader {
    private static final String ROOM_FILE = "rooms.csv";
    private static final String MT_FILE = "meeting-times.csv";
    private static final String INSTRUCTOR_FILE = "instructors.csv";
    private static final String COURSE_FILE = "courses.csv";
    private static final String DEPT_FILE = "departments.csv";

    private ReaderUtils readerUtils = new ReaderUtils();

    List<Room> readRoom() {
        List<Room> rooms = new ArrayList<>();
        List<List<String>> records = readerUtils.read(ROOM_FILE);
        records.forEach(rows -> {
            String number = rows.get(0);
            int seats = Integer.parseInt(rows.get(1));
            rooms.add(new Room(number, seats));
        });
        return rooms;
    }

    List<MeetingTime> readMeetingTime() {
        List<MeetingTime> meetingTimes = new ArrayList<>();
        List<List<String>> records = readerUtils.read(MT_FILE);
        records.forEach(rows -> {
            String id = rows.get(0);
            String time = rows.get(1);
            meetingTimes.add(new MeetingTime(id, time));
        });

        return meetingTimes;
    }

    List<Instructor> readInstructor() {
        List<Instructor> instructors = new ArrayList<>();
        List<List<String>> records = readerUtils.read(INSTRUCTOR_FILE);
        records.forEach(rows -> {
            String id = rows.get(0);
            String name = rows.get(1);
            instructors.add(new Instructor(id, name));
        });

        return instructors;
    }

    List<Course> readCourses(List<Instructor> instructors) {
        List<Course> courses = new ArrayList<>();
        List<List<String>> records = readerUtils.read(COURSE_FILE);
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


    List<Department> readDepartments(List<Course> courses) {
        List<Department> department = new ArrayList<>();
        List<List<String>> records = readerUtils.read(DEPT_FILE);
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
