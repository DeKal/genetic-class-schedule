package com.dekal.scheduling.algo;

import com.dekal.scheduling.input.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Population {
    List<Schedule> schedules;

    public Population(int size, Data data) {
        schedules = new ArrayList<>(size);
        IntStream.range(0, size)
                .forEach(time -> schedules.add(new Schedule(data).init()));
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public Population sortByFitness() {
        schedules.sort((schedule1, schedule2) ->
                Double.compare(schedule2.getFitness(), schedule1.getFitness()));

        return this;
    }
}
