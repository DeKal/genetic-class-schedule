package com.dekal.scheduling.runner;

import com.dekal.scheduling.algorithm.GeneticAlgorithm;
import com.dekal.scheduling.algorithm.PopulationAlgorithm;
import com.dekal.scheduling.algorithm.ScheduleAlgorithm;
import com.dekal.scheduling.algorithm.SelectAlgorithm;
import com.dekal.scheduling.entity.Schedule;
import com.dekal.scheduling.factory.PopulationFactory;
import com.dekal.scheduling.factory.ScheduleFactory;
import com.dekal.scheduling.input.Data;
import com.dekal.scheduling.printer.Printer;

public class RunWithResult {
    final static Data data = new Data();
    final static Printer printer = new Printer();
    final static SelectAlgorithm selector = new SelectAlgorithm();
    final static ScheduleFactory scheduleFactory = new ScheduleFactory(data, selector);
    final static ScheduleAlgorithm scheduleAlgo = new ScheduleAlgorithm();
    final static PopulationFactory populationFactory = new PopulationFactory(scheduleFactory, selector);
    final static PopulationAlgorithm populationAlgorithm = new PopulationAlgorithm(populationFactory, scheduleAlgo);

    public static void main(String[] args) {

        printer.printAvailableData(data);

        GeneticAlgorithm algorithm =
                new GeneticAlgorithm(populationFactory,
                        scheduleFactory,
                        selector,
                        populationAlgorithm,
                        scheduleAlgo);

        Schedule schedule = algorithm.schedule();
        printer.printSchedule(schedule, algorithm.getGeneration());
    }
}
