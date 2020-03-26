package com.dekal.scheduling.entity;

public class Class {
    private int id;
    private Department department;
    private Course course;
    private Instructor instructor;
    private MeetingTime meetingTime;
    private Room room;

    public Class(int id, Department department, Course course) {
        this.id = id;
        this.department = department;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public Department getDepartment() {
        return department;
    }

    public Course getCourse() {
        return course;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public MeetingTime getMeetingTime() {
        return meetingTime;
    }

    public Room getRoom() {
        return room;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public void setMeetingTime(MeetingTime meetingTime) {
        this.meetingTime = meetingTime;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");
        stringBuilder.append(department.getName());
        stringBuilder.append(',');
        stringBuilder.append(course.getNumber());
        stringBuilder.append(',');
        stringBuilder.append(room.getNumber());
        stringBuilder.append(',');
        stringBuilder.append(instructor.getId());
        stringBuilder.append(',');
        stringBuilder.append(meetingTime.getId());
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
