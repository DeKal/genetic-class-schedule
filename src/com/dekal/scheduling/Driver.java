package com.dekal.scheduling;

import com.dekal.scheduling.utils.Printer;

public class Driver {
    public final static int POPULATION_SIZE = 9;
    public final static double MUTATION_RATE = 0.1;
    public final static double CROSSOVER_RATE = 0.9;
    public final static int TOURNAMENT_SELECTION_SIZE = 3;
    public final static int ELITE_SCHEDULE_NUM = 1;
    public final static Printer printer = new Printer();

    public static void main(String[] args) {
        Data data = new Data();
        printer.printAvailableData(data);

        GeneticAlgorithm algorithm = new GeneticAlgorithm(data);
        int generationNumber = 0;
        Population population = new Population(Driver.POPULATION_SIZE, data).sortByFitness();
        printer.printPopulation(generationNumber, population);

        while (population.getSchedules().get(0).getFitness() != 1.0) {
            population = algorithm.evolve(population).sortByFitness();
            printer.printPopulation(generationNumber, population);
            generationNumber++;
        }
    }

}