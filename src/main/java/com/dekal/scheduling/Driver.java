package com.dekal.scheduling;

import com.dekal.scheduling.algorithm.GeneticAlgorithm;
import com.dekal.scheduling.algorithm.PopulationAlgorithm;
import com.dekal.scheduling.algorithm.ScheduleAlgorithm;
import com.dekal.scheduling.algorithm.SelectAlgorithm;
import com.dekal.scheduling.config.Config;
import com.dekal.scheduling.entity.Population;
import com.dekal.scheduling.factory.PopulationFactory;
import com.dekal.scheduling.factory.ScheduleFactory;
import com.dekal.scheduling.input.Data;
import com.dekal.scheduling.printer.Printer;

public class Driver {
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

        int generationNumber = 0;

        Population population = populationFactory.create(Config.POPULATION_SIZE);
        populationAlgorithm.sortByFitness(population);
        printer.printPopulation(generationNumber, population);

        while (populationAlgorithm.isFit(population)) {
            population = algorithm.evolve(population);
            populationAlgorithm.sortByFitness(population);
            printer.printPopulation(generationNumber, population);
            generationNumber++;
        }
    }

}