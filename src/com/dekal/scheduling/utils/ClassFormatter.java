package com.dekal.scheduling.utils;

import com.dekal.scheduling.entity.*;

public class ClassFormatter extends Formatter {
    private static final String OPEN_BRACKET = " (";
    private static final String CLOSE_BRACKET = ")";
    private static final String COMMA = ", ";

    public static String format(int classNo,
                                Department dept,
                                Course course,
                                Room room,
                                Instructor instructor,
                                MeetingTime mt) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(space(25));
        stringBuilder.append(String.format("%1$02d", classNo));
        stringBuilder.append(space(1));
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(String.format("%1$4s", dept.getName()));
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(String.format("%1$21s",formatCourse(course)));
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(String.format("%1$10s", formatRoom(room)));
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(String.format("%1$15s", formatInstructor(instructor)));
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(formatMeetingTime(mt));
        stringBuilder.append(space(24));

        return stringBuilder.toString();
    }

    private static String formatCourse(Course course) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(course.getName());
        stringBuilder.append(OPEN_BRACKET);
        stringBuilder.append(course.getNumber());
        stringBuilder.append(COMMA);
        stringBuilder.append(course.getMaxStudents());
        stringBuilder.append(CLOSE_BRACKET);
        stringBuilder.append(space(21));
        return stringBuilder.toString();
    }

    private static String formatRoom(Room room) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(room.getNumber());
        stringBuilder.append(OPEN_BRACKET);
        stringBuilder.append(room.getSeats());
        stringBuilder.append(CLOSE_BRACKET);
        stringBuilder.append(space(8));
        return stringBuilder.toString();
    }

    private static String formatInstructor(Instructor instructor) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(instructor.getName());
        stringBuilder.append(OPEN_BRACKET);
        stringBuilder.append(instructor.getId());
        stringBuilder.append(CLOSE_BRACKET);
        stringBuilder.append(space(2));
        return stringBuilder.toString();
    }

    private static String formatMeetingTime(MeetingTime mt) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(mt.getTime());
        stringBuilder.append(OPEN_BRACKET);
        stringBuilder.append(mt.getId());
        stringBuilder.append(CLOSE_BRACKET);
        return stringBuilder.toString();
    }
}
