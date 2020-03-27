package com.dekal.scheduling.algorithm;

import com.dekal.scheduling.entity.Population;
import com.dekal.scheduling.entity.Schedule;
import com.dekal.scheduling.factory.PopulationFactory;


import java.util.List;

public class PopulationAlgorithm {
    private PopulationFactory populationFactory;
    private ScheduleAlgorithm scheduleAlgorithm;

    public PopulationAlgorithm(PopulationFactory populationFactory, ScheduleAlgorithm scheduleAlgorithm) {
        this.populationFactory = populationFactory;
        this.scheduleAlgorithm = scheduleAlgorithm;
    }

    public Population sortByFitness(Population population) {
        return sortByFitness(population, true);
    }

    public Population sortByFitness(Population population, boolean isForceFitness) {
        List<Schedule> schedules = population.getSchedules();
        schedules.sort((schedule1, schedule2) -> {
            double schedule1Fitness = scheduleAlgorithm.calcFitness(schedule1, isForceFitness);
            double schedule2Fitness = scheduleAlgorithm.calcFitness(schedule2, isForceFitness);
            return Double.compare(schedule2Fitness, schedule1Fitness);
        });
        return population;
    }

    private Schedule sortAndGetBestFitSchedule(Population population) {
        sortByFitness(population, false);
        return population.getSchedules().get(0);
    }

    Schedule doTournamentAndGetBestFit(Population population) {
        List<Schedule> schedules = population.getSchedules();
        Population tournamentPopulation = populationFactory.createTournament(schedules);
        return sortAndGetBestFitSchedule(tournamentPopulation);
    }

    public boolean isFit(Population population) {
        return population.getSchedules().get(0).getFitness() != 1.0;
    }
}
