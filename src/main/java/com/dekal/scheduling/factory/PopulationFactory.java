package com.dekal.scheduling.factory;

import com.dekal.scheduling.algorithm.SelectAlgorithm;
import com.dekal.scheduling.config.Config;
import com.dekal.scheduling.entity.Population;
import com.dekal.scheduling.entity.Schedule;
import com.dekal.scheduling.input.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PopulationFactory {
    private ScheduleFactory scheduleFactory;
    private SelectAlgorithm selector;

    public PopulationFactory(ScheduleFactory scheduleFactory, SelectAlgorithm selector) {
        this.scheduleFactory = scheduleFactory;
        this.selector = selector;
    }

    public Population create(int size) {
        List<Schedule> schedules = new ArrayList<>(size);
        IntStream.range(0, size)
                .forEach(time -> schedules.add(scheduleFactory.create()));
        return new Population(schedules);
    }

    public Population createTournament(List<Schedule> schedules) {
        Population tournamentPopulation = create(Config.TOURNAMENT_SELECTION_SIZE);
        List<Schedule> tournamentSchedules = tournamentPopulation.getSchedules();
        IntStream.range(0, Config.TOURNAMENT_SELECTION_SIZE).forEach(x -> {
            tournamentSchedules.set(x, selector.pickRandom(schedules));
        });
        return tournamentPopulation;
    }
}
