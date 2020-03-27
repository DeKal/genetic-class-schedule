package com.dekal.scheduling;

import com.dekal.scheduling.algorithm.GeneticAlgorithm;
import com.dekal.scheduling.algorithm.Population;
import com.dekal.scheduling.algorithm.SelectAlgorithm;
import com.dekal.scheduling.config.Config;
import com.dekal.scheduling.input.Data;
import com.dekal.scheduling.printer.Printer;

public class Driver {
    final static Printer printer = new Printer();
    final static SelectAlgorithm selector = new SelectAlgorithm();

    public static void main(String[] args) {
        Data data = new Data();
        printer.printAvailableData(data);

        GeneticAlgorithm algorithm = new GeneticAlgorithm(data, selector);

        int generationNumber = 0;
        Population population =
                Population.create(Config.POPULATION_SIZE, data, selector).sortByFitness();

        printer.printPopulation(generationNumber, population);
        while (population.getSchedules().get(0).getFitness() != 1.0) {
            population = algorithm.evolve(population).sortByFitness();
            printer.printPopulation(generationNumber, population);
            generationNumber++;
        }
    }

}