package com.dekal.scheduling;

import com.dekal.scheduling.domain.*;
import com.dekal.scheduling.domain.Class;

import java.util.List;

public class Driver {
    public final static int POPULATION_SIZE = 9;
    public final static double MUTATION_RATE = 0.1;
    public final static double CROSSOVER_RATE = 0.9;
    public final static int TOURNAMENT_SELECTION_SIZE = 3;
    public final static int ELITE_SCHEDULE_NUM = 1;
    private Data data;
    private int classNumb = 1;
    private int scheduleNum = 1;

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.data = new Data();
        int generationNumber = 0;
        driver.printAvailableData();
        System.out.println("> Generation # " + generationNumber);
        System.out.print(" Schedule # |                                            ");
        System.out.print("Classes [dept,class,room,instructor,meeting-time]       ");
        System.out.println("                                              | Fitness | Conflicts");
        System.out.print("-------------------------------------------------------------------------");
        System.out.print("-------------------------------------------------------------------------");
        System.out.println("----------------------------------");
        GeneticAlgorithm algo = new GeneticAlgorithm(driver.data);
        Population population = new Population(Driver.POPULATION_SIZE, driver.data).sortByFitness();
        population.getSchedules().forEach(schedule -> {
            System.out.println("       " + driver.scheduleNum++ + "     | " + schedule + " | " + String.format("%.5f"
                    , schedule.getFitness()) + " | " + schedule.getConflictNum());
        });
        driver.printSchedule(population.getSchedules().get(0), generationNumber);
        while (population.getSchedules().get(0).getFitness() != 1.0) {
            System.out.println("> Generation # " + ++generationNumber);
            System.out.print(" Schedule # |                                            ");
            System.out.print("Classes [dept,class,room,instructor,meeting-time]       ");
            System.out.println("                                              | Fitness | Conflicts");
            System.out.print("-------------------------------------------------------------------------");
            System.out.print("-------------------------------------------------------------------------");
            System.out.println("----------------------------------");
            population = algo.evolve(population).sortByFitness();
            population.getSchedules().forEach(schedule -> {
                System.out.println("       " + driver.scheduleNum++ + "     | " + schedule + " | " + String.format("%.5f"
                        , schedule.getFitness()) + " | " + schedule.getConflictNum());
            });
            driver.printSchedule(population.getSchedules().get(0), generationNumber);
            driver.classNumb = 1;
        }
    }

    private void printAvailableData() {
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

    private void printSchedule(Schedule schedule, int generation) {
        List<Class> classes = schedule.getClasses();
        System.out.print("\n                       ");
        System.out.println("Class # | Dept | Course (number, max # of students) | Room (Capacity) | Instructor (Id) |" +
                " Meeting Time (Id) ");
        System.out.print("                       ");
        System.out.print("------------------------------------------------------");
        System.out.println("---------------------------------------------------------------");
        classes.forEach(classInfo -> {
            int majorIndex = data.getDepartments().indexOf(classInfo.getDepartment());
            Department dept = data.getDepartments().get(majorIndex);
            int courseIndex = data.getCourses().indexOf(classInfo.getCourse());
            Course course = data.getCourses().get(courseIndex);
            int roomIndex = data.getRooms().indexOf(classInfo.getRoom());
            Room room = data.getRooms().get(roomIndex);
            int instructorIndex = data.getInstructors().indexOf(classInfo.getInstructor());
            Instructor instructor = data.getInstructors().get(instructorIndex);
            int meetingIndex = data.getMeetingTimes().indexOf(classInfo.getMeetingTime());
            MeetingTime mt = data.getMeetingTimes().get(meetingIndex);

            System.out.print("                       ");
            System.out.print(String.format("  %1$02d", classNumb) + "  | ");
            System.out.print(String.format("%1$4s", dept.getName()) + " | ");
            System.out.print(String.format("%1$21s",
                    course.getName() + " (" + course.getNumber() + ", " +  classInfo.getCourse().getMaxStudents()) +
                            ")              | ");
            System.out.print(String.format("%1$10s", room.getNumber() + " (" + room.getSeats()) + ")     | ");
            System.out.print(String.format("%1$15s", instructor.getName() + " (" + instructor.getId() + ")")  + "  | ");
            System.out.println(mt.getTime() + " (" + mt.getId()  + ")");
            classNumb++;
        });
        if (schedule.getFitness() == 1) {
            System.out.println("> Solution found in " + (generation + 1) + " generations!");
        }
        System.out.print("------------------------------------------------------");
        System.out.println("---------------------------------------------------------------");
    }
}