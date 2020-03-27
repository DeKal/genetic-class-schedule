package com.dekal.scheduling.entity;

import java.util.List;


public class Population {
    private List<Schedule> schedules;

    public Population(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }
}
