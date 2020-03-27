package com.dekal.scheduling.format;

import com.dekal.scheduling.entity.Schedule;

import java.util.stream.IntStream;

public class Formatter {

    static final String SEPARATOR = " | ";
    static String space(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(0, num).forEach(time -> {
            stringBuilder.append(" ");
        });
        return stringBuilder.toString();
    }

    static String hyphen(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(0, num).forEach(time -> {
            stringBuilder.append("-");
        });
        return stringBuilder.toString();
    }

    public static String getGenerationHeader() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Schedule #");
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(space(43));
        stringBuilder.append("Classes [dept,class,room,instructor,meeting-time]");
        stringBuilder.append(space(51));
        stringBuilder.append(SEPARATOR);
        stringBuilder.append("Fitness");
        stringBuilder.append(SEPARATOR);
        stringBuilder.append("Conflicts");
        return stringBuilder.toString();
    }

    public static String getScheduleSummary(Schedule schedule, int scheduleNum) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(space(4));
        stringBuilder.append(scheduleNum);
        stringBuilder.append(space(4));
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(schedule);
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(String.format("%.5f", schedule.getFitness()));
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(schedule.getConflictNum());
        return stringBuilder.toString();
    }

    public static String getScheduleHeader() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        stringBuilder.append(space(21));
        stringBuilder.append("Class #");
        stringBuilder.append(SEPARATOR);
        stringBuilder.append("Dept");
        stringBuilder.append(SEPARATOR);
        stringBuilder.append("Course (number, max # of students)");
        stringBuilder.append(SEPARATOR);
        stringBuilder.append("Room (Capacity)");
        stringBuilder.append(SEPARATOR);
        stringBuilder.append("Instructor (Id)");
        stringBuilder.append(SEPARATOR);
        stringBuilder.append("Meeting Time (Id)");
        stringBuilder.append(space(24));
        return stringBuilder.toString();
    }

    public static String getBigEndLine() {
        return hyphen(180);
    }
}
