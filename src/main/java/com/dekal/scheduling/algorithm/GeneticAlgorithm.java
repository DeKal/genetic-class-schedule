package com.dekal.scheduling.algorithm;

import java.util.List;
import java.util.stream.IntStream;

import com.dekal.scheduling.config.Config;
import com.dekal.scheduling.input.Data;
import com.dekal.scheduling.entity.Class;

public class GeneticAlgorithm {
    private Data data;
    private SelectAlgorithm selector;

    public GeneticAlgorithm(Data data, SelectAlgorithm selector) {
        this.data = data;
        this.selector = selector;
    }

    public Population evolve(Population population) {
        return mutatePopulation(crossOverPopulation(population));
    }


    private Population crossOverPopulation(Population population) {

        List<Schedule> normalSchedules = population.getSchedules();
        int scheduleSize = normalSchedules.size();
        Population crossOverPop = Population.create(scheduleSize, data, selector);
        List<Schedule> crossOverSchedules = crossOverPop.getSchedules();

        Schedule.assignElite(crossOverSchedules, normalSchedules, Config.ELITE_SCHEDULE_NUM);
        IntStream.range(Config.ELITE_SCHEDULE_NUM, scheduleSize)
                .forEach(index -> {
                    if (Config.CROSSOVER_RATE > Math.random()) {
                        Schedule schedule1 = population.doTournamentAndGetBestFit(data);
                        Schedule schedule2 = population.doTournamentAndGetBestFit(data);
                        crossOverSchedules.set(index, crossOverSchedule(schedule1, schedule2));
                    } else {
                        crossOverSchedules.set(index, normalSchedules.get(index));
                    }
                });

        return crossOverPop;
    }

    private Schedule crossOverSchedule(Schedule schedule1, Schedule schedule2) {
        Schedule crossOverSchedule = Schedule.create(data, selector);
        List<Class> classes = crossOverSchedule.getClasses();
        int classSize = classes.size();

        IntStream.range(0, classSize).forEach(index -> {
            Schedule chosenSchedule = selector.pickCrossOverSchedule(schedule1, schedule2);
            classes.set(index, chosenSchedule.getClasses().get(index));
        });

        return crossOverSchedule;
    }

    private Population mutatePopulation(Population population) {
        List<Schedule> normalSchedules = population.getSchedules();
        int scheduleSize = normalSchedules.size();
        Population mutatePopulation = Population.create(scheduleSize, data, selector);
        List<Schedule> schedules = mutatePopulation.getSchedules();

        Schedule.assignElite(schedules, normalSchedules, Config.ELITE_SCHEDULE_NUM);
        IntStream.range(Config.ELITE_SCHEDULE_NUM, scheduleSize)
                .forEach(x -> {
                    schedules.set(x, mutateSchedule(normalSchedules.get(x)));
                });
        return mutatePopulation;
    }

    private Schedule mutateSchedule(Schedule schedule) {
        Schedule mutateSchedule = Schedule.create(data, selector);
        List<Class> classes = mutateSchedule.getClasses();
        int classSize = classes.size();
        IntStream.range(0, classSize).forEach(index -> {
            Schedule chosenSchedule = selector.pickScheduleIfMutated(schedule);
            if (chosenSchedule != null) {
                classes.set(index, chosenSchedule.getClasses().get(index));
            }
        });
        return mutateSchedule;
    }

}
