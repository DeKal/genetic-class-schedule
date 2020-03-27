package com.dekal.scheduling.algorithm;

import java.util.List;
import java.util.stream.IntStream;

import com.dekal.scheduling.config.Config;
import com.dekal.scheduling.entity.Population;
import com.dekal.scheduling.entity.Schedule;
import com.dekal.scheduling.factory.PopulationFactory;
import com.dekal.scheduling.entity.Class;
import com.dekal.scheduling.factory.ScheduleFactory;

public class GeneticAlgorithm {

    private PopulationFactory populationFactory;
    private ScheduleFactory scheduleFactory;
    private SelectAlgorithm selector;
    private PopulationAlgorithm populationAlgo;
    private ScheduleAlgorithm scheduleAlgo;

    public GeneticAlgorithm(PopulationFactory populationFactory,
                            ScheduleFactory scheduleFactory,
                            SelectAlgorithm selector,
                            PopulationAlgorithm populationAlgo,
                            ScheduleAlgorithm scheduleAlgo) {
        this.populationFactory = populationFactory;
        this.scheduleFactory = scheduleFactory;
        this.selector = selector;
        this.populationAlgo = populationAlgo;
        this.scheduleAlgo = scheduleAlgo;
    }


    public Population evolve(Population population) {
        return mutatePopulation(crossOverPopulation(population));
    }


    private Population crossOverPopulation(Population population) {

        List<Schedule> normalSchedules = population.getSchedules();
        int scheduleSize = normalSchedules.size();
        Population crossOverPop = populationFactory.create(scheduleSize);
        List<Schedule> crossOverSchedules = crossOverPop.getSchedules();

        scheduleAlgo.assignElite(crossOverSchedules, normalSchedules, Config.ELITE_SCHEDULE_NUM);
        IntStream.range(Config.ELITE_SCHEDULE_NUM, scheduleSize)
                .forEach(index -> {
                    if (Config.CROSSOVER_RATE > Math.random()) {
                        Schedule schedule1 = populationAlgo.doTournamentAndGetBestFit(population);
                        Schedule schedule2 = populationAlgo.doTournamentAndGetBestFit(population);
                        crossOverSchedules.set(index, crossOverSchedule(schedule1, schedule2));
                    } else {
                        crossOverSchedules.set(index, normalSchedules.get(index));
                    }
                });

        return crossOverPop;
    }

    private Schedule crossOverSchedule(Schedule schedule1, Schedule schedule2) {
        Schedule crossOverSchedule = scheduleFactory.create();
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
        Population mutatePopulation = populationFactory.create(scheduleSize);
        List<Schedule> schedules = mutatePopulation.getSchedules();

        scheduleAlgo.assignElite(schedules, normalSchedules, Config.ELITE_SCHEDULE_NUM);
        IntStream.range(Config.ELITE_SCHEDULE_NUM, scheduleSize)
                .forEach(x -> {
                    schedules.set(x, mutateSchedule(normalSchedules.get(x)));
                });
        return mutatePopulation;
    }

    private Schedule mutateSchedule(Schedule schedule) {
        Schedule mutateSchedule = scheduleFactory.create();
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
