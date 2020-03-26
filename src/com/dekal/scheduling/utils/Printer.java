package com.dekal.scheduling.utils;

import com.dekal.scheduling.Data;
import com.dekal.scheduling.Population;
import com.dekal.scheduling.Schedule;
import com.dekal.scheduling.entity.*;
import com.dekal.scheduling.entity.Class;

import java.util.List;
import java.util.ListIterator;

public class Printer {

    static void print(String text){
        System.out.print(text);
    }

    static void println(String text){
        System.out.println(text);
    }

    static public void printEndLine() {
        println(Formatter.getBigEndLine());
    }

    static private final String generationHeader = Formatter.getGenerationHeader();
    static public void printGenerationHeader(int generationNumber) {
        println("> Generation # " + generationNumber);
        printEndLine();
        println(generationHeader);
    }

    static public void printAvailableData(Data data) {
        System.out.println("Available Department => ");
        data.getDepartments()
                .forEach(x -> System.out.println("name: " + x.getName() + ", courses: " + x.getCourses()));

        System.out.println("Available Courses => ");
        data.getCourses()
                .forEach(x -> System.out.println("course #: " + x.getNumber() + ", name: " + x.getName() + ", max " +
                        "student: " + x.getMaxStudents()+ ", instructors: " + x.getInstructors()));

        System.out.println("Available Rooms =>");
        data.getRooms()
                .forEach(x -> System.out.println("rooms# : " + x.getNumber() + ", max seat: " + x.getSeats()));

        System.out.println("Available instructors =>");
        data.getInstructors()
                .forEach(x -> System.out.println("id: " + x.getId() + " name: " + x.getName()));

        System.out.println("Available MeetingTimes =>");
        data.getMeetingTimes()
                .forEach(x -> System.out.println("id: " + x.getId() + ", time: " + x.getTime()));

        System.out.println("----------------------------------------------------");
        System.out.println("----------------------------------------------------");
    }



    public static void printPopulationSchedules(List<Schedule> schedules) {
        int scheduleNo = 0;
        ListIterator<Schedule> scheduleIterator = schedules.listIterator();
        while (scheduleIterator.hasNext()) {
            Schedule schedule = scheduleIterator.next();
            String scheduleSummary = Formatter.getScheduleSummary(schedule, scheduleNo);
            println(scheduleSummary);
            scheduleNo++;
        }
    }

    public static void printSchedule(Schedule schedule, int generation) {
        printScheduleHeader();

        List<Class> classes = schedule.getClasses();
        printClasses(classes);

        if (schedule.getFitness() == 1) {
            printScheduleSolution(generation);
        }
        printEndLine();
    }

    private static void printScheduleHeader() {
        println(Formatter.getScheduleHeader());
        printEndLine();
    }

    private static void printClasses(List<Class> classes) {
        int classNo = 1;
        ListIterator<Class> classIterator = classes.listIterator();
        while (classIterator.hasNext()) {
            Class classInfo = classIterator.next();
            printClass(classNo, classInfo);
            classNo++;
        }
    }

    private static void printScheduleSolution(int generation) {
        printEndLine();
        println("> Solution found in " + generation + " generations!");
    }

    private static void printClass(int classNo, Class classInfo) {
        Department dept = classInfo.getDepartment();
        Course course = classInfo.getCourse();
        Room room = classInfo.getRoom();
        Instructor instructor = classInfo.getInstructor();
        MeetingTime mt = classInfo.getMeetingTime();

        String classStr = ClassFormatter.format(classNo, dept, course, room, instructor, mt);
        println(classStr);
    }

    public static void printPopulation(int generationNumber, Population population) {
        printGenerationHeader(generationNumber);
        printEndLine();
        printPopulationSchedules(population.getSchedules());
        printSchedule(population.getSchedules().get(0), generationNumber);
    }
}
