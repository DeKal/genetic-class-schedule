package com.dekal.scheduling.algorithm;

import com.dekal.scheduling.config.Config;
import com.dekal.scheduling.input.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Population {
    private List<Schedule> schedules;
    private SelectAlgorithm selector;

    public Population(List<Schedule> schedules, SelectAlgorithm selector) {
        this.selector = selector;
        this.schedules = schedules;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public Population sortByFitness() {
        schedules.sort((schedule1, schedule2) ->
                Double.compare(schedule2.getFitness(), schedule1.getFitness()));

        return this;
    }

    private Schedule sortAndGetBestFitSchedule() {
        sortByFitness();
        return schedules.get(0);
    }

    public static Population create(int size, Data data, SelectAlgorithm selector) {
        List<Schedule> schedules = new ArrayList<>(size);
        IntStream.range(0, size)
                .forEach(time -> schedules.add(Schedule.create(data, selector)));
        return new Population(schedules, selector);
    }

    private Population createTournamentPopulation(Data data) {
        Population tournamentPopulation =
                Population.create(Config.TOURNAMENT_SELECTION_SIZE, data, selector);
        List<Schedule> tournamentSchedules = tournamentPopulation.getSchedules();
        IntStream.range(0, Config.TOURNAMENT_SELECTION_SIZE).forEach(x -> {
            tournamentSchedules.set(x, selector.pickRandom(schedules));
        });
        return tournamentPopulation;
    }

    public Schedule doTournamentAndGetBestFit(Data data) {
        Population tournamentPopulation = createTournamentPopulation(data);
        return tournamentPopulation.sortAndGetBestFitSchedule();
    }
}
