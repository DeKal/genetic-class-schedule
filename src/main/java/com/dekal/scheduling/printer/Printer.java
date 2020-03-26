package com.dekal.scheduling.printer;

import com.dekal.scheduling.format.ClassFormatter;
import com.dekal.scheduling.format.Formatter;
import com.dekal.scheduling.input.Data;
import com.dekal.scheduling.algo.Population;
import com.dekal.scheduling.algo.Schedule;
import com.dekal.scheduling.entity.*;
import com.dekal.scheduling.entity.Class;

import java.util.List;
import java.util.ListIterator;

public class Printer {

    void println(String text){
        System.out.println(text);
    }

    private void printEndLine() {
        println(Formatter.getBigEndLine());
    }

    private <T extends  HasToFullString>void printData(List<T> objects) {
        objects.forEach(object -> println(object.toFullString()));
    }
    public void printAvailableData(Data data) {
        println("Available Department => ");
        printData(data.getDepartments());

        println("Available Courses => ");
        printData(data.getCourses());

        println("Available Rooms =>");
        printData(data.getRooms());

        println("Available instructors =>");
        printData(data.getInstructors());

        println("Available MeetingTimes =>");
        printData(data.getMeetingTimes());

        printEndLine();
    }

    public void printPopulation(int generationNumber, Population population) {
        printGenerationHeader(generationNumber);
        printPopulationSchedules(population.getSchedules());
        printSchedule(population.getSchedules().get(0), generationNumber);
    }

    private final String generationHeader = Formatter.getGenerationHeader();
    private void printGenerationHeader(int generationNumber) {
        println("> Generation # " + generationNumber);
        printEndLine();
        println(generationHeader);
        printEndLine();
    }

    private void printPopulationSchedules(List<Schedule> schedules) {
        int scheduleNo = 0;
        for (Schedule schedule : schedules) {
            String scheduleSummary = Formatter.getScheduleSummary(schedule, scheduleNo);
            println(scheduleSummary);
            scheduleNo++;
        }
    }

    private void printSchedule(Schedule schedule, int generation) {
        printScheduleHeader();

        List<Class> classes = schedule.getClasses();
        printClasses(classes);

        if (schedule.getFitness() == 1) {
            printScheduleSolution(generation);
        }
        printEndLine();
    }

    private void printScheduleHeader() {
        println(Formatter.getScheduleHeader());
        printEndLine();
    }

    private void printClasses(List<Class> classes) {
        int classNo = 1;
        for (Class classInfo : classes) {
            printClass(classNo, classInfo);
            classNo++;
        }
    }

    private void printScheduleSolution(int generation) {
        printEndLine();
        println("> Solution found in " + generation + " generations!");
    }

    private void printClass(int classNo, Class classInfo) {
        Department dept = classInfo.getDepartment();
        Course course = classInfo.getCourse();
        Room room = classInfo.getRoom();
        Instructor instructor = classInfo.getInstructor();
        MeetingTime mt = classInfo.getMeetingTime();

        String classStr = ClassFormatter.format(classNo, dept, course, room, instructor, mt);
        println(classStr);
    }
}
