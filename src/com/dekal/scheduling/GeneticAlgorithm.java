package com.dekal.scheduling;

import java.util.List;
import java.util.stream.IntStream;
import com.dekal.scheduling.entity.Class;

public class GeneticAlgorithm {
    Data data;

    public GeneticAlgorithm(Data data) {
        this.data = data;
    }

    public Population evolve(Population population) {
        return mutatePopulation(crossOverPopulation(population));
    }

    private Population crossOverPopulation(Population population) {
        Population crossOverPop = new Population(population.schedules.size(), data);
        List<Schedule> normalSchedules = population.getSchedules();
        IntStream.range(0, Driver.ELITE_SCHEDULE_NUM)
                .forEach(elite -> {
                    crossOverPop.getSchedules().set(elite, normalSchedules.get(elite));
                });

        IntStream.range(Driver.ELITE_SCHEDULE_NUM, population.schedules.size())
                .forEach(x -> {
                    List<Schedule> crossOverSchedules = crossOverPop.getSchedules();
                    if (Driver.CROSSOVER_RATE > Math.random()) {
                        Schedule schedule1 =
                                selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
                        Schedule schedule2 =
                                selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
                        crossOverSchedules.set(x, crossOverSchedule(schedule1, schedule2));
                    } else {
                        crossOverSchedules.set(x, normalSchedules.get(x));
                    }
                });
        return crossOverPop;
    }

    private Schedule crossOverSchedule(Schedule schedule1, Schedule schedule2) {
        Schedule crossOverSchedule = new Schedule(data).init();
        List<Class> classes = crossOverSchedule.getClasses();
        int classSize = classes.size();
        IntStream.range(0, classSize).forEach(index -> {
            if (Math.random() > 0.5) {
                classes.set(index, schedule1.getClasses().get(index));
            } else {
                classes.set(index, schedule2.getClasses().get(index));
            }
        });
        return crossOverSchedule;
    }

    private Population mutatePopulation(Population population) {
        Population mutatePopulation = new Population(population.schedules.size(), data);
        List<Schedule> schedules = mutatePopulation.getSchedules();
        List<Schedule> normalSchedules = population.getSchedules();
        IntStream.range(0, Driver.ELITE_SCHEDULE_NUM)
                .forEach(elite -> {
                    schedules.set(elite, normalSchedules.get(elite));
                });

        IntStream.range(Driver.ELITE_SCHEDULE_NUM, population.schedules.size())
                .forEach(x -> {
                    schedules.set(x, mutateSchedule(normalSchedules.get(x)));
                });
        return mutatePopulation;
    }

    private Schedule mutateSchedule(Schedule schedule) {
        Schedule mutateSchedule = new Schedule(data).init();
        List<Class> classes = mutateSchedule.getClasses();
        int classSize = classes.size();
        IntStream.range(0, classSize).forEach(index -> {
            if (Driver.MUTATION_RATE > Math.random()) {
                classes.set(index, schedule.getClasses().get(index));
            }
        });
        return mutateSchedule;
    }

    private Population selectTournamentPopulation(Population population) {
        Population tournamentPopulation = new Population(Driver.TOURNAMENT_SELECTION_SIZE, data);
        List<Schedule> tournamentSchedules = tournamentPopulation.getSchedules();
        IntStream.range(0, Driver.TOURNAMENT_SELECTION_SIZE)
                .forEach(x -> {
                    tournamentSchedules.set(x,
                            population.getSchedules().get((int)(Math.random() * population.getSchedules().size())));
                });
        return tournamentPopulation;
    }

}
