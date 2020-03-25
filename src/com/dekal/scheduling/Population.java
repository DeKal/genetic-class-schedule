package com.dekal.scheduling;

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
        schedules.sort((schedule1, schedule2) -> {
            if (schedule1.getFitness() > schedule2.getFitness()) {
                return -1;
            }
            if (schedule1.getFitness() < schedule2.getFitness()) {
                return 1;
            }
            return 0;
        });

        return this;
    }
}
